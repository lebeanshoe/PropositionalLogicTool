package operators;

public class Implies implements Operator {
    @Override
    public boolean evaluate(Boolean bool1, Boolean bool2) {
        if ((bool1 == true) && (bool2 == false)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "->";
    }
}