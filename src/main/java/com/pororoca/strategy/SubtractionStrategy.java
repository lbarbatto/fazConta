package com.pororoca.strategy;

/**
 * Strategy responsible for subtraction.
 */
public final class SubtractionStrategy implements OperationStrategy {

    /**
     * Singleton instance.
     */
    public static final SubtractionStrategy INSTANCE =
            new SubtractionStrategy();

    /**
     * Prevents external instantiation.
     */
    private SubtractionStrategy() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculate(double left, double right) {
        return left - right;
    }

}