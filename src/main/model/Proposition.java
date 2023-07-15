package model;

import operators.Operator;

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
        return null;
    }

    public List<Proposition> getSubProps() {
        return null;
    }

    public int getNumVar() {
        return 0; //stub
    }


}
