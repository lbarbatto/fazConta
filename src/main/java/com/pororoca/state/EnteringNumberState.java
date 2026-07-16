package com.pororoca.state;

import com.pororoca.model.Operator;
import com.pororoca.service.CalculatorContext;
import com.pororoca.service.DisplayFormatter;


/**
 * State used while the user is entering a number.
 */
public final class EnteringNumberState
        implements CalculatorState {


    public static final EnteringNumberState INSTANCE =
            new EnteringNumberState();


    private EnteringNumberState() {
    }


    @Override
    public void inputDigit(
            CalculatorContext context,
            String digit
    ) {

        String current =
                context.getDisplayValue();


        if ("0".equals(current)) {

            context.setDisplayValue(digit);

        } else {

            context.setDisplayValue(
                    current + digit
            );

        }

    }


    @Override
    public void inputDecimal(
            CalculatorContext context
    ) {

        if (!context
                .getDisplayValue()
                .contains(".")) {

            context.setDisplayValue(
                    context.getDisplayValue() + "."
            );

        }

    }


    @Override
    public void selectOperator(
            CalculatorContext context,
            Operator operator
    ) {

        context.setAccumulator(
                context.getDisplayAsDouble()
        );

        context.setPendingOperator(operator);

        context.changeState(
                OperatorSelectedState.INSTANCE
        );

    }


    @Override
    public void calculateResult(
            CalculatorContext context
    ) {
        if (context.getPendingOperator() != null) {
            context.executePendingOperation();
            context.changeState(
                    ResultState.INSTANCE
            );
        }
    }


    @Override
    public void clear(
            CalculatorContext context
    ) {

        context.setDisplayValue("0");

    }


    @Override
    public void clearEntry(
            CalculatorContext context
    ) {

        context.setDisplayValue("0");

    }


    @Override
    public void backspace(
            CalculatorContext context
    ) {

        String value =
                context.getDisplayValue();

        if (value.length() <= 1) {
            context.setDisplayValue("0");
            return;
        }

        context.setDisplayValue(
                value.substring(
                        0,
                        value.length() - 1
                )
        );

    }


    @Override
    public void toggleSignal(
            CalculatorContext context
    ) {

        double value =
                context.getDisplayAsDouble();

        if (value == 0) {
            return;
        }

        context.setDisplayValue(
                DisplayFormatter.format(
                        value * -1
                )
        );

    }

}