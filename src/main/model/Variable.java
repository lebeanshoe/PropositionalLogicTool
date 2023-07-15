package model;

import java.util.List;

public class Variable extends Proposition {

    // REQUIRES: var is single letter in alphabet but not "v"
    // EFFECTS: constructs a single variable
    public Variable(String var) {
        //stub
    }

    @Override
    // EFFECTS: returns a list of truth assignments whose length is dependent
    //          on how many variables there are and whose order is dependent
    //          on which variable it is
    public List<Boolean> evaluate() {
        return null;
    }
}
