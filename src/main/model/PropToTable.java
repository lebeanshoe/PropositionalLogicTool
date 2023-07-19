package model;

import operations.Proposition;
import operations.TruthTable;

import java.util.ArrayList;
import java.util.List;

// Represents a conversion query from proposition to truth table. Contains input proposition and output table.
public class PropToTable implements Query {
    private List<Proposition> input = new ArrayList<>();
    private List<TruthTable> output = new ArrayList<>();

    // EFFECTS: constructs
    public PropToTable(Proposition input, TruthTable output) {
        this.input.add(input);
        this.output.add(output);
    }

    @Override
    public List<Proposition> getInputs() {
        return this.input;
    }

    @Override
    public List<TruthTable> getOutputs() {
        return this.output;
    }

    @Override
    // EFFECTS: prints out the input of this query
    public void preview() {
        System.out.println("Conversion to truth table: " + this.input.get(0).toString());
    }
}
