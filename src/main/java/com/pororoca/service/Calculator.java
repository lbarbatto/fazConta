package com.pororoca.service;

import com.pororoca.model.Operator;
import com.pororoca.util.CalculatorLogger;

import java.util.logging.Logger;

/**
 * Facade responsible for exposing calculator operations
 * to external clients.
 *
 * <p>This class hides the internal state management and
 * delegates operations to CalculatorContext.</p>
 */
public class Calculator {

    /**
     * Logger for monitoring calculator operations.
     */
    private static final Logger LOGGER =
            CalculatorLogger.forClass(Calculator.class);


    /**
     * Internal calculator context.
     */
    private final CalculatorContext context;


    /**
     * Creates a calculator with a new context.
     */
    public Calculator() {

        this.context =
                new CalculatorContext();

    }


    /**
     * Creates a calculator using an existing context.
     *
     * @param context calculator context
     */
    public Calculator(
            CalculatorContext context
    ) {

        this.context = context;

    }


    /**
     * Adds a digit to the current input.
     *
     * @param digit digit character
     */
    public void inputDigit(
            String digit
    ) {

        LOGGER.fine("Input digit: " + digit);
        context.inputDigit(digit);

    }


    /**
     * Adds decimal separator.
     */
    public void inputDecimal() {

        LOGGER.fine("Input decimal separator");
        context.inputDecimal();

    }


    /**
     * Selects arithmetic operation.
     *
     * @param operator selected operator
     */
    public void selectOperator(
            Operator operator
    ) {

        LOGGER.info("Select operator: " + operator);
        context.selectOperator(operator);

    }


    /**
     * Executes pending calculation.
     */
    public void calculateResult() {

        LOGGER.info("Calculate result requested");
        try {
            context.calculateResult();
            LOGGER.info("Result: " + context.getDisplayValue());
        } catch (com.pororoca.exception.CalculatorException e) {
            LOGGER.warning("Calculation error: " + e.getMessage());
            context.setDisplayValue("Erro");
        }
    }


    /**
     * Clears calculator.
     */
    public void clear() {

        LOGGER.info("Clear calculator");
        context.clear();
        context.setAccumulator(0);
        context.setPendingOperator(null);

    }


    /**
     * Clears current input.
     *
     * <p>Preserves the accumulated value and pending
     * operation, matching the Windows calculator CE key.</p>
     */
    public void clearEntry() {

        LOGGER.fine("Clear entry");
        context.clearEntry();

    }


    /**
     * Removes last entered character.
     */
    public void backspace() {

        LOGGER.fine("Backspace");
        context.backspace();

    }


    /**
     * Changes the sign of the current value.
     */
    public void toggleSignal() {

        LOGGER.fine("Toggle signal");
        context.toggleSignal();

    }


    /**
     * Returns the value currently shown.
     *
     * @return display text
     */
    public String getDisplayValue() {

        return context.getDisplayValue();

    }


}