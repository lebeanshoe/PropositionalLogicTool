package operations;

import java.util.ArrayList;
import java.util.List;

// represents a truth table with columns headers and truth assignments
public class TruthTable {
    private List<Proposition> colHeads = new ArrayList<>();
    private List<List<Boolean>> assigns = new ArrayList<>();

    // EFFECTS: constructs a truth table from the input proposition
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
