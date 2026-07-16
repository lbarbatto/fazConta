package com.pororoca.command;


import com.pororoca.service.Calculator;

/**
 * Command responsible for changing numeric sign.
 */
public final class ToggleSignalCommand
        implements ButtonCommand {


    public static final ToggleSignalCommand INSTANCE =
            new ToggleSignalCommand();


    private ToggleSignalCommand() {
    }


    @Override
    public void execute(
            Calculator calculator
    ) {

        calculator.toggleSignal();

    }

}