package operators;

// represents an "or" logical operator
public class Or implements Operator {

    @Override
    // EFFECTS: evaluates bool1 "or" bool2
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return bool1 || bool2;
    }

    @Override
    public String toString() {
        return "v";
    }
}