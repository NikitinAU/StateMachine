package state_machine.implementation;

import state_machine.util.Pair;
import state_machine.util.StateMachineException;
import state_machine.api.State;
import state_machine.api.StateMachine;

import java.util.*;
import java.util.function.Consumer;

public class StateMachineImpl implements StateMachine {

    private Map<State, Map<Event, State>> stateMap;
    private State initialState;
    private State currentState;
    private boolean isForwardLinkChecking = true;
    public StateMachineImpl(){
        stateMap = new HashMap<>();
    }

    public StateMachineImpl(State startingState, boolean enableForwardLinkChecking) {
        this.initialState = startingState;
        this.isForwardLinkChecking = enableForwardLinkChecking;
        stateMap = new HashMap<>();
        currentState = initialState;
    }

    @Override
    public void setStartingState(State state) {
        initialState = state;
        currentState = initialState;
    }

    @Override
    public void addTransition(State from, Event onEvent, State to) {
        if (!stateMap.containsKey(from)){
            stateMap.put(from, new HashMap<>());
        }
        stateMap.get(from).put(onEvent, to);
        if (!stateMap.containsKey(to)){
            stateMap.put(to, new HashMap<>());
        }
    }

    @Override
    public void enableForwardLinkChecking(boolean flag) {
        isForwardLinkChecking = flag;
    }

    @Override
    public void fire(Event event) {
        if (isForwardLinkChecking) {
            if (stateMap.containsKey(currentState)) {
                if (!stateMap.get(currentState).containsKey(event)) {
                    throw new StateMachineException("NO TRANSITION FROM CURRENT STATE: " + currentState + " WITH EVENT: " + event);
                }
            } else {
                throw new StateMachineException("NO TRANSITION FROM CURRENT STATE: " + currentState);
            }
        }
        else{
            if (currentState == null){
                throw new StateMachineException("CURRENT STATE IS NULL");
            }
        }
        currentState.doAction();
        currentState = stateMap.get(currentState).get(event);
    }
    public void printStructure(Consumer<String> printer){
        printer.accept(getStructureString());
    }
    private String getStructureString(){
        StringBuilder sb = new StringBuilder();
        Set<Pair<State, State>> statePairSet = new HashSet<>();
        Pair<State, State> p = new Pair<>(null, initialState);
        statePairSet.add(p);
        Stack<State> stack = new Stack<>();
        stack.push(initialState);
        while (!stack.isEmpty()) {
            StringBuilder sb_buffer = new StringBuilder();
            State item = stack.pop();
            sb_buffer.append(item).append(" --> ");
            var entries = stateMap.get(item).entrySet();
            for (var entry : entries) {
                p = new Pair<>(item, entry.getValue());
                if (!statePairSet.contains(p)) {
                    stack.push(entry.getValue());
                    statePairSet.add(p);
                    sb_buffer.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
                }
            }
            if (sb_buffer.lastIndexOf("Event") != -1){
                sb.append(sb_buffer).append("\n");
            }
        }
        return sb.toString();
    }
}
