package com.pororoca.factory;

import com.pororoca.command.ButtonCommand;
import com.pororoca.command.DigitCommand;
import com.pororoca.command.OperatorCommand;
import com.pororoca.model.Operator;
import com.pororoca.service.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link CommandFactory}.
 *
 * <p>Validates that button labels are mapped to the correct
 * Command objects and that unknown labels are rejected.</p>
 */
class CommandFactoryTest {

    @Test
    void digitShouldCreateDigitCommand() {
        ButtonCommand command =
                CommandFactory.create("7");

        assertTrue(command instanceof DigitCommand);
    }

    @Test
    void operatorShouldCreateOperatorCommand() {
        ButtonCommand command =
                CommandFactory.create("+");

        assertTrue(command instanceof OperatorCommand);

        Calculator calculator = new Calculator();
        command.execute(calculator);
        calculator.inputDigit("2");
        calculator.calculateResult();
        assertEquals("2", calculator.getDisplayValue());
    }

    @Test
    void unknownLabelShouldThrow() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CommandFactory.create("#")
        );
    }

    @Test
    void commandShouldMutateCalculatorState() {
        Calculator calculator = new Calculator();
        CommandFactory.create("9").execute(calculator);

        assertEquals("9", calculator.getDisplayValue());
    }
}
