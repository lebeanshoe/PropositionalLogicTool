package model;

import java.util.ArrayList;
import java.util.List;

public class PropToTable implements Query {
    private List<Proposition> input = new ArrayList<>();
    private List<TruthTable> output = new ArrayList<>();

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
    public void preview() {
        System.out.println("Conversion to truth table: " + this.input.get(0).toString());
    }
}
