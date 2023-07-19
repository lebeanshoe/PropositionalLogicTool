package operators;

// represents an "and" logical operator
public class And implements Operator {

    @Override
    // EFFECTS: evaluates bool1 "and" bool2
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return bool1 && bool2;
    }

    @Override
    public String toString() {
        return "^";
    }
}
