package ui;

import model.BinaryOperation;
import model.Proposition;
import model.TruthTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
//        } else if (command.equals("c")) {
//            doComparison();
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
        System.out.println("\tt -> to truth table conversion");
        System.out.println("\tc -> propositional logic state comparator");
        System.out.println("\th -> history");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void doConversion() {
        System.out.print("Enter propositional logic statement: ");
        BinaryOperation operation = new BinaryOperation(input.next(), new ArrayList<>(), new ArrayList<>());
        TruthTable table = new TruthTable(operation);

        printTable(table);
    }

//    // MODIFIES: this
//    // EFFECTS: conducts a withdraw transaction
//    private void doComparison() {
//        Account selected = selectAccount();
//        System.out.print("Enter amount to withdraw: $");
//        double amount = input.nextDouble();
//
//        if (amount < 0.0) {
//            System.out.println("Cannot withdraw negative amount...\n");
//        } else if (selected.getBalance() < amount) {
//            System.out.println("Insufficient balance on account...\n");
//        } else {
//            selected.withdraw(amount);
//        }
//
//        printBalance(selected);
//    }

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

    private int boolToInt(boolean bool) {
        if (bool) {
            return 1;
        }
        return 0;
    }
}