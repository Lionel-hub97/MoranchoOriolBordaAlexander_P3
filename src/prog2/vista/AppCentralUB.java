package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {
    // Atribut creat automàticament a partir del .form:

    private JPanel panelAppCentral;
    private JButton btnGestioComponentsCentral;
    private JButton btnVisualitzarInformacio;
    private JButton btnFinalitzarDia;
    private JButton btnGuardarCarregar;
    private JLabel lblNumDia;
    private JLabel lblDemandaPotencia;
    private JLabel lblGuanyAcumulat;
    private Adaptador adaptador;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB appCentralUB = new AppCentralUB();
            appCentralUB.setVisible(true);
        });
    }


    // Constructor on configures la finestra
    public AppCentralUB() {
        adaptador = new Adaptador();
        setTitle("Prova GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelAppCentral); // IMPORTANT: perquè es mostri el que has dissenyat al .form
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la finestra a la pantalla
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral dialog = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador);
                dialog.setVisible(true);

            }
        });
        btnVisualitzarInformacio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVisualitzarInformacio.mostrarDialog(AppCentralUB.this, adaptador);
            }
        });

    }
}