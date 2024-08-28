package state_machine;

import state_machine.api.State;
import state_machine.api.StateMachine;
import state_machine.implementation.Event;
import state_machine.implementation.StateImpl;
import state_machine.implementation.StateMachineImpl;

public class Main {
    public static void main(String[] args) {

        State one = new StateImpl("1", ()-> System.out.println("1!"));
        State two = new StateImpl("2", ()-> System.out.println("2!"));
        State three = new StateImpl("3", ()-> System.out.println("3!"));
        State four = new StateImpl("4", ()-> System.out.println("4!"));
        State five = new StateImpl("5", ()-> System.out.println("5!"));
        State six = new StateImpl("6", ()-> System.out.println("6!"));
        State seven = new StateImpl("7", ()-> System.out.println("7!"));
        State eight = new StateImpl("8", ()-> System.out.println("8!"));
        Event left = new Event("left");
        Event right = new Event("right");
        StateMachine sm = new StateMachineImpl(one, false);

        sm.addTransition(one, left, two);
        sm.addTransition(two, left, three);
        sm.addTransition(three, left, two);
        sm.addTransition(two, right, four);
        sm.addTransition(four, left, five);
        sm.addTransition(five, left, two);
        sm.addTransition(four, right ,six);
        sm.addTransition(six, right ,seven);
        sm.addTransition(seven, right ,eight);
        sm.addTransition(one, right ,seven);
        sm.addTransition(eight, left, three);


        sm.printStructure(System.out::println);
    }
}