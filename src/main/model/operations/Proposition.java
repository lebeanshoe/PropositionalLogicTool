package model.operations;

import model.operators.Operator;

import java.util.List;

// represents a generic logical proposition that can either be an operation or a simple variable
public interface Proposition {

    // EFFECTS: evaluates the truth assignment of a given proposition
    List<Boolean> evaluate();

    // EFFECTS: returns the final operator of a proposition
    Operator getOperator();

    // EFFECTS: returns a proposition's sub propositions
    List<Proposition> getSubProps();

    // EFFECTS: returns the number of variables in a proposition
    int getNumVar();

    // EFFECTS: converts the proposition into a well formatted string
    String toString();

    // EFFECTS: returns the list of variables of the proposition
    List<Variable> getVars();

    // EFFECTS: returns the order of operations of a proposition, not including the final operation
    List<Proposition> getOOP();
}
