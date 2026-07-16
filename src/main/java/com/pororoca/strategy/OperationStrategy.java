package com.pororoca.strategy;

/**
 * Defines the contract for all arithmetic operations.
 *
 * <p>Each implementation encapsulates a single arithmetic
 * algorithm, following the Strategy design pattern.</p>
 */
@FunctionalInterface
public interface OperationStrategy {

    /**
     * Executes the arithmetic operation.
     *
     * @param left  left operand
     * @param right right operand
     * @return operation result
     */
    double calculate(double left, double right);
}
