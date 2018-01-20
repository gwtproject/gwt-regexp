package org.gwtproject.regexp.shared;

public interface SplitResult {
    /**
     * Returns one the strings split off.
     *
     * @param index the index of the string to be returned.
     * @return The index'th string resulting from the split.
     */
    String get(int index);

    /**
     * Returns the number of strings split off.
     */
    int length();

    /**
     * Sets (overrides) one of the strings split off.
     *
     * @param index the index of the string to be set.
     */
    void set(int index, String value);
}
