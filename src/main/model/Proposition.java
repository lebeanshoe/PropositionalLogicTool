package model;

import operators.Biconditional;
import operators.*;

import java.util.ArrayList;
import java.util.List;

/*

 */
public abstract class Proposition {
    private int numVar;
    private List<Variable> vars;

    public abstract List<Boolean> evaluate();

    public void incrementNumVar() {
        // stub
    }

    public Operator getOperator() {
        Operator dummy = new Not();
        return dummy; // stub TODO
    }

    public List<Proposition> getSubProps() {
        return new ArrayList<>(); //stub
    }

    public int getNumVar() {
        return 0; //stub
    }

    public abstract String toString();
}
