package main;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.net.URL;

public class Table {
    private JFrame frame;

    public Table()
    {
        frame = new JFrame("Tabelle");

        URL iconURL = getClass().getClassLoader().getResource("resources/matrix-icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());

        frame.pack();
        frame.setVisible(true);
    }

    public void displayCellTable(Integer[][] daten, String[] columns, int highest)
    {
        JTable table = new JTable(new MyTableModel(daten, columns));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                int content = (Integer) (table.getModel().getValueAt(row, col));
                content--;
                
                if (content < 0)
                {
                    content = 0;
                }
                table.getModel().setValueAt(content, row, col);
            }
        });

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
            int i = (divisor - (Integer) table.getValueAt(row, column)) * factor;
            setBackground(new Color(i, i, i));

            return this;
        }
    }

    public class MyTableModel extends AbstractTableModel {
        private String[] columnNames;
    
        private Object[][] data;

        public MyTableModel(Object[][] data, String[] columns)
        {
            this.data = data;
            columnNames = columns;
        }
        
        @Override
        public int getColumnCount() {
          return columnNames.length;
        }
        
        @Override
        public int getRowCount() {
          return data.length;
        }
    
        @Override
        public String getColumnName(int col) {
          return columnNames[col];
        }
    
        @Override
        public Object getValueAt(int row, int col) {
          return data[row][col];
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;

            fireTableCellUpdated(row, col);
        }
    }
}
