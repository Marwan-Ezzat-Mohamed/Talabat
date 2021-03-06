package CustomModels;



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

    @Override
    public Class getColumnClass(int column) {
        // 1 is the index of the column image

        Class returnValue = null;
        if (column == 0) {
            returnValue = Icon.class;
        } else if (column == 3) {
            returnValue = Float.class;
        } else {
            returnValue = String.class;
        }

        return returnValue;
    }

    @Override
    public int getRowCount() {
        return this.rows.length;
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return this.rows[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return this.columns[col];
    }

}
