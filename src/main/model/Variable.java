package model;

import operators.Operator;

import java.util.ArrayList;
import java.util.List;

public class Variable implements Proposition {
    private String var;
    private int numVars;
    private int varNum;

    // REQUIRES: var is single letter in alphabet but not "v"
    // EFFECTS: constructs a single variable
    public Variable(String var, int varNum) {
        this.var = var;
        this.varNum = varNum;
    }

    public void setNumVars(int numVars) {
        this.numVars = numVars;
    }

    @Override
    // EFFECTS: returns a list of truth assignments whose length is dependent
    //          on how many variables there are and whose order is dependent
    //          on which variable it is
    /*
    varNum = 0, alternate F/T at n/2 intervals
    varNum = 1, alternate F/T at n/4 intervals
    alternate F/T at n / (2 ^ (varNum + 1)) intervals
     */
    public List<Boolean> evaluate() {
        List<Boolean> assignment = new ArrayList<>();
        int n = (int)Math.pow(2, this.numVars);
        int interval = n / (int)Math.pow(2, (this.varNum + 1));
        boolean currState = false;
        int k = 0;
        for (int i = 0; i < n; i++) {
            assignment.add(currState);
            if (k + 1 == interval) {
                currState = !currState;
                k = 0;
            } else {
                k++;
            }
        }
        return assignment;
    }
    /*
    numVars = 4
    n = 16

    8   4   2   1   k

    0   0   0   0
    0   0   0   1
    0   0   1   0
    0   0   1   1
    0   1   0   0
    0   1   0   1
    0   1   1   0
    0   1   1   1
    1   0   0   0
    1   0   0   1
    1   0   1   0
    1   0   1   1
    1   1   0   0
    1   1   0   1
    1   1   1   0
    1   1   1   1
     */

    @Override
    public Operator getOperator() {
        return null;
    }

    @Override
    public List<Proposition> getSubProps() {
        return null;
    }

    @Override
    public int getNumVar() {
        return 0;
    }

    @Override
    // EFFECTS: returns variable as a single letter
    public String toString() {
        return this.var;
    }
}
