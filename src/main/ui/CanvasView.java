package ui;

import model.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// represents a canvas with its queries displayed within it
public class CanvasView extends JFrame {
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;

    private model.Canvas cv;

    private JDesktopPane desktop;

    // EFFECTS: constructs a canvas desktop frame with queries displayed within
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
        for (Query q : cv.getQueries()) {
            QueryUI queryUI = new QueryUI(q, cv, CanvasView.this);
            desktop.add(queryUI);
        }
    }

    // MODIFIES: this
    // EFFECTS: centres the canvas view window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


     // Represents action to be taken when user clicks desktop
     // to switch focus. (Needed for key handling.)
    private class DesktopFocusAction extends MouseAdapter {
        // MODIFIES: this
        // EFFECTS: focuses canvas view window
        @Override
        public void mouseClicked(MouseEvent e) {
            requestFocusInWindow();
        }
    }
}
