package org.gwtproject.regexp.client;

import elemental2.core.JsArray;
import org.gwtproject.regexp.shared.MatchResult;

public class NativeMatchResult implements MatchResult {

    private JsArray<String> array;

    NativeMatchResult(JsArray<String> results) {
        array = results;
    }

    /**
     * Retrieves the matched string or the given matched group.
     *
     * @param index the index of the group to return, 0 to return the whole
     *              matched string; must be between 0 and {@code getGroupCount() - 1}
     *              included
     * @return The matched string if {@code index} is zero, else the given matched
     * group. If the given group was optional and did not match, the
     * behavior is browser-dependent: this method will return {@code null}
     * or an empty string.
     */
    @Override
    public String getGroup(int index) {
        return array.getAt(index);
    }

    /**
     * Returns the number of groups, including the matched string hence greater or
     * equal than 1.
     */
    @Override
    public int getGroupCount() {
        return array.length;
    }

    /**
     * Returns the zero-based index of the match in the input string.
     */
    @Override
    public int getIndex() {
        return array.index;
    }

    /**
     * Returns the original input string.
     */
    @Override
    public String getInput() {
        return array.input;
    }
}