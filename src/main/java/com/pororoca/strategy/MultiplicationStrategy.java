package com.pororoca.strategy;

/**
 * Strategy responsible for multiplication.
 */
public final class MultiplicationStrategy implements OperationStrategy {

    /**
     * Singleton instance.
     */
    public static final MultiplicationStrategy INSTANCE =
            new MultiplicationStrategy();

    /**
     * Prevents external instantiation.
     */
    private MultiplicationStrategy() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculate(double left, double right) {
        return left * right;
    }

}