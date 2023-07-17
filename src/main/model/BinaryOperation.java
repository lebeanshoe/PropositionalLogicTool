package model;

import operators.Operator;

import java.util.ArrayList;
import java.util.List;

// represents a binary operation with an operator and two proposition parameters
public class BinaryOperation extends Proposition {

    /*
    main converter from string -> prop logic statement
    - calls BinaryOperation and UnaryOperation constructors
     */
    public BinaryOperation(String statement) {
        //stub
    }

    // EFFECTS: constructs a binary operation with given operation that operates on the firstParam
    //          proposition and secondParam proposition in order
    public BinaryOperation(Proposition firstParam, Operator operation, Proposition secondParam) {
        //stub
    }

    @Override
    public List<Boolean> evaluate() {
        return new ArrayList<>();
    }

    @Override
    // EFFECTS: returns operations as string, with spaces between binary operators and variables,
    //          and parentheses wrapping every proposition that's not a variable
    public String toString() {
        return "";
    }
}
