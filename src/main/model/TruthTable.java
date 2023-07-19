package model;

import java.util.ArrayList;
import java.util.List;

/*
this is where the truth assignments live

has columns for variables, then operations, then the whole proposition
 */
public class TruthTable {
    private List<Proposition> colHeads = new ArrayList<>();
    private List<List<Boolean>> assigns = new ArrayList<>();

    public TruthTable(Proposition prop) {
        colHeads.addAll(prop.getVars());
        colHeads.addAll(prop.getOOP());
        colHeads.add(prop);

        for (Proposition p : colHeads) {
            assigns.add(p.evaluate());
        }
    }

    public List<Proposition> getColumnHeaders() {
        return this.colHeads;
    }

    public List<List<Boolean>> getAssignments() {
        return this.assigns;
    }
}
