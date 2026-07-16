package com.pororoca.util;

import java.util.logging.Logger;

/**
 * Centralizes logger instances for the FazConta application.
 *
 * <p>Using a single accessor avoids scattered logger creation and
 * guarantees a consistent logger naming convention across the
 * codebase. Relies solely on {@link java.util.logging} so no
 * external dependency is introduced.</p>
 */
public final class CalculatorLogger {

    /**
     * Base logger name for the application.
     */
    private static final String BASE_NAME =
            "com.pororoca.fazconta";

    /**
     * Prevents instantiation.
     */
    private CalculatorLogger() {
    }

    /**
     * Returns a logger scoped to the given class.
     *
     * <p>The resulting logger name follows the pattern
     * {@code com.pororoca.fazconta.<simpleClassName>}, which keeps
     * log entries traceable to their origin.</p>
     *
     * @param clazz class that will use the logger
     * @return configured logger instance
     */
    public static Logger forClass(
            Class<?> clazz
    ) {

        return Logger.getLogger(
                BASE_NAME + "." + clazz.getSimpleName()
        );

    }

}
