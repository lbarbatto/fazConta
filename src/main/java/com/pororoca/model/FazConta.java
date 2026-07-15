package com.pororoca.model;

/**
 * Core domain class responsible for performing arithmetic operations.
 *
 * <p>This class acts as a stateless calculation engine, providing
 * basic mathematical operations used by the calculator UI layer.</p>
 *
 * <p>Design characteristics:
 * <ul>
 *     <li>Stateless utility-style class</li>
 *     <li>No side effects</li>
 *     <li>Pure functions only</li>
 * </ul>
 * </p>
 *
 * <p>This class is intended to be used by controllers or services
 * responsible for UI interaction logic.</p>
 */
public class FazConta {

    /**
     * Executes a binary arithmetic operation between two operands.
     *
     * <p>Supported operations:
     * <ul>
     *     <li>{@code +} addition</li>
     *     <li>{@code -} subtraction</li>
     *     <li>{@code *} multiplication</li>
     *     <li>{@code /} division</li>
     * </ul>
     * </p>
     *
     * <p>Division by zero is not allowed and will result in an exception.</p>
     *
     * @param a  left operand
     * @param op arithmetic operator as a string symbol
     * @param b  right operand
     *
     * @return result of applying the operator to the operands
     *
     * @throws ArithmeticException if division by zero is attempted
     * @throws IllegalArgumentException if the operator is not supported
     */
    public static double calculate(double a, String op, double b) {
        return switch (op) {

            case "+" -> a + b;

            case "-" -> a - b;

            case "*" -> a * b;

            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException(
                            "Division by zero is not allowed"
                    );
                }
                yield a / b;
            }

            default -> throw new IllegalArgumentException(
                    "Unsupported operator: " + op
            );
        };
    }
}