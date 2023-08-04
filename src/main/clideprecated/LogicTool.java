package clideprecated;

import exceptions.InvalidStatementException;
import model.*;
import operations.BinaryOperation;
import operations.Proposition;
import operations.TruthTable;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// from TellerApp - https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class LogicTool {
    private static final String JSON_STORE = "./data/canvas.json";
    private Canvas canvas;
    private List<Query> history;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the logic tool application
    public LogicTool() throws FileNotFoundException {
        runLogicTool();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLogicTool() throws FileNotFoundException {
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
        } else if (command.equals("x")) {
            clearHistory();
        } else if (command.equals("v")) {
            editCanvas();
        } else if (command.equals("s")) {
            saveCanvas();
        } else if (command.equals("l")) {
            loadCanvas();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner and canvas
    private void init() throws FileNotFoundException {
        this.canvas = new Canvas("User's Canvas");
        this.history = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> truth table converter");
        System.out.println("\tc -> propositional logic statement comparator");
        System.out.println("\th -> view history");
        System.out.println("\tx -> clear history");
        System.out.println("\tv -> edit canvas");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a conversion from propositional logic statement to truth table, prints result,
    //          and adds query to history
    private void doConversion() {
        System.out.print("Enter propositional logic statement: ");
        String in = input.next();
        try {
            BinaryOperation operation = new BinaryOperation(in, new ArrayList<>(), new ArrayList<>());
            TruthTable table = new TruthTable(operation);
            printTable(table);
            this.history.add(new PropToTable(operation, table));
        } catch (InvalidStatementException e) {
            System.out.println("Please enter a valid propositional logic statement");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a comparison between two propositional logic statements and adds query to history
    private void doComparison() {
        System.out.print("Enter first propositional logic statement: ");
        String first = input.next();
        TruthTable table1;
        BinaryOperation operation1;
        try {
            operation1 = new BinaryOperation(first, new ArrayList<>(), new ArrayList<>());
            table1 = new TruthTable(operation1);
        } catch (InvalidStatementException e) {
            System.out.println("Please enter a valid propositional logic statement");
            return;
        }
        System.out.print("Enter second propositional logic statement: ");
        String second = input.next();
        TruthTable table2;
        BinaryOperation operation2;
        try {
            operation2 = new BinaryOperation(second, new ArrayList<>(), new ArrayList<>());
            table2 = new TruthTable(operation2);
        } catch (InvalidStatementException e) {
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

            System.out.println("\nExpand query no.? ");
            int targetNo = input.nextInt();
            try {
                Query target = this.history.get(targetNo);
                if (target.getClass() == PropToTable.class) {
                    printTable(target.getOutputs().get(0));
                } else {
                    equivalency(target.getInputs().get(0), target.getInputs().get(1),
                            target.getOutputs().get(0), target.getOutputs().get(1));
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index number!");
            }
        }
    }

    // EFFECTS: prints out the history of queries
    private void showHistory() {
        System.out.println("History:");
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
        System.out.println("\tv -> view canvas");
        System.out.println("\ta -> add to canvas");
        System.out.println("\tr -> remove from canvas");
        String command = input.next();
        printCanvas();
        if (command.equals("v")) {
            doViewCanvas();
        } else if (command.equals("a")) {
            doAddToCanvas();
        } else if (command.equals("r")) {
            doRemoveFromCanvas();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: prints outputs of selected query from canvas
    public void doViewCanvas() {
        System.out.println("\nExpand query no.? ");
        int targetNo = input.nextInt();
        try {
            Query target = this.canvas.getQueries().get(targetNo);
            if (target.getClass() == PropToTable.class) {
                printTable(target.getOutputs().get(0));
            } else {
                equivalency(target.getInputs().get(0), target.getInputs().get(1),
                        target.getOutputs().get(0), target.getOutputs().get(1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index number!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds selected query index no. to canvas
    public void doAddToCanvas() {
        System.out.println("\nSelect query no. to add: ");
        int targetNo = input.nextInt();
        try {
            Query target = this.history.get(targetNo);
            canvas.addQuery(target, 0, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index number!");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes selected query index no. from canvas
    public void doRemoveFromCanvas() {
        System.out.println("\nSelect query no. to remove: ");
        int targetNo = input.nextInt();
        try {
            Query target = this.canvas.getQueries().get(targetNo);
            canvas.removeQuery(target);
        } catch (IndexOutOfBoundsException e) {
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

//    // temporary version of printTable using HashMap implementation
//    private void printHashTable(TruthTable tab) {
//        Proposition prop = tab.getProp();
//        List<Proposition> headers = new ArrayList<>();
//        headers.addAll(prop.getVars());
//        headers.addAll(prop.getOOP());
//        headers.add(prop);
//        for (Proposition p : headers) {
//            String str = p.toString();
//            System.out.print(str + "\t");
//        }
//    }

    // EFFECTS: prints out the list of queries in the canvas and their x and y positions
    private void printCanvas() {
        System.out.println("Canvas queries:");
        for (Query q : canvas.getQueries()) {
            int index = canvas.getQueries().indexOf(q);
            System.out.print(index + ". (" + canvas.getX(q) + ", " + canvas.getY(q) + ") ");
            q.preview();
        }
    }

    // EFFECTS: converts false -> 0 and true -> 1
    private int boolToInt(boolean bool) {
        if (bool) {
            return 1;
        }
        return 0;
    }

    // EFFECTS: clears all prior history of queries
    private void clearHistory() {
        this.history = new ArrayList<>();
        System.out.println("History cleared!");
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

    // EFFECTS: saves the canvas to file
    private void saveCanvas() {
        try {
            jsonWriter.open();
            jsonWriter.write(canvas);
            jsonWriter.close();
            System.out.println("Saved " + canvas.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadCanvas() {
        try {
            canvas = jsonReader.read();
            System.out.println("Loaded " + canvas.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}