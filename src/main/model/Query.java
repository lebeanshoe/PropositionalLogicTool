package model;

import operations.Proposition;
import operations.TruthTable;

import java.util.List;

// represents a generic query performed by users in the LogicTool app.
public interface Query {
    List<Proposition> getInputs();

    List<TruthTable> getOutputs();

    // EFFECTS: prints a preview of the query input(s)
    void preview();
}
