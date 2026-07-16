package com.pororoca.command;


import com.pororoca.model.Operator;
import com.pororoca.service.Calculator;

/**
 * Command responsible for selecting arithmetic operations.
 */
public final class OperatorCommand
        implements ButtonCommand {


    private final Operator operator;


    public OperatorCommand(
            Operator operator
    ) {

        this.operator = operator;

    }


    @Override
    public void execute(
            Calculator calculator
    ) {

        calculator.selectOperator(operator);

    }

}