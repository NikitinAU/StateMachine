package state_machine.util;

import java.util.Objects;

public class Pair <O1, O2>{
    private O1 first;
    private O2 second;

    public Pair(O1 first, O2 second) {
        this.first = first;
        this.second = second;
    }

    public O1 getFirst() {
        return first;
    }

    public void setFirst(O1 first) {
        this.first = first;
    }

    public O2 getSecond() {
        return second;
    }

    public void setSecond(O2 second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
