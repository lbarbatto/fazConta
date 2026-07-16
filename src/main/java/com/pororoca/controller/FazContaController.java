package com.pororoca.controller;

import com.pororoca.command.ButtonCommand;
import com.pororoca.factory.CommandFactory;
import com.pororoca.service.Calculator;
import com.pororoca.util.CalculatorLogger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.logging.Logger;

/**
 * JavaFX controller responsible only for coordinating
 * UI events with calculator commands.
 *
 * <p>This controller follows the Thin Controller principle,
 * delegating all business logic to the service layer.</p>
 */
public class FazContaController {

    /**
     * Logger for monitoring UI interactions.
     */
    private static final Logger LOGGER =
            CalculatorLogger.forClass(
                    FazContaController.class
            );

    /**
     * Calculator display component.
     */
    @FXML
    private TextField display;

    /**
     * Calculator service.
     */
    private final Calculator calculator =
            new Calculator();

    /**
     * Initializes controller state.
     */
    @FXML
    public void initialize() {

        display.setText(
                calculator.getDisplayValue()
        );
    }

    /**
     * Handles all calculator button actions.
     *
     * <p>The controller does not interpret the button.
     * It delegates command creation to CommandFactory.</p>
     *
     * @param event JavaFX action event
     */
    @FXML
    private void handleButton(
            ActionEvent event
    ) {
        Button button =
                (Button) event.getSource();

        handleButton(button.getText());
    }

    /**
     * Handles a calculator action from any source (button or
     * keyboard) by its textual value.
     *
     * @param value button/key text
     */
    public void handleButton(
            String value
    ) {
        LOGGER.fine("Button pressed: " + value);

        ButtonCommand command =
                CommandFactory.create(value);

        command.execute(calculator);

        display.setText(
                calculator.getDisplayValue()
        );
    }
}