package model.operations;

import java.util.*;

// represents a truth table with columns headers and truth assignments
public class TruthTable {
    private List<Proposition> colHeads;
    private List<List<Boolean>> assigns;

    // EFFECTS: constructs a truth table from the input proposition
    public TruthTable(Proposition prop) {
        colHeads = new ArrayList<>();
        assigns = new ArrayList<>();
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

    // EFFECTS: returns true if this has same column headers as o
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TruthTable that = (TruthTable) o;
        return Objects.equals(colHeads, that.colHeads);
    }

    // EFFECTS: returns hashCode
    @Override
    public int hashCode() {
        return Objects.hash(colHeads);
    }
}
