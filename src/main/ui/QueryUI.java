package ui;

import model.Canvas;
import model.Comparison;
import model.Query;
import model.operations.TruthTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.max;

// represents a frame on the canvas to display a query
public class QueryUI extends JInternalFrame implements ComponentListener {
    private TruthTableUI printer;
    private Query theQuery;
    private Canvas cv;

    // EFFECTS: constructs a single query frame with appropriate frame size
    public QueryUI(Query q, Canvas cv, Component parent) {
        super(q.getType(), false, false, false, false);
        printer = new TruthTableUI();
        theQuery = q;
        this.cv = cv;

        JButton remove = new JButton(new RemoveAction());
        remove.setAlignmentX(CENTER_ALIGNMENT);
        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

        JScrollPane tabNew = printer.printTable(q.getOutputs().get(0));
        tabNew.setAlignmentX(CENTER_ALIGNMENT);
        cp.add(tabNew);

        Dimension dim = tabNew.getMinimumSize();
        int minWidth = (int) max(dim.getWidth(), 150);

        int dimHeight = (int) dim.getHeight() + 100;
        setSizes(minWidth, dimHeight);

        addIfComparison(q, cp, minWidth, dimHeight);

        cp.add(remove);

        setVisible(true);
        setLocation(cv.getX(theQuery), cv.getY(theQuery));

        addComponentListener(this);
    }

    // MODIFIES: this
    // EFFECTS: if query is comparison, add second truth table, equivalency statement,
    //          and resizes frame. Otherwise, do nothing.
    private void addIfComparison(Query q, Container cp, int minWidth, int dimHeight) {
        if (theQuery.getClass() == Comparison.class) {
            JScrollPane tabNew2 = printer.printTable(q.getOutputs().get(1));
            tabNew2.setAlignmentX(CENTER_ALIGNMENT);
            cp.add(tabNew2);

            TruthTable table1 = q.getOutputs().get(0);
            TruthTable table2 = q.getOutputs().get(1);
            int noCols1 = table1.getAssignments().size();
            int noCols2 = table2.getAssignments().size();

            boolean equiv = table1.getAssignments().get(noCols1 - 1).equals(table2.getAssignments().get(noCols2 - 1));

            String equivMsg = "";
            if (equiv) {
                equivMsg = "The statements are equivalent.";
            } else {
                equivMsg = "The statements are not equivalent.";
            }
            JTextField equivField = new JTextField(equivMsg);
            equivField.setEditable(false);
            cp.add(equivField);

            int minWidth2 = max(max(minWidth, tabNew2.getMinimumSize().width), 250);
            int dimHeight2 = (int) (dimHeight + tabNew2.getMinimumSize().getHeight() + 0);
            setSizes(minWidth2, dimHeight2);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets minimum size and initial size of frame with given dimensions
    private void setSizes(int width, int height) {
        Dimension dim = new Dimension(width, height);
        setMinimumSize(dim);
        setSize(dim);
    }

    // does nothing, necessary override to allow componentMoved detection
    @Override
    public void componentResized(ComponentEvent e) {

    }

    // MODIFIES: this
    // EFFECTS: updates position of theQuery when the frame is moved
    @Override
    public void componentMoved(ComponentEvent e) {
        updateLocation();
    }

    // does nothing, necessary override to allow componentMoved detection
    @Override
    public void componentShown(ComponentEvent e) {

    }

    // does nothing, necessary override to allow componentMoved detection
    @Override
    public void componentHidden(ComponentEvent e) {

    }

    // MODIFIES: this
    // represents action to be performed when remove button is clicked
    private class RemoveAction extends AbstractAction {
        RemoveAction() {
            super("Remove Query");
        }

        // MODIFIES: this
        // EFFECTS: removes query from canvas and sets visibility to false
        @Override
        public void actionPerformed(ActionEvent evt) {
            cv.removeQuery(theQuery);
            setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates theQuery's position based on current UI position
    public void updateLocation() {
        cv.setX(theQuery, getX());
        cv.setY(theQuery, getY());
    }
}
