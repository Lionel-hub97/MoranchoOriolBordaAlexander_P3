package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CarregarDades extends JFrame {
    private JTextField txtNomFitxer;
    private JButton btnSelecciona;
    private JPanel contentPanel;
    private JButton btnCarregarFitxer;

    public CarregarDades(JFrame parent, Adaptador adaptador)  {

        setContentPane(contentPanel);

        setSize(600, 350);
        setLocationRelativeTo(parent);
        setTitle("Carregar Dades");
        btnSelecciona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fitxer;
                //Creació del selector de fitxer
                JFileChooser seleccio = new JFileChooser();
                //Mostrem la finestra de dialeg
                //Resultat emmagazema una constant que indica si s’ha
                //seleccionat o no un fitxer
                int resultat = seleccio.showOpenDialog(CarregarDades.this);
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
                    JOptionPane.showMessageDialog(CarregarDades.this, "Dades carregades des de: " + nomFitxer + ".");
                    dispose();
                } catch (CentralUBException ex) {
                    mostrarError(ex.getMessage());
                }
            }
        });


    }
    private void mostrarError(String missatge) {
        JOptionPane.showMessageDialog(this, missatge, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
