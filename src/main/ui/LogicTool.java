package ui;

import model.*;
import operations.BinaryOperation;
import operations.Proposition;
import operations.TruthTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.StatementSplitter.balancedGroups;

// from TellerApp
public class LogicTool {
    private Canvas canvas;
    private List<Query> history = new ArrayList<>();
    private Scanner input;

    // EFFECTS: runs the logic tool application
    public LogicTool() {
        runLogicTool();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLogicTool() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for trying out this tool!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("t")) {
            doConversion();
        } else if (command.equals("c")) {
            doComparison();
        } else if (command.equals("h")) {
            viewHistory();
        } else if (command.equals("v")) {
            editCanvas();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner
    private void init() {
        this.canvas = new Canvas();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> truth table converter");
        System.out.println("\tc -> propositional logic statement comparator");
        System.out.println("\th -> history");
        System.out.println("\tv -> edit canvas");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a conversion from propositional logic statement to truth table and prints result
    private void doConversion() {
        System.out.print("Enter propositional logic statement: ");
        String in = input.next();
        if (isValidStatement(in)) {
            BinaryOperation operation = new BinaryOperation(in, new ArrayList<>(), new ArrayList<>());
            TruthTable table = new TruthTable(operation);
            printTable(table);
            this.history.add(new PropToTable(operation, table));
        } else {
            System.out.println("Please enter a valid propositional logic statement");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a comparison between two propositional logic statements
    private void doComparison() {
        System.out.print("Enter first propositional logic statement: ");
        String first = input.next();
        TruthTable table1;
        BinaryOperation operation1;
        if (isValidStatement(first)) {
            operation1 = new BinaryOperation(first, new ArrayList<>(), new ArrayList<>());
            table1 = new TruthTable(operation1);
        } else {
            System.out.println("Please enter a valid propositional logic statement");
            return;
        }
        System.out.print("Enter second propositional logic statement: ");
        String second = input.next();
        TruthTable table2;
        BinaryOperation operation2;
        if (isValidStatement(second)) {
            operation2 = new BinaryOperation(second, new ArrayList<>(), new ArrayList<>());
            table2 = new TruthTable(operation2);
        } else {
            System.out.println("Please enter a valid propositional logic statement");
            return;
        }

        equivalency(operation1, operation2, table1, table2);
    }

    // EFFECTS: prints out history of queries if it exists, then prints out the outputs of selected query
    private void viewHistory() {
        if (this.history.isEmpty()) {
            System.out.println("No history to show!");
        } else {
            showHistory();
        }

        System.out.println("\nExpand query no.? ");
        int targetNo = input.nextInt();
        if ((targetNo >= 0) && (targetNo <= this.history.size() - 1)) {
            Query target = this.history.get(targetNo);
            if (target.getClass() == PropToTable.class) {
                printTable(target.getOutputs().get(0));
            } else {
                equivalency(target.getInputs().get(0), target.getInputs().get(1),
                        target.getOutputs().get(0), target.getOutputs().get(1));
            }
        } else {
            System.out.println("Invalid index number!");
        }
    }

    // EFFECTS: prints out the history of queries
    private void showHistory() {
        for (Query query : this.history) {
            int index = this.history.indexOf(query);
            System.out.print(index + ". ");
            query.preview();
        }
    }

    // MODIFIES: this
    // EFFECTS: shows history, then prompts user to add or remove query from canvas
    private void editCanvas() {
        if (this.history.isEmpty()) {
            System.out.println("No history to show!");
        } else {
            showHistory();
        }
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add to canvas");
        System.out.println("\tr -> remove from canvas");
        if (input.next().equals("a")) {
            System.out.print(this.canvas.getQueries().toString());
            doAddToCanvas();
        } else if (input.next().equals("r")) {
            doRemoveFromCanvas();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds selected query index no. to canvas
    public void doAddToCanvas() {
        System.out.println("\nSelect query no. to add: ");
        int targetNo = input.nextInt();
        if ((targetNo >= 0) && (targetNo <= this.history.size() - 1)) {
            Query target = this.history.get(targetNo);
            canvas.addQuery(target, 0, 0);
        } else {
            System.out.println("Invalid index number!");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes selected query index no. from canvas
    public void doRemoveFromCanvas() {
        System.out.println("\nSelect query no. to remove: ");
        int targetNo = input.nextInt();
        if ((targetNo >= 0) && (targetNo <= this.history.size() - 1)) {
            Query target = this.history.get(targetNo);
            canvas.removeQuery(target);
        } else {
            System.out.println("Invalid index number!");
        }
    }

    // EFFECTS: prints out given truth table with columns headers as variables and operations.
    private void printTable(TruthTable tab) {
        List<Proposition> colHeads = tab.getColumnHeaders();
        List<List<Boolean>> assigns = tab.getAssignments();
        int noCol = colHeads.size();
        int assignLength = assigns.get(0).size();
        int i = 0;
        while (i < noCol - 1) {
            String str = colHeads.get(i).toString();
            System.out.print(str + "\t");
            i++;
        }
        System.out.print(colHeads.get(noCol - 1) + "\n");

        for (int k = 0; k < assignLength; k++) {
            int j = 0;
            while (j < noCol - 1) {
                int val = boolToInt(assigns.get(j).get(k));
                System.out.print(val + "\t");
                j++;
            }
            System.out.print(boolToInt(assigns.get(noCol - 1).get(k)) + "\n");
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

    // EFFECTS: converts false -> 0 and true -> 1
    private int boolToInt(boolean bool) {
        if (bool) {
            return 1;
        }
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: determines the equivalency of 2 propositions, displays both truth tables, and adds query to history
    private void equivalency(Proposition operation1, Proposition operation2, TruthTable table1, TruthTable table2) {
        printTable(table1);
        printTable(table2);
        int noCols1 = table1.getAssignments().size();
        int noCols2 = table2.getAssignments().size();

        boolean equiv = table1.getAssignments().get(noCols1 - 1).equals(table2.getAssignments().get(noCols2 - 1));
        if (equiv) {
            System.out.println("The statements are equivalent.");
        } else {
            System.out.println("The statements are not equivalent.");
        }

        this.history.add(new Comparison(operation1, operation2, table1, table2));
    }
}