package prog2.vista;

import javax.swing.*;

public class FrmGestioComponentsCentral extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public FrmGestioComponentsCentral(JFrame parent) {
        super(parent, "Gestio Central");
        setContentPane(contentPane);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
    }
}
