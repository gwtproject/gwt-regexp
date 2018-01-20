package org.gwtproject.regexp.shared;

import org.gwtproject.regexp.client.NativeRegExp;

class NativeFactory extends RegExpFactory {

    @Override
    RegExp compile(String pattern, String flags) {
        return NativeRegExp.compile(pattern, flags);
    }

    @Override
    RegExp compile(String pattern) {
        return NativeRegExp.compile(pattern);
    }

    @Override
    String quote(String input) {
        return NativeRegExp.quote(input);
    }
}
