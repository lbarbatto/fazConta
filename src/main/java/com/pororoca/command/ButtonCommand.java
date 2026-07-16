package com.pororoca.command;

import com.pororoca.service.Calculator;

/**
 * Defines a command executed by calculator controls.
 *
 * <p>Implementation of the Command design pattern
 * described by GoF.</p>
 */
@FunctionalInterface
public interface ButtonCommand {


    /**
     * Executes the command.
     *
     * @param calculator calculator receiver
     */
    void execute(Calculator calculator);

}