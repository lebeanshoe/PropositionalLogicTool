package operators;

public class Not implements Operator {
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return !bool1;
    }
}
