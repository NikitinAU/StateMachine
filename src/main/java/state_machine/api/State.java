package state_machine.api;

import state_machine.util.StateException;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * State with custom action
 * The action is a functional interface that consumes nothing and provides nothing as well
 */
public interface State {
    /**
     * Set action as lambda
     * @param action accepts lambda
     */
    void setAction(Action action);

    /**
     * Executes a given action
     * @throws StateException if no action is defined
     */
    void doAction() throws StateException;
}
