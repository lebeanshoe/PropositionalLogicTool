package ui;

import operations.Proposition;
import operations.TruthTable;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Provides methods for displaying truth tables
public class TruthTableUI {
    private static final int ROW_HEIGHT_MULTIPLIER = 22;
    private static final int COL_WIDTH_PER_CHAR_MULTIPLIER = 12;

/*    // EFFECTS: prints out given truth table with columns headers as variables and operations.
    public String printTable(TruthTable tab) {
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
*/
    // EFFECTS: converts false -> 0 and true -> 1
    private int boolToInt(boolean bool) {
        if (bool) {
            return 1;
        }
        return 0;
    }

    // JTable implementation
    public JScrollPane printTable(TruthTable tab) {
        List<String> headerList = new ArrayList<>();
        for (Proposition p : tab.getColumnHeaders()) {
            headerList.add(p.toString());
        }
        int cols = tab.getColumnHeaders().size();
        int rows = tab.getAssignments().get(0).size();
        List<List<Boolean>> assigns = tab.getAssignments();

        Object[][] data = new Object[rows][cols];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                data[j][i] = boolToInt(assigns.get(i).get(j));
            }
        }

        Object[] headers = headerList.toArray();
        JTable tempTable = new JTable(data, headers) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        int width2 = 0;
        for (int i = 0; i < tab.getColumnHeaders().size(); i++) {
            int length = tab.getColumnHeaders().get(i).toString().length();
            int size = length * COL_WIDTH_PER_CHAR_MULTIPLIER;
            width2 += size;
            tempTable.getColumnModel().getColumn(i).setPreferredWidth(size);
        }


        int numChars = 0;
        for (Proposition p : tab.getColumnHeaders()) {
            numChars += p.toString().length();
        }
        int width = numChars * COL_WIDTH_PER_CHAR_MULTIPLIER;
        int height = rows * ROW_HEIGHT_MULTIPLIER;

        JScrollPane table = new JScrollPane(tempTable);
        Dimension min = new Dimension(width2, height);
        table.setMinimumSize(min);
        table.setPreferredSize(min);
        return table;
    }
}
