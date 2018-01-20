package org.gwtproject.regexp.shared;

import com.google.gwt.core.shared.GwtIncompatible;
import org.gwtproject.regexp.server.JavaRegExp;

class JavaFactory extends RegExpFactory {

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
