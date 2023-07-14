package model;

import java.util.List;

/*
length of boolean list depends on # of variables

how to represent truth table in test?
 */

// Represents a propositional logic statement's truth table with a header row
// of a statements variables and operations in order, and a list of boolean
// truth assignments in each column
public class TruthTable extends LogicStatement {

    // EFFECTS: generates a truth table with TODO
    public TruthTable(String logicStatement) {
        super(logicStatement);
        //stub
    }

    public List<Boolean> getTruthValues(Operation operation) {
        return null; //stub TODO
    }
}
