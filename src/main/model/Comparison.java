package model;

import operations.Proposition;
import operations.TruthTable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a comparison query between two propositions. Contains input propositions and output truth tables.
public class Comparison implements Query {
    private List<Proposition> inputs = new ArrayList<>();
    private List<TruthTable> outputs = new ArrayList<>();

    // EFFECTS: constructs a comparison query
    public Comparison(Proposition input1, Proposition input2, TruthTable output1, TruthTable output2) {
        this.inputs.add(input1);
        this.inputs.add(input2);
        this.outputs.add(output1);
        this.outputs.add(output2);
    }

    @Override
    public List<Proposition> getInputs() {
        return this.inputs;
    }

    @Override
    public List<TruthTable> getOutputs() {
        return this.outputs;
    }

    @Override
    public String getType() {
        return "Comparison";
    }

    @Override
    // EFFECTS: prints out the inputs of this query
    public void preview() {
        System.out.println("Comparison between " + this.inputs.get(0).toString()
                + " and " + this.inputs.get(1).toString());
    }

    @Override
    public JSONObject toJson(int x, int y) {
        JSONObject json = toJson();
        json.put("XPos", x);
        json.put("YPos", y);
        return json;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("query", "Comparison");
        json.put("prop1", getInputs().get(0).toString());
        json.put("prop2", getInputs().get(1).toString());
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comparison that = (Comparison) o;
        return Objects.equals(inputs, that.inputs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputs);
    }
}
