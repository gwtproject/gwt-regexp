package org.gwtproject.regexp.shared;

import org.gwtproject.regexp.server.JavaRegExp;

/**
 * RegExp factory to create Java implementation
 */
class JavaRegExpFactory extends NativeRegExpFactory {

    @GwtIncompatible
    @Override
    RegExp compile(String pattern) {
        return JavaRegExp.compile(pattern);
    }

    @GwtIncompatible
    @Override
    RegExp compile(String pattern, String flags) {
        return JavaRegExp.compile(pattern, flags);
    }

    @GwtIncompatible
    @Override
    String quote(String input) {
        return JavaRegExp.quote(input);
    }
}
