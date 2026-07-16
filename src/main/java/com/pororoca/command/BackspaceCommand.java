package com.pororoca.command;


import com.pororoca.service.Calculator;

/**
 * Command responsible for deleting last digit.
 */
public final class BackspaceCommand
        implements ButtonCommand {


    public static final BackspaceCommand INSTANCE =
            new BackspaceCommand();


    private BackspaceCommand() {
    }


    @Override
    public void execute(
            Calculator calculator
    ) {

        calculator.backspace();

    }

}