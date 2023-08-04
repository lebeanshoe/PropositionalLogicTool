package operations;

import java.util.*;

// represents a truth table with columns headers and truth assignments
public class TruthTable {
//    private Map<Proposition, List<Boolean>> columns;
//    private Proposition prop;
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

        // HashMap version
//        columns = new LinkedHashMap<>();
//        this.prop = prop;
//        for (Variable v : prop.getVars()) {
//            columns.put(v, v.evaluate());
//        }
//        for (Proposition p : prop.getOOP()) {
//            columns.put(p, p.evaluate());
//        }
    }

    public List<Proposition> getColumnHeaders() {
        return this.colHeads;
    }

    public List<List<Boolean>> getAssignments() {
        return this.assigns;
    }

//    public List<Boolean> getHashAssign(Proposition prop) {
//        return this.columns.get(prop);
//    }

//    public Proposition getProp() {
//        return this.prop;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TruthTable that = (TruthTable) o;

        return Objects.equals(this.colHeads, that.colHeads);
    }

    @Override
    public int hashCode() {
        return this.colHeads != null ? this.colHeads.hashCode() : 0;
    }
}
