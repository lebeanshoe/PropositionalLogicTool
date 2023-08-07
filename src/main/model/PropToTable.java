package model;

import operations.Proposition;
import operations.TruthTable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a conversion query from proposition to truth table. Contains input proposition and output table.
public class PropToTable implements Query {
    private List<Proposition> input = new ArrayList<>();
    private List<TruthTable> output = new ArrayList<>();

    // EFFECTS: constructs a proposition to truth table conversion query
    public PropToTable(Proposition input, TruthTable output) {
        this.input.add(input);
        this.output.add(output);
    }

    // EFFECTS: returns the input of this conversion
    @Override
    public List<Proposition> getInputs() {
        return this.input;
    }

    // EFFECTS: returns the output of this conversion
    @Override
    public List<TruthTable> getOutputs() {
        return this.output;
    }

    // EFFECTS: returns "Truth Table Conversion"
    @Override
    public String getType() {
        return "Truth Table Conversion";
    }

    // EFFECTS: prints out the input of this query
    @Override
    public void preview() {
        System.out.println("Conversion to truth table: " + this.input.get(0).toString());
    }

    // EFFECTS: writes query to canvas, then writes x and y as its position
    @Override
    public JSONObject toJson(int x, int y) {
        JSONObject json = toJson();
        json.put("XPos", x);
        json.put("YPos", y);
        return json;
    }

    // EFFECTS: writes query to canvas
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("query", "PropToTable");
        json.put("proposition", getInputs().get(0).toString());
        return json;
    }

    // EFFECTS: returns true if this has same input as o
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PropToTable that = (PropToTable) o;
        return Objects.equals(input, that.input);
    }

    // EFFECTS: returns hashCode
    @Override
    public int hashCode() {
        return Objects.hash(input);
    }
}
