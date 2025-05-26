package prog2.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import prog2.adaptador.Adaptador;

public class FrmVisualitzarInformacio extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox<String> cmboxOpcionsVisualitzar;
    private JButton btnVisualitzar;
    private JTextArea txtAreaInformacio;
    private JPanel Panel1;
    private JPanel Panel2;
    private final Adaptador adaptador;

    public FrmVisualitzarInformacio(JFrame parent, Adaptador adaptador) {
        super(parent, "Visualitzar Informació de la Central", true);
        this.adaptador = adaptador;

        setContentPane(contentPane);
        setModal(true);
        setSize(600, 500);
        setLocationRelativeTo(parent);

        configurarComboBox();
        configurarTextArea();
        configurarBotones();

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });
    }

    private void configurarComboBox() {
        cmboxOpcionsVisualitzar.setModel(new DefaultComboBoxModel<>(new String[]{
                "Estat de la central",
                "Quadern de bitàcola",
                "Incidències"
        }));

        // Mostrar información automáticamente al cambiar la opción
        //cmboxOpcionsVisualitzar.addActionListener(e -> btnVisualitzar.doClick());
    }

    private void configurarTextArea() {
        txtAreaInformacio.setEditable(false);
        txtAreaInformacio.setLineWrap(true);
        txtAreaInformacio.setWrapStyleWord(true);
        txtAreaInformacio.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void configurarBotones() {
        btnVisualitzar.addActionListener(e -> mostrarInformacionSeleccionada());
    }

    private void mostrarInformacionSeleccionada() {
        String opcio = (String) cmboxOpcionsVisualitzar.getSelectedItem();
        String informacio;

        try {
            switch (opcio) {
                case "Estat de la central":
                    informacio = adaptador.mostraEstat().toString();
                    break;
                case "Quadern de bitàcola":
                    informacio = adaptador.mostraBitacola().toString();
                    break;
                case "Incidències":
                    informacio = adaptador.mostraIncidencies();
                    break;
                default:
                    informacio = "Opció no reconeguda";
            }

            txtAreaInformacio.setText(informacio);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtenir la informació: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onOK() {
        dispose();
    }

    // Método estático para mostrar el diálogo
    public static void mostrarDialog(JFrame parent, Adaptador adaptador) {
        FrmVisualitzarInformacio dialog = new FrmVisualitzarInformacio(parent, adaptador);
        dialog.setVisible(true);
    }
}
