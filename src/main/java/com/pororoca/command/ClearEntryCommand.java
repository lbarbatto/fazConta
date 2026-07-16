package com.pororoca.command;


import com.pororoca.service.Calculator;

/**
 * Command responsible for clearing current input.
 */
public final class ClearEntryCommand
        implements ButtonCommand {


    public static final ClearEntryCommand INSTANCE =
            new ClearEntryCommand();


    private ClearEntryCommand() {
    }


    @Override
    public void execute(
            Calculator calculator
    ) {

        calculator.clearEntry();

    }

}