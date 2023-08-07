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

    String getType();

    // EFFECTS: prints a preview of the query input(s)
    void preview();

    // EFFECTS: returns this as a Json object with coordinates x,y
    JSONObject toJson(int x, int y);
}
