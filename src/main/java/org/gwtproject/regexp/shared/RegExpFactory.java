package org.gwtproject.regexp.shared;


/**
 * Factory class for creating RegExp implementation depends on the scope of the execution whether client side or server side
 */
class RegExpFactory {
    RegExp compile(String pattern) {
        throw new UnsupportedOperationException();
    }

    RegExp compile(String pattern, String flags) {
        throw new UnsupportedOperationException();
    }

    String quote(String input) {
        throw new UnsupportedOperationException();
    }
}
