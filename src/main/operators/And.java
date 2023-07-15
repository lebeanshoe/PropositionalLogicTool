package operators;

public class And implements Operator {

    @Override
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return bool1 && bool2;
    }
}
