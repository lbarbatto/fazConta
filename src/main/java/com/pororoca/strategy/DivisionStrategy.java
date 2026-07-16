package com.pororoca.strategy;

import com.pororoca.exception.CalculatorException;

/**
 * Strategy responsible for division operations.
 */
public final class DivisionStrategy implements OperationStrategy {

    /**
     * Singleton instance.
     */
    public static final DivisionStrategy INSTANCE =
            new DivisionStrategy();

    /**
     * Prevents external instantiation.
     */
    private DivisionStrategy(){
    }

    /**
     * {@inheritDoc}
     *
     * @throws CalculatorException when attempting division by zero
     */
    @Override
    public double calculate(double left, double right) {

        if (right == 0) {
            throw new CalculatorException("Divisão por zero.");
        }
        return left / right;
    }
}
