package operations;

import operators.Operator;

import java.util.List;

// represents a generic logical proposition that can either be an operation or a simple variable
public interface Proposition {

    // EFFECTS: evaluates the truth assignment of a given proposition
    List<Boolean> evaluate();

    Operator getOperator();

    List<Proposition> getSubProps();

    int getNumVar();

    String toString();

    List<Variable> getVars();

    // EFFECTS: returns the order of operations of a proposition.
    List<Proposition> getOOP();
}
