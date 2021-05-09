import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class Table {
    private JFrame frame;

    public Table()
    {
        frame = new JFrame("Tabelle");
        frame.pack();
        frame.setVisible(true);
    }

    public void displayCellTable(String[][] daten, String[] columns, int highest)
    {
        JTable table = new JTable(new MyTableModel(daten, columns));
        JScrollPane sp = new JScrollPane(table);    
        colorize(table, highest);
        frame.getContentPane().add(sp);
        frame.pack();
    }

    public void colorize(JTable t, int divisor)
    {
        t.setDefaultRenderer(Object.class, new CellRenderer(divisor));
    }


    public class CellRenderer extends DefaultTableCellRenderer
    {
        private int factor;
        private int divisor;

        public CellRenderer(int divisor)
        {
            this.divisor = divisor;
            factor = (int) 255 / divisor;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            int i = (divisor - Integer.parseInt((String) table.getValueAt(row, column))) * factor;
            setBackground(new Color(i, i, i));

            return this;
        }
    }

    public class MyTableModel extends AbstractTableModel {
        private String[] columnNames;
    
        private Object[][] data;

        public MyTableModel(String[][] data, String[] columns)
        {
            this.data = data;
            columnNames = columns;
        }
    
        public int getColumnCount() {
          return columnNames.length;
        }
    
        public int getRowCount() {
          return data.length;
        }
    
        public String getColumnName(int col) {
          return columnNames[col];
        }
    
        public Object getValueAt(int row, int col) {
          return data[row][col];
        }
    }
}
