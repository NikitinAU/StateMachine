package state_machine.api;

/**
 * Functional interface that takes nothing in and spits nothing out.
 * Purely for impure functions
 *
 * If you have any idea on how to use ANY functional interface as a field, please let me know.
 */
@FunctionalInterface
public interface Action {
    /**
     * Executes impure action (ex. reading from the file and writing to the file)
     */
    void execute();
}
