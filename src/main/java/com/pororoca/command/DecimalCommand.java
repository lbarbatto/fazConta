package com.pororoca.command;

import com.pororoca.service.Calculator;

/**
 * Command responsible for decimal separator input.
 */
public final class DecimalCommand
        implements ButtonCommand {


    public static final DecimalCommand INSTANCE =
            new DecimalCommand();


    private DecimalCommand() {
    }


    @Override
    public void execute(
            Calculator calculator
    ) {

        calculator.inputDecimal();

    }

}