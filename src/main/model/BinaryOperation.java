package model;

import operators.*;

import java.util.ArrayList;
import java.util.List;

import static model.StatementSplitter.balancedGroups;

// represents a binary operation with an operator and two proposition parameters
public class BinaryOperation implements Proposition {
    private Operator operator;
    private List<Proposition> subProps = new ArrayList<>();
    private List<Variable> vars = new ArrayList<>();

    /*
    REQUIRES: No outermost wrapping parentheses
    main converter from string -> prop logic statement
    - calls BinaryOperation and UnaryOperation constructors
     */
    public BinaryOperation(String statement, List<Variable> vars) {
        this.vars = vars;
        List<String> initialSplit = balancedGroups(statement);
        int elements = initialSplit.size();
        if (elements == 2) {
            this.operator = new Not();
            String subProp = initialSplit.get(1);
            subPropSetter(subProp, vars);
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
            subPropSetter(subProp1, vars);
            subPropSetter(subProp2, vars);
        }
    }

//    // EFFECTS: constructs a binary operation with given operation that operates on the firstParam
//    //          proposition and secondParam proposition in order
//    public BinaryOperation(Proposition firstParam, String operator, Proposition secondParam) {
//        this.subProps.add(firstParam);
//        this.subProps.add(secondParam);
//
//        if (operator.equals("^")) {
//            this.operator = and;
//        } else if (operator.equals("<->") | operator.equals("<=>")) {
//            this.operator = bic;
//        } else if (operator.equals("->") | operator.equals("=>")) {
//            this.operator = imp;
//        } else if (operator.equals("~")) {
//            this.operator = not;
//        } else if (operator.equals("v")) {
//            this.operator = or;
//        } else {
//            this.operator = xor;
//        }
//    }

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
                return lhs.toString() + " " + this.operator.toString() + " " + rhs.toString();
            } else if (lhs.getClass() != Variable.class && rhs.getClass() == Variable.class) {
                return "(" + lhs.toString() + ")" + " " + this.operator.toString() + " " + rhs.toString();
            } else if (lhs.getClass() == Variable.class && rhs.getClass() != Variable.class) {
                return lhs.toString() + " " + this.operator.toString() + " " + "(" + rhs.toString() + ")";
            } else {
                return "(" + lhs.toString() + ")" + " " + this.operator.toString() + " " + "(" + rhs.toString() + ")";
            }
        }
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

    public void subPropSetter(String statement, List<Variable> vars) {
        if (statement.length() == 1) {
            List<String> varsAsString = new ArrayList<>();
            for (Variable var : this.vars) {
                varsAsString.add(var.toString());
            }
            if (!varsAsString.contains(statement)) {
                Variable localVar = new Variable(statement, this.vars.size());
                this.subProps.add(localVar);
                this.vars.add(localVar);
                for (Variable var : this.vars) {
                    var.setNumVars(this.vars.size());
                }
            } else {
                int varPos = varsAsString.indexOf(statement);
                this.subProps.add(this.vars.get(varPos));
            }

//            Variable localVar = new Variable(statement);
//            this.subProps.add(localVar);
//            if (!this.vars.contains(localVar)) {
//                this.vars.add(localVar);
//            }
        } else {
            this.subProps.add(new BinaryOperation(statement.substring(1, statement.length() - 1), vars));
        }
    }
}
