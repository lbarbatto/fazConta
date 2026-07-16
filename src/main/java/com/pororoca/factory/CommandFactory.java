package com.pororoca.factory;

import com.pororoca.command.*;
import com.pororoca.model.Operator;

/**
 * Factory responsible for creating calculator commands.
 *
 * <p>This class centralizes the association between
 * UI button values and their corresponding Command objects.</p>
 *
 * <p>Implements the Factory Method pattern from GoF.</p>
 */
public final class CommandFactory {


    /**
     * Prevents instantiation.
     */
    private CommandFactory() {
    }


    /**
     * Creates the command associated with the given button value.
     *
     * @param value button text
     * @return corresponding command
     * @throws IllegalArgumentException
     *         when button value is unsupported
     */
    public static ButtonCommand create(
            String value
    ) {


        if (value.matches("[0-9]")) {

            return new DigitCommand(value);

        }


        return switch (value) {


            case "." ->
                    DecimalCommand.INSTANCE;


            case "+" ->
                    new OperatorCommand(
                            Operator.ADD
                    );


            case "-" ->
                    new OperatorCommand(
                            Operator.SUBTRACT
                    );


            case "*" ->
                    new OperatorCommand(
                            Operator.MULTIPLY
                    );


            case "/" ->
                    new OperatorCommand(
                            Operator.DIVIDE
                    );


            case "×" ->
                    new OperatorCommand(
                            Operator.MULTIPLY
                    );


            case "÷" ->
                    new OperatorCommand(
                            Operator.DIVIDE
                    );


            case "=" ->
                    EqualsCommand.INSTANCE;


            case "C" ->
                    ClearCommand.INSTANCE;


            case "CE" ->
                    ClearEntryCommand.INSTANCE;


            case "⌫" ->
                    BackspaceCommand.INSTANCE;


            case "±" ->
                    ToggleSignalCommand.INSTANCE;


            default ->
                    throw new IllegalArgumentException(
                            "Unsupported command: "
                                    + value
                    );

        };

    }

}