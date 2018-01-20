/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.regexp.client;

import elemental2.core.JsRegExp;
import elemental2.core.JsString;
import jsinterop.base.Js;
import org.gwtproject.regexp.shared.MatchResult;
import org.gwtproject.regexp.shared.RegExp;
import org.gwtproject.regexp.shared.SplitResult;

import java.util.logging.Logger;

import static java.util.Objects.isNull;

/**
 * A class for regular expressions with features like Javascript's RegExp, plus
 * Javascript String's replace and split methods (which can take a RegExp
 * parameter). The pure Java implementation (for server-side use) uses Java's
 * Pattern class, unavailable under GWT. The super-sourced GWT implementation
 * simply calls on to the native Javascript classes.
 * <p>
 * There are a few small incompatibilities between the two implementations.
 * Java-specific constructs in the regular expression syntax (e.g. [a-z&&[^bc]],
 * (?<=foo), \A, \Q) work only in the pure Java implementation, not the GWT
 * implementation, and are not rejected by either. Also, the Javascript-specific
 * constructs $` and $' in the replacement expression work only in the GWT
 * implementation, not the pure Java implementation, which rejects them.
 */
public class NativeRegExp implements RegExp {

    private static final Logger LOGGER = Logger.getLogger(NativeRegExp.class.getCanonicalName());

    /**
     * Creates a regular expression object from a pattern with no flags.
     *
     * @param pattern the Javascript regular expression pattern to compile
     * @return a new regular expression
     * @throws RuntimeException if the pattern is invalid
     */
    public static RegExp compile(String pattern) {
        return new NativeRegExp(pattern);
    }

    /**
     * Creates a regular expression object from a pattern with no flags.
     *
     * @param pattern the Javascript regular expression pattern to compile
     * @param flags   the flags string, containing at most one occurence of {@code
     *                'g'} ({@link #getGlobal()}), {@code 'i'} ({@link #getIgnoreCase()}
     *                ), or {@code 'm'} ({@link #getMultiline()}).
     * @return a new regular expression
     * @throws RuntimeException if the pattern or the flags are invalid
     */
    public static RegExp compile(String pattern, String flags) {
        return new NativeRegExp(pattern, flags);
    }

    /**
     * Returns a literal pattern <code>String</code> for the specified
     * <code>String</code>.
     * <p>
     * <p>This method produces a <code>String</code> that can be used to
     * create a <code>RegExp</code> that would match the string
     * <code>s</code> as if it were a literal pattern.</p> Metacharacters
     * or escape sequences in the input sequence will be given no special
     * meaning.
     *
     * @param input The string to be literalized
     * @return A literal string replacement
     */
    public static String quote(String input) {
        // /([.?*+^$[\]\\(){}|-])/g
        return new JsString(input).replace("/([.?*+^$[\\]\\\\(){}|-])/g", "\\$1");
    }

    private JsRegExp jsRegExp;

    private NativeRegExp() {
        jsRegExp = new JsRegExp();
    }

    private NativeRegExp(String pattern) {
        this();
        jsRegExp.compile(pattern);
    }

    private NativeRegExp(String pattern, String flags) {
        this();
        jsRegExp.compile(pattern, flags);
    }

    /**
     * Applies the regular expression to the given string. This call affects the
     * value returned by {@link #getLastIndex()} if the global flag is set.
     *
     * @param input the string to apply the regular expression to
     * @return a match result if the string matches, else {@code null}
     */
    @Override
    public MatchResult exec(String input) {
        String[] result = jsRegExp.exec(input);
        return isNull(result) ? null : new NativeMatchResult(Js.cast(result));
    } /*-{
     return this.exec(input);
   }-*/

    ;

    /**
     * Returns whether the regular expression captures all occurences of the
     * pattern.
     */
    @Override
    public boolean getGlobal() {
        return jsRegExp.global;
    } /*-{
    return this.global;
  }-*/

    ;

    /**
     * Returns whether the regular expression ignores case.
     */
    @Override
    public boolean getIgnoreCase() {
        return jsRegExp.ignoreCase;
    } /*-{
    return this.ignoreCase;
  }-*/

    ;

    /**
     * Returns the zero-based position at which to start the next match. The
     * return value is not defined if the global flag is not set. After a call
     * to {@link #exec(String)} or {@link #test(String)}, this method returns
     * the next position following the most recent match.
     *
     * @see #getGlobal()
     */
    @Override
    public int getLastIndex() {
        return jsRegExp.lastIndex;
    } /*-{
     return this.lastIndex;
   }-*/

    ;

    /**
     * Returns whether '$' and '^' match line returns ('\n' and '\r') in addition
     * to the beginning or end of the string.
     */
    @Override
    public boolean getMultiline() {
        return jsRegExp.multiline;
    } /*-{
    return this.multiline;
  }-*/

    ;

    /**
     * Returns the pattern string of the regular expression.
     */
    @Override
    public String getSource() {
        return jsRegExp.source;
    } /*-{
     return this.source;
   }-*/

    ;

    /**
     * Returns the input string with the part(s) matching the regular expression
     * replaced with the replacement string. If the global flag is set, replaces
     * all matches of the regular expression. Otherwise, replaces the first match
     * of the regular expression. As per Javascript semantics, backslashes in the
     * replacement string get no special treatment, but the replacement string can
     * use the following special patterns:
     * <ul>
     * <li>$1, $2, ... $99 - inserts the n'th group matched by the regular
     * expression.
     * <li>$&amp; - inserts the entire string matched by the regular expression.
     * <li>$$ - inserts a $.
     * </ul>
     *
     * @param input       the string in which the regular expression is to be searched.
     * @param replacement the replacement string.
     * @return the input string with the regular expression replaced with the
     * replacement string.
     * @throws RuntimeException if {@code replacement} is invalid
     */
    @Override
    public String replace(String input, String replacement) {
        return new JsString(input).replace(jsRegExp, replacement);/*-{
     return input.replace(this, replacement);
   }-*/
    }

    ;

    /**
     * Sets the zero-based position at which to start the next match.
     */
    @Override
    public void setLastIndex(int lastIndex) {
        jsRegExp.lastIndex = lastIndex;
        /*-{
     this.lastIndex = lastIndex;
   }-*/
    }

    ;

    /**
     * Splits the input string around matches of the regular expression. If the
     * regular expression is completely empty, splits the input string into its
     * constituent characters. If the regular expression is not empty but matches
     * an empty string, the results are not well defined.
     *
     * @param input the string to be split.
     * @return the strings split off, any of which may be empty.
     */
    @Override
    public SplitResult split(String input) {
        return new NativeSplitResult(Js.cast(new JsString(input).split(jsRegExp)));
        /*-{
     return input.split(this);
   }-*/
    }

    ;

    /**
     * Splits the input string around matches of the regular expression. If the
     * regular expression is completely empty, splits the input string into its
     * constituent characters. If the regular expression is not empty but matches
     * an empty string, the results are not well defined.
     *
     * @param input the string to be split.
     * @param limit the maximum number of strings to split off and return,
     *              ignoring the rest of the input string. If negative, there is no
     *              limit.
     * @return the strings split off, any of which may be empty.
     */
    @Override
    public SplitResult split(String input, int limit) {
        return new NativeSplitResult(Js.cast(new JsString(input).split(jsRegExp, limit)));
        /*-{
     return input.split(this, limit);
   }-*/
    }

    ;

    /**
     * Determines if the regular expression matches the given string. This call
     * affects the value returned by {@link #getLastIndex()} if the global flag is
     * not set. Equivalent to: {@code exec(input) != null}
     *
     * @param input the string to apply the regular expression to
     * @return whether the regular expression matches the given string.
     */
    @Override
    public boolean test(String input) {
        return jsRegExp.test(input);/*-{
     return this.test(input);
   }-*/
    }

    ;
}