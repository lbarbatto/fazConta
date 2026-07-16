package com.pororoca.command;


import com.pororoca.service.Calculator;

/**
 * Command responsible for calculating results.
 */
public final class EqualsCommand
        implements ButtonCommand {


    public static final EqualsCommand INSTANCE =
            new EqualsCommand();


    private EqualsCommand() {
    }


    @Override
    public void execute(
            Calculator calculator
    ) {

        calculator.calculateResult();

    }

}