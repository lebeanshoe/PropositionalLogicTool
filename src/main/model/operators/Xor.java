package model.operators;

// represents an "xor" logical operator
public class Xor implements Operator {

    @Override
    // EFFECTS: evaluates bool1 "xor" bool2
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return !(bool1 == bool2);
    }

    @Override
    public String toString() {
        return "xor";
    }
}
