package ui;

import model.BinaryOperation;
import model.Proposition;
import model.TruthTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.StatementSplitter.balancedGroups;

// from TellerApp
public class LogicTool {
    private Scanner input;

    // EFFECTS: runs the teller application
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
//        } else if (command.equals("h")) {
//            viewHistory();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> truth table converter");
        System.out.println("\tc -> propositional logic statement comparator");
        System.out.println("\th -> history");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void doConversion() {
        System.out.print("Enter propositional logic statement: ");
        String in = input.next();
        if (isValidStatement(in)) {
            BinaryOperation operation = new BinaryOperation(in, new ArrayList<>(), new ArrayList<>());
            TruthTable table = new TruthTable(operation);
            printTable(table);
        }
        System.out.println("Please enter a valid propositional logic statement");
    }

    // MODIFIES: this
    // EFFECTS: conducts a withdraw transaction
    private void doComparison() {
        System.out.print("Enter first propositional logic statement: ");
        String first = input.next();
        TruthTable table1;
        if (isValidStatement(first)) {
            table1 = new TruthTable(new BinaryOperation(first, new ArrayList<>(), new ArrayList<>()));
        } else {
            System.out.println("Please enter a valid propositional logic statement");
            return;
        }
        System.out.print("Enter second propositional logic statement: ");
        String second = input.next();
        TruthTable table2;
        if (isValidStatement(second)) {
            table2 = new TruthTable(new BinaryOperation(second, new ArrayList<>(), new ArrayList<>()));
        } else {
            System.out.println("Please enter a valid propositional logic statement");
            return;
        }

        equivalency(table1, table2);
    }

//    // MODIFIES: this
//    // EFFECTS: conducts a transfer transaction
//    private void viewHistory() {
//        System.out.println("\nTransfer from?");
//        Account source = selectAccount();
//        System.out.println("Transfer to?");
//        Account destination = selectAccount();
//
//        System.out.print("Enter amount to transfer: $");
//        double amount = input.nextDouble();
//
//        if (amount < 0.0) {
//            System.out.println("Cannot transfer negative amount...\n");
//        } else if (source.getBalance() < amount) {
//            System.out.println("Insufficient balance on source account...\n");
//        } else {
//            source.withdraw(amount);
//            destination.deposit(amount);
//        }
//
//        System.out.print("Source ");
//        printBalance(source);
//        System.out.print("Destination ");
//        printBalance(destination);
//    }

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

    private boolean isValidStatement(String str) {
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

    private boolean isValidOperator(String operator) {
        return operator.equals("^") | operator.equals("<->") | operator.equals("<=>") | operator.equals("->")
                | operator.equals("=>") | operator.equals("~") | operator.equals("v") | operator.equals("xor");
    }

    private int boolToInt(boolean bool) {
        if (bool) {
            return 1;
        }
        return 0;
    }

    private void equivalency(TruthTable table1, TruthTable table2) {
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
    }
}