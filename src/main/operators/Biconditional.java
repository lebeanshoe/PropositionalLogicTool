package operators;

public class Biconditional implements Operator {
    @Override
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return bool1 == bool2;
    }

    @Override
    public String toString() {
        return "<->";
    }
}