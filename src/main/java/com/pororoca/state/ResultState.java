package com.pororoca.state;


import com.pororoca.model.Operator;
import com.pororoca.service.CalculatorContext;
import com.pororoca.service.DisplayFormatter;


/**
 * State representing a calculated result.
 */
public final class ResultState implements CalculatorState {


    public static final ResultState INSTANCE =
            new ResultState();


    private ResultState() {
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
        if (context.getLastOperator() != null) {
            context.setAccumulator(
                    context.getDisplayAsDouble()
            );
            context.setPendingOperator(
                    context.getLastOperator()
            );
            context.setDisplayValue(
                    DisplayFormatter.format(
                            context.getLastOperand()
                    )
            );
            context.executePendingOperation();
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

        context.changeState(
                EnteringNumberState.INSTANCE
        );

    }


    @Override
    public void backspace(
            CalculatorContext context
    ) {

        String value =
                context.getDisplayValue();

        if (value.length() <= 1) {
            context.setDisplayValue("0");
            context.changeState(
                    EnteringNumberState.INSTANCE
            );
            return;
        }

        context.setDisplayValue(
                value.substring(
                        0,
                        value.length() - 1
                )
        );

        context.changeState(
                EnteringNumberState.INSTANCE
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

        context.changeState(
                EnteringNumberState.INSTANCE
        );

    }

}