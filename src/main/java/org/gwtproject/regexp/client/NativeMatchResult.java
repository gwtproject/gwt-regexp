package org.gwtproject.regexp.client;

import elemental2.core.JsArray;
import org.gwtproject.regexp.shared.MatchResult;

public class NativeMatchResult implements MatchResult {

    private JsArray<String> array;

    NativeMatchResult(JsArray<String> results) {
        array = results;
    }

    @Override
    public String getGroup(int index) {
        return array.getAt(index);
    }

    @Override
    public int getGroupCount() {
        return array.length;
    }

    @Override
    public int getIndex() {
        return array.index;
    }

    @Override
    public String getInput() {
        return array.input;
    }
}