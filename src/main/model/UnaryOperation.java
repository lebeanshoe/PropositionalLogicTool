package model;

import operators.Operator;

import java.util.ArrayList;
import java.util.List;

// represents a single unary operation with an operator and a proposition parameter
public class UnaryOperation extends Proposition {

    // EFFECTS: constructs a single unary operation with one operator
    //          and one parameter
    public UnaryOperation(Operator operator, Proposition firstParam) {
        //stub
    }


    @Override
    // REQUIRES: firstParam has an existing truth assignment
    // EFFECTS: returns the opposite of the parameter's truth
    //          assignment
    public List<Boolean> evaluate() {
        return new ArrayList<>();
    }

    @Override
    // EFFECTS: returns operator concatenated to sub proposition without space.
    //          Wrapped in parentheses if sub prop is not a variable, otherwise
    //          unwrapped
    public String toString() {
        return "";
    }
}
