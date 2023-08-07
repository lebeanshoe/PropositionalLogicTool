package ui;

import model.Canvas;
import model.Query;

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
        super("Sample", true, false, false, false);
        printer = new TruthTableUI();
        theQuery = q;
        this.cv = cv;

//        JTable rawTab = printer.printTable(q.getOutputs().get(0));
        JScrollPane tabNew = printer.printTable(q.getOutputs().get(0));

        tabNew.setAlignmentX(CENTER_ALIGNMENT);

        JButton remove = new JButton(new RemoveAction());
        remove.setAlignmentX(CENTER_ALIGNMENT);
        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        cp.add(tabNew);
        cp.add(remove);
        int minWidth = max(tabNew.getMinimumSize().width, 150);
        Dimension dim = tabNew.getMinimumSize();
        int dimHeight = (int) dim.getHeight() + 70;
        Dimension newDim = new Dimension(minWidth, dimHeight);
        setMinimumSize(newDim);
        setSize(newDim);
        setVisible(true);
        setLocation(cv.getX(theQuery), cv.getY(theQuery));

//        addMouseListener(this);
        addComponentListener(this);

        this.cv = cv;
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

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

    public void updateLocation() {
        cv.setX(theQuery, getX());
        cv.setY(theQuery, getY());
    }
}
