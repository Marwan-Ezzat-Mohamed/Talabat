package talabat;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class TableModelForRestaurantsTable extends AbstractTableModel {

    private String[] columns;
    private Object[][] rows;

    public TableModelForRestaurantsTable() {
    }

    public TableModelForRestaurantsTable(Object[][] data, String[] columnName) {

        this.rows = data;
        this.columns = columnName;
    }

    public Class getColumnClass(int column) {
        // 1 is the index of the column image
        if (column == 0) {
            return Icon.class;
        } else {
            return String.class;
        }
    }

    public int getRowCount() {
        return this.rows.length;
    }

    public int getColumnCount() {
        return this.columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        return this.rows[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return this.columns[col];
    }

}
