package com.pororoca.command;


import com.pororoca.service.Calculator;

/**
 * Command responsible for clearing calculator state.
 */
public final class ClearCommand
        implements ButtonCommand {


    public static final ClearCommand INSTANCE =
            new ClearCommand();


    private ClearCommand() {
    }


    @Override
    public void execute(
            Calculator calculator
    ) {

        calculator.clear();

    }

}