package ui;

import exceptions.InvalidStatementException;
import model.Canvas;
import model.Comparison;
import model.PropToTable;
import model.Query;
import operations.BinaryOperation;
import operations.Proposition;
import operations.TruthTable;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
need:
- empty canvas
- controls:
    - history view list
 */
// represents application's main window frame
public class LogicToolUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/canvas.json";

    private Canvas cv;
    private List<Query> history;

    private JDesktopPane desktop;
    private JInternalFrame commands;
    private JDesktopPane canvasPane;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public LogicToolUI() {
        cv = new Canvas("User's Canvas");
        history = new ArrayList<>();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        commands = new JInternalFrame("Commands");
        commands.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Propositional Logic Tool");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();
        addMenu();

        commands.pack();
        commands.setVisible(true);
        desktop.add(commands);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    /**
     * Adds menu bar.
     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        addMenuItem(fileMenu, new SaveAction(),
                KeyStroke.getKeyStroke("control S"));
        addMenuItem(fileMenu, new LoadAction(),
                KeyStroke.getKeyStroke("control L"));
        menuBar.add(fileMenu);

        JMenu canvasMenu = new JMenu("Canvas");
        canvasMenu.setMnemonic('C');
        addMenuItem(canvasMenu, new ViewCanvasListAction(), null);
        addMenuItem(canvasMenu, new ViewCanvasAction(), null);
//        addMenuItem(canvasMenu, new AddQueryAction(), null);
//        addMenuItem(canvasMenu, new RemoveQueryAction(), null);
        menuBar.add(canvasMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Adds an item with given handler to the given menu
     * @param theMenu  menu to which new item is added
     * @param action   handler for new menu item
     * @param accelerator    keystroke accelerator for this menu item
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
        buttonPanel.add(new JButton(new PropToTableAction()));
        buttonPanel.add(new JButton(new ComparisonAction()));

        commands.add(buttonPanel, BorderLayout.WEST);
    }

    // EFFECTS: prints out given truth table with columns headers as variables and operations.
    private String printTable(TruthTable tab) {
        List<Proposition> colHeads = tab.getColumnHeaders();
        List<List<Boolean>> assigns = tab.getAssignments();
        int noCol = colHeads.size();
        int assignLength = assigns.get(0).size();
        int i = 0;
        String res = "";
        while (i < noCol - 1) {
            String str = colHeads.get(i).toString();
//            System.out.print(str + "\t");
            res = res + str + "   ";
            i++;
        }
//        System.out.print(colHeads.get(noCol - 1) + "\n");
        res = res + colHeads.get(noCol - 1) + "\n";

        for (int k = 0; k < assignLength; k++) {
            int j = 0;
            while (j < noCol - 1) {
                int val = boolToInt(assigns.get(j).get(k));
//                System.out.print(val + "\t");
                res = res + val + "   ";
                j++;
            }
//            System.out.print(boolToInt(assigns.get(noCol - 1).get(k)) + "\n");
            res = res + boolToInt(assigns.get(noCol - 1).get(k)) + "\n";
        }
        System.out.println(res); // TODO - shows console output of truth table
        return res;
    }

    // EFFECTS: converts false -> 0 and true -> 1
    private int boolToInt(boolean bool) {
        if (bool) {
            return 1;
        }
        return 0;
    }

    // represents action to be taken to save canvas
    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Canvas");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(cv);
                jsonWriter.close();
                String message = "Saved " + cv.getName() + " to " + JSON_STORE;
                System.out.println(message);
                JOptionPane.showMessageDialog(null, message, "Canvas Saved",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (FileNotFoundException e) {
                String message = "Unable to write to file: " + JSON_STORE;
                System.out.println(message);
                JOptionPane.showMessageDialog(null, message, "Save Fail",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // represents action to be taken to load canvas from file
    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("Load Canvas");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                cv = jsonReader.read();
                String message = "Loaded " + cv.getName() + " from " + JSON_STORE;
                System.out.println(message);
                JOptionPane.showMessageDialog(null, message, "Canvas Loaded",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                String message = "Unable to read from file: " + JSON_STORE;
                System.out.println(message);
                JOptionPane.showMessageDialog(null, message, "Load Fail",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // represents action to be taken to view list of canvas queries
    private class ViewCanvasListAction extends AbstractAction {
        ViewCanvasListAction() {
            super("View Canvas List");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            System.out.println("Canvas queries:");
            for (Query q : cv.getQueries()) {
                int index = cv.getQueries().indexOf(q);
                System.out.print(index + ". (" + cv.getX(q) + ", " + cv.getY(q) + ") ");
                q.preview();
            }
        }
    }

    // represents action to be taken to view canvas
    private class ViewCanvasAction extends AbstractAction {
        ViewCanvasAction() {
            super("View Canvas");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            canvasPane = new JDesktopPane();
        }
    }

    /**
     * Represents action to be taken when user wants to convert
     * a propositional logic statement to a truth table
     */
    private class PropToTableAction extends AbstractAction {
        PropToTableAction() {
            super("Truth Table Converter");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String statement = JOptionPane.showInputDialog(null,
                    "Enter propositional logic statement",
                    "Enter propositional logic statement",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                BinaryOperation binOp = new BinaryOperation(statement, new ArrayList<>(), new ArrayList<>());
                TruthTable truTab = new TruthTable(binOp);
                Query query = new PropToTable(binOp, truTab);
                history.add(query);

                Object [] options = {"Save to canvas", "No"};
                int reply = JOptionPane.showOptionDialog(null, printTable(truTab),
                        "Truth Table of: " + binOp.toString(), JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (reply == 0) {
                    System.out.println("save to canvas: " + reply);
                    cv.addQuery(query, 0, 0);
                } else {
                    System.out.println("don't save: " + reply);
                }
            } catch (InvalidStatementException e) {
                JOptionPane.showMessageDialog(null, "Invalid statement!", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents action to be taken when user wants to compare
     * two propositional logic statements
     */
    private class ComparisonAction extends AbstractAction {
        ComparisonAction() {
            super("Comparison");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // from a youtube video for multiple input fields
            JTextField field1 = new JTextField();
            JTextField field2 = new JTextField();

            Object [] fields = {
                    "Statement 1", field1,
                    "Statement 2", field2
            };

            JOptionPane.showConfirmDialog(null, fields, "Comparison", JOptionPane.OK_CANCEL_OPTION);
            String statement1 = field1.getText();
            String statement2 = field2.getText();
            try {
                BinaryOperation binOp1 = new BinaryOperation(statement1, new ArrayList<>(), new ArrayList<>());
                BinaryOperation binOp2 = new BinaryOperation(statement2, new ArrayList<>(), new ArrayList<>());
                TruthTable tab1 = new TruthTable(binOp1);
                TruthTable tab2 = new TruthTable(binOp2);
                equivalency(binOp1, binOp2, tab1, tab2);
            } catch (InvalidStatementException e) {
                JOptionPane.showMessageDialog(null, "Invalid statement!", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: determines the equivalency of 2 propositions, displays both truth tables, and adds query to history
    private void equivalency(Proposition operation1, Proposition operation2, TruthTable table1, TruthTable table2) {
        int noCols1 = table1.getAssignments().size();
        int noCols2 = table2.getAssignments().size();

        boolean equiv = table1.getAssignments().get(noCols1 - 1).equals(table2.getAssignments().get(noCols2 - 1));
        String message = printTable(table1) + "\n" + printTable(table2);
        if (equiv) {
//            System.out.println("The statements are equivalent.");
            message = message + "\n" + "The statements are equivalent.";
        } else {
//            System.out.println("The statements are not equivalent.");
            message = message + "\n" + "The statements are not equivalent.";
        }

        Query query = new Comparison(operation1, operation2, table1, table2);
        this.history.add(query);

        Object [] options = {"Save to canvas", "No"};
        int reply = JOptionPane.showOptionDialog(null, message,
                "Comparison of: " + operation1.toString() + " and " + operation2.toString(),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        if (reply == 0) {
            System.out.println("save to canvas: " + reply);
            cv.addQuery(query, 0, 0);
        } else {
            System.out.println("don't save: " + reply);
        }
    }

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            LogicToolUI.this.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        new LogicToolUI();
    }
}