package model.operators;

// represents an implication logical operator
public class Implies implements Operator {

    @Override
    // EFFECTS: evaluates bool1 "implies" bool2
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return !bool1 || bool2;
    }

    @Override
    public String toString() {
        return "->";
    }
}