package model;

import operations.Proposition;
import operations.TruthTable;

import java.util.ArrayList;
import java.util.List;

// Represents a comparison query between two propositions. Contains input propositions and output truth tables.
public class Comparison implements Query {
    private List<Proposition> inputs = new ArrayList<>();
    private List<TruthTable> outputs = new ArrayList<>();

    // EFFECTS: constructs
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
    // EFFECTS: prints out the inputs of this query
    public void preview() {
        System.out.println("Comparison between " + this.inputs.get(0).toString()
                + " and " + this.inputs.get(1).toString());
    }
}
