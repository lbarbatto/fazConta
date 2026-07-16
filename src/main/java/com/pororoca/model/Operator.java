package com.pororoca.model;

/**
 * Represents all arithmetic operators supported by the calculator.
 *
 * <p>This enumeration centralizes the symbolic representation
 * of arithmetic operations, eliminating the use of magic strings
 * throughout the application.</p>
 */
public enum Operator {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("×"),
    DIVIDE("÷");

    private final String symbol;

    Operator(String symbol){
        this.symbol = symbol;
    }

    /**
     * Returns the operator symbol.
     *
     * @return operator symbol
     */
    public String getSymbol(){
        return symbol;
    }

}
