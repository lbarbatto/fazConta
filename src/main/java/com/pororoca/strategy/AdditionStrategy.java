package com.pororoca.strategy;

/**
 * Strategy responsible for addition.
 */
public final class AdditionStrategy implements OperationStrategy {

    /**
     * Singleton instance.
     */
    public static final AdditionStrategy INSTANCE =
            new AdditionStrategy();

    /**
     * Prevents external instantiation.
     */
    private AdditionStrategy() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculate(double left, double right) {
        return left + right;
    }

}