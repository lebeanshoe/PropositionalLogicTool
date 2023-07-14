package model;

import java.util.ArrayList;

/*
should be able to parse ~a, a^b

have operation take string arguments, and if param is not single char,
recursively call on it?
 */

// Represents a single propositional logic operation with
// one to two variables and zero to one operator
public class Operation {

    // REQUIRES: operator is a valid logic operator,
    //           firstParam and secondParam are either
    // EFFECTS: creates an operation
    public Operation(String firstParam, String operator, String secondParam,
                     Operation firstParamOp, Operation secondParamOp) {
        //stub TODO
    }

    // EFFECTS: returns the result of the
    public ArrayList<Boolean> result() {
        return null; //stub TODO
    }
}
