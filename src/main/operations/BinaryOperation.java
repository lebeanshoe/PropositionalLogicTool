package operations;

import operators.*;

import java.util.ArrayList;
import java.util.List;

import static model.StatementSplitter.balancedGroups;

// represents a binary operation with an operator, up to two sub propositions, a list of variables used,
// and the order of operations of this operation
public class BinaryOperation implements Proposition {
    private Operator operator;
    private List<Proposition> subProps = new ArrayList<>();
    private List<Variable> vars;
    private List<Proposition> operations;

    // REQUIRES: There must be no parentheses that encapsulate the entire statement.
    //           Must be a valid statement.
    // EFFECTS: constructs a binary operation out of the string:
    //          - each variable is only counted once
    //          - stores the order of operations in this.operations, up to but not including the root
    //            proposition
    public BinaryOperation(String statement, List<Variable> vars, List<Proposition> operations) {
        this.vars = vars;
        this.operations = operations;
        List<String> initialSplit = balancedGroups(statement);
        int elements = initialSplit.size();
        if (elements == 2) {
            this.operator = new Not();
            String subProp = initialSplit.get(1);
            subPropSetter(subProp, vars, operations);
        } else {
            StringBuilder operator = new StringBuilder();
            String subProp1 = initialSplit.get(0);
            String subProp2 = initialSplit.get(elements - 1);

            // Builds operator string
            for (int i = 1; i < elements - 1; i++) {
                if (!initialSplit.get(i).equals(" ")) {
                    operator.append(initialSplit.get(i));
                }
            }
            this.operator = chooseOperator(operator.toString());
            subPropSetter(subProp1, vars, operations);
            subPropSetter(subProp2, vars, operations);
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
    public void subPropSetter(String statement, List<Variable> vars, List<Proposition> operations) {
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

    //    @Override
    public List<Proposition> getSubProps() {
        return this.subProps;
    }

    //    @Override
    public int getNumVar() {
        return this.vars.size();
    }

    //    @Override
    public Operator getOperator() {
        return this.operator;
    }

    public List<Proposition> getOOP() {
        return this.operations;
    }
}
