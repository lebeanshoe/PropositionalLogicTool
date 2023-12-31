package ui;

import model.exceptions.InvalidStatementException;
import model.*;
import model.Canvas;
import model.logging.Event;
import model.logging.EventLog;
import model.operations.BinaryOperation;
import model.operations.Proposition;
import model.operations.TruthTable;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// represents application's main window frame
public class LogicToolUI extends JFrame implements WindowListener {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 225;
    private static final String JSON_STORE = "./data/testCanvas.json";

    private Canvas cv;
    private List<Query> history;
    private TruthTableUI printer;

    private JDesktopPane desktop;
    private JInternalFrame commands;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: starts main application window after a splash screen
    public LogicToolUI() {
        cv = new Canvas("User's Canvas");
        history = new ArrayList<>();
        printer = new TruthTableUI();

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

        doSplash();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

        addWindowListener(this);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: displays the splash screen and closes it after a given amount of time
    private void doSplash() {
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }

        for (int i = 0; i < 5; i++) {
            renderSplashFrame(g, i);
            splash.update();
            try {
                Thread.sleep(90);
            } catch (InterruptedException e) {
                //
            }
        }
        splash.close();
        dispose();
    }

    // MODIFIES: this
    // EFFECTS: displays a single frame of the splash screen
    static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"operations", "queries", "canvas"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(30,350,200,40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString("Loading " + comps[(frame / 5) % 3] + "...", 30, 360);
    }

    // MODIFIES: this
    // EFFECTS: adds menu bar
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
        addMenuItem(canvasMenu, new ViewCanvasAction(), null);
        addMenuItem(canvasMenu, new ViewCanvasListAction(), null);
        menuBar.add(canvasMenu);

        setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: adds an item with given handler to the given menu
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    // MODIFIES: this
    // EFFECTS: adds main query action buttons to main window
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
        buttonPanel.add(new JButton(new PropToTableAction()));
        buttonPanel.add(new JButton(new ComparisonAction()));

        commands.add(buttonPanel, BorderLayout.WEST);
    }

    // EFFECTS: does nothing, part of WindowListener which was implemented for the windowClosing detection
    @Override
    public void windowOpened(WindowEvent e) {

    }

    // EFFECTS: prints out each event in event log to console when the program is about to terminate
    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
    }

    // EFFECTS: does nothing, part of WindowListener which was implemented for the windowClosing detection
    @Override
    public void windowClosed(WindowEvent e) {

    }

    // EFFECTS: does nothing, part of WindowListener which was implemented for the windowClosing detection
    @Override
    public void windowIconified(WindowEvent e) {

    }

    // EFFECTS: does nothing, part of WindowListener which was implemented for the windowClosing detection
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // EFFECTS: does nothing, part of WindowListener which was implemented for the windowClosing detection
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // EFFECTS: does nothing, part of WindowListener which was implemented for the windowClosing detection
    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    // EFFECTS: prints out each event in event log to console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // represents action to be taken to save canvas
    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Canvas");
        }

        // EFFECTS: saves canvas to json file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(cv);
                jsonWriter.close();
                String message = "Saved " + cv.getName() + " to " + JSON_STORE;
//                System.out.println(message);
                JOptionPane.showMessageDialog(null, message, "Canvas Saved",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (FileNotFoundException e) {
                String message = "Unable to write to file: " + JSON_STORE;
//                System.out.println(message);
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

        // MODIFIES: this
        // EFFECTS: recreates canvas from saved json file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                cv = jsonReader.read();
                String message = "Loaded " + cv.getName() + " from " + JSON_STORE;
//                System.out.println(message);
                JOptionPane.showMessageDialog(null, message, "Canvas Loaded",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                String message = "Unable to read from file: " + JSON_STORE;
//                System.out.println(message);
                JOptionPane.showMessageDialog(null, message, "Load Fail",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // represents action to be taken to view list of canvas queries in console
    private class ViewCanvasListAction extends AbstractAction {
        ViewCanvasListAction() {
            super("View Canvas List (console debugging)");
        }

        // EFFECTS: prints out canvas query list in consols
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

        // EFFECTS: creates a new window for the canvas view
        @Override
        public void actionPerformed(ActionEvent evt) {
            new CanvasView(cv);
        }
    }


    // Represents action to be taken when user wants to convert
    // a propositional logic statement to a truth table
    private class PropToTableAction extends AbstractAction {
        PropToTableAction() {
            super("Truth Table Converter");
        }

        // MODIFIES: this
        // EFFECTS: prompts user to enter statement to convert, displays result,
        //          and adds query to canvas if prompted
        @Override
        public void actionPerformed(ActionEvent evt) {
            String statement = JOptionPane.showInputDialog(null,
                    "Enter propositional logic statement",
                    "Enter propositional logic statement",
                    JOptionPane.QUESTION_MESSAGE);
            if (!(statement == null)) {
                try {
                    BinaryOperation binOp = new BinaryOperation(statement, new ArrayList<>(), new ArrayList<>());
                    TruthTable truTab = new TruthTable(binOp);
                    Query query = new PropToTable(binOp, truTab);
                    history.add(query);

                    Object[] options = {"Save to canvas", "No"};
                    int reply = JOptionPane.showOptionDialog(null, printer.printTable(truTab),
                            "Truth Table of: " + binOp.toString(), JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, null);
                    if (reply == 0) {
//                    System.out.println("save to canvas: " + reply);
                        cv.addQuery(query, 0, 0);
                    } else {
//                    System.out.println("don't save: " + reply);
                    }
                } catch (InvalidStatementException e) {
                    JOptionPane.showMessageDialog(null, "Invalid statement!", "System Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Represents action to be taken when user wants to compare
    // two propositional logic statements
    private class ComparisonAction extends AbstractAction {
        ComparisonAction() {
            super("Comparison");
        }

        // MODIFIES: this
        // EFFECTS: prompts user to enter two statements and returns result,
        //          adding query to canvas if prompted
        @Override
        public void actionPerformed(ActionEvent evt) {
            JTextField field1 = new JTextField();
            JTextField field2 = new JTextField();

            Object [] fields = {
                    "Statement 1", field1,
                    "Statement 2", field2
            };

            JOptionPane.showConfirmDialog(null, fields, "Comparison", JOptionPane.OK_CANCEL_OPTION);
            String statement1 = field1.getText();
            String statement2 = field2.getText();
            if (!(statement1 == null) && !(statement2 == null)
                    && !(statement1.equals("")) && !(statement2.equals(""))) {
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
    }

    // MODIFIES: this
    // EFFECTS: determines the equivalency of 2 propositions, displays both truth tables, and adds query to history,
    //          and adds query to canvas if prompted
    private void equivalency(Proposition operation1, Proposition operation2, TruthTable table1, TruthTable table2) {
        int noCols1 = table1.getAssignments().size();
        int noCols2 = table2.getAssignments().size();

        boolean equiv = table1.getAssignments().get(noCols1 - 1).equals(table2.getAssignments().get(noCols2 - 1));
        JScrollPane[][] message1 = {
                {printer.printTable(table1), printer.printTable(table2)}
        };

        String equivMsg;
        if (equiv) {
            equivMsg = "The statements are equivalent.";
        } else {
            equivMsg = "The statements are not equivalent.";
        }
        Object[] message = {
                message1,
                equivMsg
        };

        Query query = new Comparison(operation1, operation2, table1, table2);
        this.history.add(query);

        initCompareDialogue(operation1, operation2, message, query);
    }

    // MODIFIES: this
    // EFFECTS: creates a JOptionPane dialogue box to accept two propositions to compare and produces result,
    //          adding query to canvas if prompted
    private void initCompareDialogue(Proposition operation1, Proposition operation2, Object[] message, Query query) {
        Object [] options = {"Save to canvas", "No"};
        int reply = JOptionPane.showOptionDialog(null, message,
                "Comparison of: " + operation1.toString() + " and " + operation2.toString(),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        if (reply == 0) {
//            System.out.println("save to canvas: " + reply);
            cv.addQuery(query, 0, 0);
        } else {
//            System.out.println("don't save: " + reply);
        }
    }

    // MODIFIES: this
    // EFFECTS: centres main application window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // Represents action to be taken when user clicks desktop
    // to switch focus. (Needed for key handling.)
    private class DesktopFocusAction extends MouseAdapter {
        // EFFECTS: focuses window
        @Override
        public void mouseClicked(MouseEvent e) {
            LogicToolUI.this.requestFocusInWindow();
        }
    }

    // EFFECTS: starts application
    public static void main(String[] args) {
        new LogicToolUI();
    }
}