package operators;

// represents a generic logical boolean operator
public interface Operator {

    // EFFECTS: evaluates the result of the booleans using a logical operator
    boolean evaluate(Boolean bool1, Boolean bool2);

    String toString();
}
