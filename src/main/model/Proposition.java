package model;

import operators.*;

import java.util.List;

/*

 */
public interface Proposition {
    List<Boolean> evaluate();

    Operator getOperator();

    List<Proposition> getSubProps();

    int getNumVar();

    String toString();
}
