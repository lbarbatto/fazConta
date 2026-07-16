package com.pororoca.factory;

import com.pororoca.model.Operator;
import com.pororoca.strategy.*;

/**
 * Factory responsible for supplying arithmetic strategies.
 */
public final class OperationFactory {

    /**
     * Prevents instantiation.
     */
    private OperationFactory() {
    }

    /**
     * Returns the strategy associated with an arithmetic operator.
     *
     * @param operator arithmetic operator
     * @return operation strategy
     */
    public static OperationStrategy create(Operator operator) {

        return switch (operator) {

            case ADD -> AdditionStrategy.INSTANCE;

            case SUBTRACT -> SubtractionStrategy.INSTANCE;

            case MULTIPLY -> MultiplicationStrategy.INSTANCE;

            case DIVIDE -> DivisionStrategy.INSTANCE;
        };

    }

}