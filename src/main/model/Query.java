package model;

import operations.Proposition;
import operations.TruthTable;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// represents a generic query performed by users in the LogicTool app.
public interface Query extends Writable {
    List<Proposition> getInputs();

    List<TruthTable> getOutputs();

    // EFFECTS: prints a preview of the query input(s)
    void preview();

    JSONObject toJson(int x, int y);
}
