package operations;

import exceptions.InvalidStatementException;
import operators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static model.StatementSplitter.balancedGroups;

// represents a binary operation with an operator, up to two sub propositions, a list of variables used,
// and the order of operations of this operation
public class BinaryOperation implements Proposition {
    private Operator operator;
    private List<Proposition> subProps = new ArrayList<>();
    private List<Variable> vars;
    private List<Proposition> operations;

    // EFFECTS: constructs a binary operation out of the string:
    //          - each variable is only counted once
    //          - stores the order of operations in this.operations, up to but not including the root
    //            proposition
    public BinaryOperation(String statement, List<Variable> vars, List<Proposition> operations)
            throws InvalidStatementException {
        if (!isValidStatement(statement)) {
            throw new InvalidStatementException();
        }
        this.vars = vars;
        this.operations = operations;
        List<String> initialSplit = balancedGroups(statement);
        int elements = initialSplit.size();
        if (elements == 2) {
            buildUnaryOperation(initialSplit, vars, operations);
        } else {
            buildBinaryOperation(initialSplit, vars, operations, elements);
        }
    }

    // REQUIRES: operator is valid syntax for an operator object
    // EFFECTS: returns operator matching string
    public Operator chooseOperator(String operator) {
        if (operator.equals("^")) {
            return new And();
        } else if (operator.equals("<->") | operator.equals("<=>")) {
            return new Biconditional();
        } else if (operator.equals("->") | operator.equals("=>")) {
            return new Implies();
        } else if (operator.equals("v")) {
            return new Or();
        } else {
            return new Xor();
        }
    }

    // EFFECTS: returns false if given str is not a valid Proposition, otherwise true
    private boolean isValidStatement(String str) {
        if (str.length() == 0) {
            return false;
        } else {
            List<String> initialSplit = balancedGroups(str);
            int size = initialSplit.size();
            if (size == 1) {
                return false;
            } else if (size == 2) {
                int n = initialSplit.get(1).length();
                if (n == 1) { // second element is single variable
                    return initialSplit.get(0).equals("~");
                }
                return initialSplit.get(0).equals("~") && isValidStatement(initialSplit.get(1).substring(1, n));
            } else {
                StringBuilder operator = new StringBuilder();

                // Builds operator string
                for (int i = 1; i < size - 1; i++) {
                    if (!initialSplit.get(i).equals(" ")) {
                        operator.append(initialSplit.get(i));
                    }
                }
                return isValidOperator(operator.toString());
            }
        }
    }

    // EFFECTS: returns false if given str is not a valid Operator, otherwise true
    private boolean isValidOperator(String operator) {
        return operator.equals("^") | operator.equals("<->") | operator.equals("<=>") | operator.equals("->")
                | operator.equals("=>") | operator.equals("~") | operator.equals("v") | operator.equals("xor");
    }

    // MODIFIES: this
    // EFFECTS: builds appropriate unary operation out of inputs
    private void buildUnaryOperation(List<String> split, List<Variable> vars, List<Proposition> operations)
            throws InvalidStatementException {
        this.operator = new Not();
        String subProp = split.get(1);
        subPropSetter(subProp, vars, operations);
    }

    // MODIFIES: this
    // EFFECTS: builds appropriate binary operation out of inputs
    private void buildBinaryOperation(List<String> split, List<Variable> vars, List<Proposition> operations,
                                      int elements) throws InvalidStatementException {
        StringBuilder operator = new StringBuilder();
        String subProp1 = split.get(0);
        String subProp2 = split.get(elements - 1);

        // Builds operator string
        for (int i = 1; i < elements - 1; i++) {
            if (!split.get(i).equals(" ")) {
                operator.append(split.get(i));
            }
        }
        this.operator = chooseOperator(operator.toString());
        subPropSetter(subProp1, vars, operations);
        subPropSetter(subProp2, vars, operations);
    }

    // EFFECTS: returns a list of booleans representing an operation's truth assignment
    @Override
    public List<Boolean> evaluate() {
        List<Boolean> assignment = new ArrayList<>();
        int n = (int)Math.pow(2, this.vars.size());
        int numSubs = this.subProps.size();
        for (int i = 0; i < n; i++) {
            if (numSubs == 1) {
                Proposition sub = this.subProps.get(0);
                assignment.add(this.operator.evaluate(sub.evaluate().get(i), false));
            } else {
                Proposition sub1 = this.subProps.get(0);
                Proposition sub2 = this.subProps.get(1);
                assignment.add(this.operator.evaluate(sub1.evaluate().get(i), sub2.evaluate().get(i)));
            }
        }
        return assignment;
    }

    // EFFECTS: returns operations as string, with spaces between binary operators and variables,
    //          and parentheses wrapping every proposition that's not a variable
    @Override
    public String toString() {
        if (this.subProps.size() == 1) {
            if (subProps.get(0).getClass() == Variable.class) {
                return this.operator.toString() + this.subProps.get(0).toString();
            } else {
                return this.operator.toString() + "(" + this.subProps.get(0).toString() + ")";
            }
        } else {
            Proposition lhs = this.subProps.get(0);
            Proposition rhs = this.subProps.get(1);
            if (lhs.getClass() == Variable.class && rhs.getClass() == Variable.class) {
                return lhs + " " + this.operator.toString() + " " + rhs;
            } else if (lhs.getClass() != Variable.class && rhs.getClass() == Variable.class) {
                return "(" + lhs + ")" + " " + this.operator.toString() + " " + rhs;
            } else if (lhs.getClass() == Variable.class) {
                return lhs + " " + this.operator.toString() + " " + "(" + rhs + ")";
            } else {
                return "(" + lhs + ")" + " " + this.operator.toString() + " " + "(" + rhs + ")";
            }
        }
    }

    // MODIFIES: this, vars, operations
    // EFFECTS: takes input statement and determines whether to construct a Variable or BinaryOperation
    //          out of it. If it constructs a Variable, adds it to subProps, and adds it to vars if not
    //          already in it, otherwise do nothing. If it constructs a BinaryOperation, adds it to
    //          subVars.
    public void subPropSetter(String statement, List<Variable> vars, List<Proposition> operations)
            throws InvalidStatementException {
        if (statement.length() == 1) {
            List<String> varsAsString = new ArrayList<>();
            for (Variable var : this.vars) {
                varsAsString.add(var.toString());
            }
            if (!varsAsString.contains(statement)) {
                Variable localVar = new Variable(statement, getNumVar());
                this.subProps.add(localVar);
                this.vars.add(localVar);
                for (Variable var : this.vars) {
                    var.setNumVars(getNumVar());
                }
            } else {
                int varPos = varsAsString.indexOf(statement);
                this.subProps.add(this.vars.get(varPos));
            }
        } else {
            Proposition localBinOp =
                    new BinaryOperation(statement.substring(1, statement.length() - 1), vars, operations);
            this.subProps.add(localBinOp);
            this.operations.add(localBinOp);
        }
    }

    @Override
    public List<Variable> getVars() {
        return this.vars;
    }

    @Override
    public List<Proposition> getSubProps() {
        return this.subProps;
    }

    @Override
    public int getNumVar() {
        return this.vars.size();
    }

    @Override
    public Operator getOperator() {
        return this.operator;
    }

    @Override
    public List<Proposition> getOOP() {
        return this.operations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryOperation that = (BinaryOperation) o;
        return Objects.equals(toString(), that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }
}
