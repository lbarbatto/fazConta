package com.pororoca.state;

import com.pororoca.model.Operator;
import com.pororoca.service.CalculatorContext;

/**
 * Defines the behavior contract for calculator states.
 *
 * <p>Each concrete state encapsulates how the calculator
 * should react to user actions depending on its current condition.</p>
 *
 * <p>This interface is part of the GoF State pattern implementation.</p>
 */
public interface CalculatorState {

    /**
     * Handles numeric input.
     *
     * @param context calculator context
     * @param digit numeric value
     */
    void inputDigit(
            CalculatorContext context,
            String digit
    );


    /**
     * Handles decimal point input.
     *
     * @param context calculator context
     */
    void inputDecimal(
            CalculatorContext context
    );


    /**
     * Handles operator selection.
     *
     * @param context calculator context
     * @param operator selected operator
     */
    void selectOperator(
            CalculatorContext context,
            Operator operator
    );


    /**
     * Handles equals operation.
     *
     * @param context calculator context
     */
    void calculateResult(
            CalculatorContext context
    );


    /**
     * Handles clearing the calculator.
     *
     * @param context calculator context
     */
    void clear(
            CalculatorContext context
    );


    /**
     * Handles clearing only the current entry.
     *
     * @param context calculator context
     */
    void clearEntry(
            CalculatorContext context
    );


    /**
     * Handles removing the last entered character.
     *
     * @param context calculator context
     */
    void backspace(
            CalculatorContext context
    );


    /**
     * Handles changing the sign of the current value.
     *
     * @param context calculator context
     */
    void toggleSignal(
            CalculatorContext context
    );

}