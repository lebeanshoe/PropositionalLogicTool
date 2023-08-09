package model.operators;

// represents a "not" logical operator
public class Not implements Operator {

    @Override
    // EFFECTS: evaluates "not" bool1
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return !bool1;
    }

    @Override
    public String toString() {
        return "~";
    }
}
