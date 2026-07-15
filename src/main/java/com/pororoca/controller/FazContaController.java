package com.pororoca.controller;

import com.pororoca.model.FazConta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller responsible for managing all user interactions
 * in the FazConta calculator interface.
 *
 * <p>This class implements a stateful input-processing engine
 * that translates UI events into calculator operations.</p>
 *
 * <p>Responsibilities:
 * <ul>
 *     <li>Handle numeric and operator input from UI buttons</li>
 *     <li>Maintain calculation state (accumulator, pending operator)</li>
 *     <li>Coordinate execution with the calculation engine (FazConta)</li>
 *     <li>Update the display accordingly</li>
 * </ul>
 * </p>
 *
 * <p>This class acts as the bridge between the JavaFX view (FXML)
 * and the domain logic layer (model).</p>
 */
public class FazContaController {

    @FXML
    private TextField display;

    /** Stores the accumulated value from previous operations */
    private double accumulator = 0;

    /** Stores the pending arithmetic operator (+, -, *, /) */
    private String pendingOperator = "";

    /** Indicates whether the next digit starts a new number input */
    private boolean startNewNumber = true;

    /**
     * Initializes the controller after FXML loading.
     *
     * <p>Sets the default display state.</p>
     */
    @FXML
    public void initialize() {
        display.setText("0");
    }

    /**
     * Central event handler for all calculator button inputs.
     *
     * <p>Routes input based on button text:
     * <ul>
     *     <li>Digits → numeric input handling</li>
     *     <li>Operators → arithmetic operation handling</li>
     *     <li>Special commands → control operations</li>
     * </ul>
     * </p>
     *
     * @param event JavaFX action event triggered by button click
     */
    @FXML
    private void handleButton(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String value = btn.getText();

        if (value.matches("[0-9]")) {
            handleDigit(value);
            return;
        }

        switch (value) {
            case "." -> handleDecimal();
            case "+", "-", "*", "/" -> handleOperator(value);
            case "=" -> handleEquals();
            case "C" -> clearAll();
            case "CE" -> clearEntry();
            case "⌫" -> backspace();
            case "±" -> toggleSign();
        }
    }

    /**
     * Processes numeric digit input.
     *
     * @param digit numeric character pressed by user
     */
    private void handleDigit(String digit) {
        if (startNewNumber || display.getText().equals("0")) {
            display.setText(digit);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + digit);
        }
    }

    /**
     * Handles decimal point input ensuring valid numeric format.
     */
    private void handleDecimal() {
        if (startNewNumber) {
            display.setText("0.");
            startNewNumber = false;
            return;
        }

        if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    /**
     * Processes arithmetic operator input.
     *
     * <p>If a previous operation is pending, it is executed
     * before storing the new operator.</p>
     *
     * @param op arithmetic operator (+, -, *, /)
     */
    private void handleOperator(String op) {
        double current = Double.parseDouble(display.getText());

        if (!pendingOperator.isEmpty()) {
            accumulator = FazConta.calculate(accumulator, pendingOperator, current);
            display.setText(format(accumulator));
        } else {
            accumulator = current;
        }

        pendingOperator = op;
        startNewNumber = true;
    }

    /**
     * Executes the current pending calculation.
     */
    private void handleEquals() {
        if (pendingOperator.isEmpty()) return;

        double current = Double.parseDouble(display.getText());

        accumulator = FazConta.calculate(
                accumulator,
                pendingOperator,
                current
        );

        display.setText(format(accumulator));

        pendingOperator = "";
        startNewNumber = true;
    }

    /**
     * Resets the entire calculator state.
     */
    private void clearAll() {
        accumulator = 0;
        pendingOperator = "";
        display.setText("0");
        startNewNumber = true;
    }

    /**
     * Clears only the current input without affecting stored state.
     */
    private void clearEntry() {
        display.setText("0");
        startNewNumber = true;
    }

    /**
     * Removes the last character from the current input.
     */
    private void backspace() {
        if (startNewNumber) return;

        String text = display.getText();

        if (text.length() <= 1) {
            display.setText("0");
            startNewNumber = true;
        } else {
            display.setText(text.substring(0, text.length() - 1));
        }
    }

    /**
     * Toggles the sign of the current numeric value.
     */
    private void toggleSign() {
        double value = Double.parseDouble(display.getText());
        value *= -1;
        display.setText(format(value));
    }

    /**
     * Formats numeric output to remove unnecessary decimal places.
     *
     * <p>Example:
     * <ul>
     *     <li>10.0 → "10"</li>
     *     <li>10.5 → "10.5"</li>
     * </ul>
     * </p>
     *
     * @param value numeric value to format
     * @return formatted string representation
     */
    private String format(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}