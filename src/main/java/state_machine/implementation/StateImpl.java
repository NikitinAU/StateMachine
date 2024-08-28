package state_machine.implementation;

import state_machine.api.Action;
import state_machine.api.State;
import state_machine.util.StateException;

import java.util.Objects;
import java.util.function.Function;

public class StateImpl implements State {

    private String name;
    private Action action;
    public StateImpl(String name) {
        this.name = name;
    }

    public StateImpl(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public void doAction() throws StateException {
        if (action == null){
            throw new StateException("NO ACTION GIVEN!");
        }
        action.execute();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateImpl state = (StateImpl) o;
        return Objects.equals(name, state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                '}';
    }
}
