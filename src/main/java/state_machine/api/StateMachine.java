package state_machine.api;

import state_machine.implementation.Event;
import state_machine.util.StateMachineException;

import java.util.function.Consumer;

/**
 * This is an interface for a custom StateMachine, you can find implementation in StateMachineImpl class
 */
public interface StateMachine {
    /**
     * Set starting state of a StateMachine
     * @param state
     */
    void setStartingState(State state);

    /**
     * Add transition to the list of transitions
     * @param from starting state
     * @param onEvent event on which to change state
     * @param to next state
     */
    void addTransition(State from, Event onEvent, State to);

    /**
     * True to enable forward link checking
     * This disables StateMachine to have null as a valid state (in the implementation, unless you set is in the method @setStartingState specifically)
     * @param flag
     */
    void enableForwardLinkChecking(boolean flag);

    /**
     * Starts a State action and changes the State depending on given event
     * @param event change event
     * @throws StateMachineException if state is illegal or no event found for current state
     */
    void fire(Event event) throws StateMachineException;

    /**
     * Prints transition structure
     * @param printer customizable print
     */
    void printStructure(Consumer<String> printer);
}
