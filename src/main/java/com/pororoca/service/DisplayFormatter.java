package com.pororoca.service;

/**
 * Utility responsible for formatting calculator values
 * before displaying them to the user.
 *
 * <p>This class isolates presentation formatting rules
 * from the calculator business logic.</p>
 */
public final class DisplayFormatter {

    /**
     * Prevents instantiation.
     */
    private DisplayFormatter() {
    }

    /**
     * Formats a numeric value removing unnecessary decimal places.
     *
     * <p>Examples:
     * <ul>
     *     <li>10.0 becomes "10"</li>
     *     <li>10.5 becomes "10.5"</li>
     * </ul>
     *
     * @param value value to format
     * @return formatted representation
     */
    public static String format(double value) {

        if (value == (long) value) {
            return String.valueOf((long) value);
        }

        return String.valueOf(value);
    }

}