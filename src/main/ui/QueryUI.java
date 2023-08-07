package ui;

import model.Canvas;
import model.Comparison;
import model.Query;
import operations.TruthTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.max;

public class QueryUI extends JInternalFrame implements ComponentListener {
    private static final int LOC = 100;
    private static int queryCount = 0;
    private TruthTableUI printer;
    private JTable tab;
    private Query theQuery;
    private Canvas cv;

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
        Dimension newDim = new Dimension(minWidth, dimHeight);
        setMinimumSize(newDim);
        setSize(newDim);

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

            int minWidth2 = max(max(minWidth, tabNew2.getMinimumSize().width), 200);
            int dimHeight2 = (int) (dimHeight + tabNew2.getMinimumSize().getHeight() + 0);
            Dimension newDim2 = new Dimension(minWidth2, dimHeight2);
            setMinimumSize(newDim2);
            setSize(newDim2);
        }

        cp.add(remove);

        setVisible(true);
        setLocation(cv.getX(theQuery), cv.getY(theQuery));

//        addMouseListener(this);
        addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    // MODIFIES: cv
    // EFFECTS: updates position of theQuery when the frame is moved
    @Override
    public void componentMoved(ComponentEvent e) {
        updateLocation();
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    private class RemoveAction extends AbstractAction {
        RemoveAction() {
            super("Remove Query");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    // MODIFIES: cv
    // EFFECTS: updates theQuery's position based on current UI position
    public void updateLocation() {
        cv.setX(theQuery, getX());
        cv.setY(theQuery, getY());
    }
}
