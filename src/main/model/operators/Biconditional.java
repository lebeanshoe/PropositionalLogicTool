package model.operators;

// represents a biconditional logical operator
public class Biconditional implements Operator {

    @Override
    // EFFECTS: evaluates bool1 "iff" bool2
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return bool1 == bool2;
    }

    @Override
    public String toString() {
        return "<->";
    }
}