import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class GUI {
    private JFrame frame;
    private JPanel panel;

    private JLabel grad;
    private JLabel exp;
    private JLabel cell;
    private JLabel error;

    private JTextField gradEntry;
    private JTextField expEntry;
    private JTextField cellEntry;
    private JButton button;

    public GUI()
    {
        frame = new JFrame("Matrixmultiplikation");
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,4,10,10));
        panel.setBorder(new EmptyBorder(15,15,15,15));;

        grad = new JLabel("Grad:");
        exp = new JLabel("Exponent:");
        cell = new JLabel("Zelle:");
        error = new JLabel("");

        gradEntry = new JTextField();
        expEntry = new JTextField();
        cellEntry = new JTextField();

        button = new JButton("Ok");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                error.setText("");

                try {
                    int grad = Integer.parseInt(gradEntry.getText());
                    int exp = Integer.parseInt(expEntry.getText());
                    int cell = Integer.parseInt(cellEntry.getText());

                    if (grad < 1)
                    {
                        error.setText("Grad muss mindestens 1 sein");
                    } else if (exp < 1)
                    {
                        error.setText("Exponent muss mindestens 1 sein");
                    } else if (cell < 0 || cell >= (grad * grad))
                    {
                        error.setText("Zellennr muss zwischen 0 und " + ((grad * grad) - 1) + " sein");
                    } else
                    {
                        Matrixmultiplikator m = new Matrixmultiplikator(grad, exp, cell);
                    }
                } catch (NumberFormatException n) {
                    error.setText("Nur Zahlen erlaubt!");
                }
                
                
                frame.pack();
            }
         });

        panel.add(grad);
        panel.add(exp);
        panel.add(cell);
        panel.add(button);

        panel.add(gradEntry);
        panel.add(expEntry);
        panel.add(cellEntry);
        panel.add(error);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
