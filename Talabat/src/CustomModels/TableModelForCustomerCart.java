package CustomModels;


import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class TableModelForCustomerCart extends AbstractTableModel{
    private String[] columns;
    private Object[][] rows;

    public TableModelForCustomerCart() {
    }

    public TableModelForCustomerCart(Object[][] data, String[] columnName) {

        this.rows = data;
        this.columns = columnName;
    }

    @Override
    public Class getColumnClass(int column) {
        // 1 is the index of the column image
        if (column == 0) {
            return Icon.class;
        } else {
            return String.class;
        }
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

    @Override
    public String getColumnName(int col) {
        return this.columns[col];
    }
}
