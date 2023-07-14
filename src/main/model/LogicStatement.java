package model;

import java.util.List;

// Represents a propositional logic statement with a list of variables
// and a list of operations in order
public class LogicStatement {

    // REQUIRES: Input string is a valid propositional logic statement
    // EFFECTS: If input string is a valid propositional logic statement,
    //          constructs a propositional logic statement using
    //          input string. Otherwise, ... TODO
    public LogicStatement(String logicStatement) {
        // stub TODO
    }

    // EFFECTS: Converts propositional logic statement to a truth table
    //          with variables and operations in order as column headers
    public TruthTable toTruthTable() {
        return null;
    }

    // Getters
    public List<String> getVariables() {
        return null; //stub TODO
    }

    public List<Operation> getOperations() {
        return null; //stub TODO
    }

}
