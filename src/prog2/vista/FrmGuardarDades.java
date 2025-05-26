package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGuardarDades extends JDialog {
    private JPanel contentPane;
    private JButton btnGuardarDades;
    private JButton btnCancel;
    private JTextField txtNomFitxer;

    public FrmGuardarDades(JFrame parent, Adaptador adaptador) throws CentralUBException {
        super(parent, "Guardar Dades");
        setContentPane(contentPane);

        setSize(350, 200);
        setLocationRelativeTo(parent);
        setModal(true);
        btnGuardarDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomFitxer = txtNomFitxer.getText().replace(',', '.');
                try{
                    adaptador.guardaDades(nomFitxer);
                    JOptionPane.showMessageDialog(FrmGuardarDades.this, "Dades guardades a: " + (nomFitxer.endsWith(".dat") ? nomFitxer : nomFitxer + ".dat" )+ ".");
                    dispose();
                } catch (CentralUBException ex) {
                    mostrarError(ex.getMessage());
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    private void mostrarError(String missatge) {
        JOptionPane.showMessageDialog(this, missatge, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
