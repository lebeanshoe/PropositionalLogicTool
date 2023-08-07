package model;

import operations.Proposition;
import operations.TruthTable;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// represents a generic query performed by users in the LogicTool app.
public interface Query extends Writable {

    // EFFECTS: returns the inputs of a query
    List<Proposition> getInputs();

    // EFFECTS: returns the outputs of a query
    List<TruthTable> getOutputs();

    // EFFECTS: return the type of the query as a string
    String getType();

    // EFFECTS: prints a preview of the query input(s)
    void preview();

    // EFFECTS: returns this as a Json object with coordinates x,y
    JSONObject toJson(int x, int y);
}
