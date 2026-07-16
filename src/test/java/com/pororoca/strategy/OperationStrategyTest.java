package com.pororoca.strategy;

import com.pororoca.exception.CalculatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the arithmetic strategy implementations.
 *
 * <p>These tests validate the core calculation algorithms
 * behind each GoF Strategy without any UI dependency.</p>
 */
class OperationStrategyTest {

    @Test
    void additionShouldSumOperands() {
        assertEquals(
                5.0,
                AdditionStrategy.INSTANCE.calculate(2, 3)
        );
    }

    @Test
    void subtractionShouldSubtractOperands() {
        assertEquals(
                -1.0,
                SubtractionStrategy.INSTANCE.calculate(2, 3)
        );
    }

    @Test
    void multiplicationShouldMultiplyOperands() {
        assertEquals(
                6.0,
                MultiplicationStrategy.INSTANCE.calculate(2, 3)
        );
    }

    @Test
    void divisionShouldDivideOperands() {
        assertEquals(
                2.0,
                DivisionStrategy.INSTANCE.calculate(6, 3)
        );
    }

    @Test
    void divisionByZeroShouldThrowCalculatorException() {
        CalculatorException exception =
                assertThrows(
                        CalculatorException.class,
                        () -> DivisionStrategy.INSTANCE
                                .calculate(1, 0)
                );

        assertEquals(
                "Divisão por zero.",
                exception.getMessage()
        );
    }
}
