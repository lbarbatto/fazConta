package com.pororoca.service;

import com.pororoca.model.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests for the calculator behavior exposed by
 * {@link Calculator} and orchestrated through the State pattern.
 *
 * <p>These tests exercise the end-to-end flows that were
 * corrected in previous refactor steps.</p>
 */
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void additionShouldProduceCorrectResult() {
        calculator.inputDigit("1");
        calculator.selectOperator(Operator.ADD);
        calculator.inputDigit("2");
        calculator.calculateResult();

        assertEquals("3", calculator.getDisplayValue());
    }

    @Test
    void divisionByZeroShouldShowError() {
        calculator.inputDigit("5");
        calculator.selectOperator(Operator.DIVIDE);
        calculator.inputDigit("0");
        calculator.calculateResult();

        assertEquals("Erro", calculator.getDisplayValue());
    }

    @Test
    void clearShouldResetAccumulatorAndOperator() {
        calculator.inputDigit("5");
        calculator.selectOperator(Operator.ADD);
        calculator.clear();

        assertEquals("0", calculator.getDisplayValue());
    }

    @Test
    void clearEntryShouldPreservePendingOperation() {
        calculator.inputDigit("5");
        calculator.selectOperator(Operator.ADD);
        calculator.inputDigit("3");
        calculator.clearEntry();
        calculator.inputDigit("2");
        calculator.calculateResult();

        assertEquals("7", calculator.getDisplayValue());
    }

    @Test
    void toggleSignalOnZeroShouldRemainZero() {
        calculator.toggleSignal();

        assertEquals("0", calculator.getDisplayValue());
    }

    @Test
    void repeatedEqualsShouldReapplyLastOperation() {
        calculator.inputDigit("1");
        calculator.selectOperator(Operator.ADD);
        calculator.inputDigit("2");
        calculator.calculateResult();
        calculator.calculateResult();

        assertEquals("5", calculator.getDisplayValue());
    }

    @Test
    void backspaceShouldRemoveLastDigit() {
        calculator.inputDigit("1");
        calculator.inputDigit("2");
        calculator.inputDigit("3");
        calculator.backspace();

        assertEquals("12", calculator.getDisplayValue());
    }
}
