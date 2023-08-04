package operations;

import operators.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// represents a single letter variable
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

    // REQUIRES: numVars >= 1
    // MODIFIES: this
    // EFFECTS: changes the total number of variables to numVars
    public void setNumVars(int numVars) {
        this.numVars = numVars;
    }

    // EFFECTS: returns a list of truth assignments whose length is dependent
    //          on how many variables there are and whose order is dependent
    //          on which variable it is
    @Override
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

    @Override
    // EFFECTS: returns variable as a single letter
    public String toString() {
        return this.var;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Variable variable = (Variable) o;
        return numVars == variable.numVars && varNum == variable.varNum && Objects.equals(var, variable.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, numVars, varNum);
    }

    @Override
    // EFFECTS: returns null as this is a required method for BinaryOperation that must exist
    //          in Proposition, but for which Variable has no use
    public List<Variable> getVars() {
        return null;
    }

    @Override
    // EFFECTS: returns null as this is a required method for BinaryOperation that must exist
    //          in Proposition, but for which Variable has no use
    public List<Proposition> getOOP() {
        return null;
    }

    @Override
    // EFFECTS: returns null as this is a required method for BinaryOperation that must exist
    //          in Proposition, but for which Variable has no use
    public Operator getOperator() {
        return null;
    }

    @Override
    // EFFECTS: returns null as this is a required method for BinaryOperation that must exist
    //          in Proposition, but for which Variable has no use
    public List<Proposition> getSubProps() {
        return null;
    }

    @Override
    // EFFECTS: returns 0 as this is a required method for BinaryOperation that must exist
    //          in Proposition, but for which Variable has no use
    public int getNumVar() {
        return 0;
    }
}
