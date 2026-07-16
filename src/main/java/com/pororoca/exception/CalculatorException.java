package com.pororoca.exception;

/**
 * Represents domain-specific calculator errors.
 *
 * <p>This exception encapsulates failures that occur
 * during calculator operations.</p>
 */
public class CalculatorException
        extends RuntimeException {


    /**
     * Creates a calculator exception.
     *
     * @param message error description
     */
    public CalculatorException(
            String message
    ) {

        super(message);

    }

}