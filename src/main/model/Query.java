package model;

import java.util.List;

public interface Query {
    List<Proposition> getInputs();

    List<TruthTable> getOutputs();

    void preview();
}
