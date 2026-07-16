package com.pororoca.state;


import com.pororoca.model.Operator;
import com.pororoca.service.CalculatorContext;
import com.pororoca.service.DisplayFormatter;


/**
 * State used after an operator has been selected.
 */
public final class OperatorSelectedState
        implements CalculatorState {


    public static final OperatorSelectedState INSTANCE =
            new OperatorSelectedState();


    private OperatorSelectedState() {
    }


    @Override
    public void inputDigit(
            CalculatorContext context,
            String digit
    ) {

        context.setDisplayValue(digit);


        context.changeState(
                EnteringNumberState.INSTANCE
        );

    }


    @Override
    public void inputDecimal(
            CalculatorContext context
    ) {

        context.setDisplayValue("0.");


        context.changeState(
                EnteringNumberState.INSTANCE
        );

    }


    @Override
    public void selectOperator(
            CalculatorContext context,
            Operator operator
    ) {

        context.setPendingOperator(operator);

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

        context.setAccumulator(0);

        context.setPendingOperator(null);

        context.setDisplayValue("0");


        context.changeState(
                EnteringNumberState.INSTANCE
        );

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