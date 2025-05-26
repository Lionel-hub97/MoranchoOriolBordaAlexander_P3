package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FrmCarregarDades extends JDialog {
    private JPanel contentPane;
    private JButton btnCarregarFitxer;
    private JButton btnCancelar;
    private JTextField txtNomFitxer;
    private JButton btnSelecciona;

    public FrmCarregarDades(JFrame parent, Adaptador adaptador) throws CentralUBException {
        super(parent, "Carregar Dades");
        setContentPane(contentPane);

        setSize(350, 200);
        setLocationRelativeTo(parent);
        setModal(true);
        btnSelecciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fitxer;
                //Creació del selector de fitxer
                JFileChooser seleccio = new JFileChooser();
                //Mostrem la finestra de dialeg
                //Resultat emmagazema una constant que indica si s’ha
                //seleccionat o no un fitxer
                int resultat = seleccio.showOpenDialog(FrmCarregarDades.this);
                //Assegurem que hi hagi un fitxer seleccionat
                if (resultat == JFileChooser.APPROVE_OPTION) {
                    //Obtenim el fitxer
                    fitxer = seleccio.getSelectedFile();
                    //Posem la ruta del fitxer al quadre de text

                    txtNomFitxer.setText(fitxer.toString());
                }
            }
        });
        btnCarregarFitxer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomFitxer = txtNomFitxer.getText().replace(',', '.');
                try {

                    adaptador.carregaDades(nomFitxer);
                    JOptionPane.showMessageDialog(FrmCarregarDades.this, "Dades carregades des de: " + nomFitxer + ".");
                    dispose();
                } catch (CentralUBException ex) {
                    mostrarError(ex.getMessage());
                }
            }
        });


        btnCancelar.addActionListener(new ActionListener() {
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
