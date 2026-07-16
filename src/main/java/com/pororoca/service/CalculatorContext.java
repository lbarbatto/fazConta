package com.pororoca.service;

import com.pororoca.exception.CalculatorException;
import com.pororoca.factory.OperationFactory;
import com.pororoca.model.Operator;
import com.pororoca.state.CalculatorState;
import com.pororoca.state.EnteringNumberState;
import com.pororoca.state.ResultState;
import com.pororoca.util.CalculatorLogger;

import java.util.logging.Logger;

/**
 * Maintains the internal state of the calculator.
 *
 * <p>The context delegates behavior to the current
 * CalculatorState implementation.</p>
 *
 * <p>Part of the GoF State pattern.</p>
 */
public class CalculatorContext {

    /**
     * Logger for monitoring internal context operations.
     */
    private static final Logger LOGGER =
            CalculatorLogger.forClass(CalculatorContext.class);


    /**
     * Current numeric value shown on display.
     */
    private String displayValue = "0";


    /**
     * Stored accumulated value.
     */
    private double accumulator;


    /**
     * Pending arithmetic operation.
     */
    private Operator pendingOperator;


    /**
     * Last executed operator (used for repeated equals).
     */
    private Operator lastOperator;


    /**
     * Last right operand used (used for repeated equals).
     */
    private double lastOperand;


    /**
     * Current calculator state.
     */
    private CalculatorState state;


    /**
     * Creates a new calculator context.
     */
    public CalculatorContext() {

        this.state =
                EnteringNumberState.INSTANCE;

    }


    /**
     * Delegates digit input to current state.
     *
     * @param digit digit pressed
     */
    public void inputDigit(String digit) {

        state.inputDigit(this, digit);

    }


    /**
     * Delegates decimal input.
     */
    public void inputDecimal() {

        state.inputDecimal(this);

    }


    /**
     * Delegates operator selection.
     *
     * @param operator selected operator
     */
    public void selectOperator(
            Operator operator
    ) {

        state.selectOperator(
                this,
                operator
        );

    }


    /**
     * Delegates calculation.
     */
    public void calculateResult() {

        state.calculateResult(this);

    }


    /**
     * Clears calculator state.
     */
    public void clear() {

        state.clear(this);
    }


    /**
     * Clears only the current entry, preserving the
     * accumulated value and pending operation.
     */
    public void clearEntry() {

        state.clearEntry(this);
    }


    /**
     * Removes the last entered character.
     */
    public void backspace() {

        state.backspace(this);
    }


    /**
     * Changes the sign of the current value.
     */
    public void toggleSignal() {

        state.toggleSignal(this);
    }


    // ----------------------------
    // State support methods
    // ----------------------------


    public String getDisplayValue() {
        return displayValue;
    }


    public void setDisplayValue(
            String displayValue
    ) {
        this.displayValue = displayValue;
    }


    public double getAccumulator() {
        return accumulator;
    }


    public void setAccumulator(
            double accumulator
    ) {
        this.accumulator = accumulator;
    }


    public Operator getPendingOperator() {
        return pendingOperator;
    }


    public void setPendingOperator(
            Operator pendingOperator
    ) {
        this.pendingOperator = pendingOperator;
    }


    public Operator getLastOperator() {
        return lastOperator;
    }


    public void setLastOperator(
            Operator lastOperator
    ) {
        this.lastOperator = lastOperator;
    }


    public double getLastOperand() {
        return lastOperand;
    }


    public void setLastOperand(
            double lastOperand
    ) {
        this.lastOperand = lastOperand;
    }


    /**
     * Changes the current calculator state.
     *
     * @param state new state
     */
    public void changeState(
            CalculatorState state
    ) {

        this.state = state;

    }

    /**
     * Returns the current displayed numeric value.
     *
     * @return current numeric value
     */
    public double getDisplayAsDouble() {

        return Double.parseDouble(displayValue);
    }

    /**
     * Executes pending operation if available.
     */
    public void executePendingOperation() {

        if (pendingOperator == null) {
            return;
        }

        double right =
                getDisplayAsDouble();

        try {
            double result =
                    OperationFactory
                            .create(pendingOperator)
                            .calculate(
                                    accumulator,
                                    right
                            );

            accumulator = result;
            displayValue =
                    DisplayFormatter.format(result);
            lastOperator = pendingOperator;
            lastOperand = right;
            LOGGER.info("Executed " + pendingOperator
                    + " -> " + displayValue);
        } catch (ArithmeticException e) {
            LOGGER.severe("Arithmetic failure: "
                    + e.getMessage());
            throw new CalculatorException(
                    e.getMessage()
            );
        }
    }


}