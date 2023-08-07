package ui;

import model.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CanvasView extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private model.Canvas cv;

    private JDesktopPane desktop;

    public CanvasView(model.Canvas cv) {
        this.cv = cv;

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());

        setContentPane(desktop);
        setTitle(cv.getName());
        setSize(WIDTH, HEIGHT);

        initQueries();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds all queries on canvas to desktop
    private void initQueries() {
        //todo
        for (Query q : cv.getQueries()) {
//            JInternalFrame queryView = new QueryUI(q, cv, this);
//            queryView.setLayout(new BorderLayout());
//            queryView.pack();
//            queryView.setVisible(true);
//            desktop.add(queryView);
            QueryUI queryUI = new QueryUI(q, cv, CanvasView.this);
//            queryUI.addMouseListener(this);
//            addMouseListener(this);
            desktop.add(queryUI);
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
            requestFocusInWindow();
        }
    }
}
