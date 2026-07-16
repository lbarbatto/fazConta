package com.pororoca.command;

import com.pororoca.service.Calculator;

/**
 * Command responsible for numeric digit input.
 */
public final class DigitCommand implements ButtonCommand {


    private final String digit;


    /**
     * Creates a digit command.
     *
     * @param digit digit value
     */
    public DigitCommand(
            String digit
    ) {

        this.digit = digit;

    }


    @Override
    public void execute(
            Calculator calculator
    ) {

        calculator.inputDigit(digit);

    }

}