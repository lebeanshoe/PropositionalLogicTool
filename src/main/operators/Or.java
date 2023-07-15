package operators;

public class Or implements Operator {

    @Override
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        return bool1 || bool2;
    }
}
