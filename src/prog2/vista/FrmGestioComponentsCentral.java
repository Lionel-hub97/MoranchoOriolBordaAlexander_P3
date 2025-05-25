package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;

public class FrmGestioComponentsCentral extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSlider sldBarresControl;
    private JTextField textField1;
    private Adaptador adaptador;

    public FrmGestioComponentsCentral(JFrame parent, Adaptador adaptador) {
        super(parent, "Gestio Central");
        setContentPane(contentPane);
        setSize(600, 500);
        setLocationRelativeTo(parent);
        setModal(true);
    }
}
