package ui;

import model.operations.Proposition;
import model.operations.TruthTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Provides methods for displaying truth tables
public class TruthTableUI {
    private static final int ROW_HEIGHT_MULTIPLIER = 22;
    private static final int COL_WIDTH_PER_CHAR_MULTIPLIER = 10;

    // EFFECTS: converts false -> 0 and true -> 1
    private int boolToInt(boolean bool) {
        if (bool) {
            return 1;
        }
        return 0;
    }

    // EFFECTS: returns a JScrollPane containing a JTable of the truth table
    public JScrollPane printTable(TruthTable tab) {
        Object[] headers = extractHeaders(tab);
        Object[][] data = initData(tab);

        JTable tempTable = new JTable(data, headers) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        Dimension dim = getDimension(tab, tempTable);

        JScrollPane table = new JScrollPane(tempTable);
        table.setMinimumSize(dim);
        table.setPreferredSize(dim);
        return table;
    }

    // EFFECTS: returns the minimum dimension of a truth table based on the number of rows and columns
    private Dimension getDimension(TruthTable tab, JTable tempTable) {
        int width = 0;
        for (int i = 0; i < tab.getColumnHeaders().size(); i++) {
            int length = tab.getColumnHeaders().get(i).toString().length();
            int size = length * COL_WIDTH_PER_CHAR_MULTIPLIER;
            width += size;
            tempTable.getColumnModel().getColumn(i).setPreferredWidth(size);
        }

        int numChars = 0;
        for (Proposition p : tab.getColumnHeaders()) {
            numChars += p.toString().length();
        }
        int rows = tab.getAssignments().get(0).size();
        int height = rows * ROW_HEIGHT_MULTIPLIER;

        Dimension min = new Dimension(width, height);
        return min;
    }

    // EFFECTS: returns a 1-dimensional array representing a truth table's headers
    private Object[] extractHeaders(TruthTable tab) {
        List<String> headerList = new ArrayList<>();
        for (Proposition p : tab.getColumnHeaders()) {
            headerList.add(p.toString());
        }
        Object[] headers = headerList.toArray();
        return headers;
    }

    // EFFECTS: returns 2-dimensional array to represent a truth table's values
    private Object[][] initData(TruthTable tab) {
        int cols = tab.getColumnHeaders().size();
        int rows = tab.getAssignments().get(0).size();
        List<List<Boolean>> assigns = tab.getAssignments();

        Object[][] data = new Object[rows][cols];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                data[j][i] = boolToInt(assigns.get(i).get(j));
            }
        }
        return data;
    }
}
