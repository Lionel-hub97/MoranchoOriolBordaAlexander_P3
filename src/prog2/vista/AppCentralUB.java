package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {
    // Components del .form
    private JPanel panelAppCentral;
    private JButton btnGestioComponentsCentral;
    private JButton btnVisualitzarInformacio;
    private JButton btnFinalitzarDia;
    private JLabel lblNumDia;
    private JLabel lblDemandaPotencia;
    private JLabel lblGuanyAcumulat;
    private JButton btnGuardar;
    private JButton btnCarregar;
    private JCheckBox chkModeEstetic;

    // Altres atributs
    private Adaptador adaptador;
    private VariableNormal variableNormal;
    private float demandaPotencia;

    // Constructor
    public AppCentralUB() {
        adaptador = new Adaptador();
        variableNormal = new VariableNormal(1000, 800, 123);
        demandaPotencia = generaDemandaPotencia();

        setTitle("Prova GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelAppCentral);
        setSize(700, 600);
        setLocationRelativeTo(null);

        actualizarLabelDia();
        actualizarLabelDemanda();
        actualizarLabelGuanys();

        btnGestioComponentsCentral.addActionListener(e -> {
            try {
                FrmGestioComponentsCentral dialog = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador, chkModeEstetic.isSelected());
                dialog.setVisible(true);
            } catch (CentralUBException ex) {
                JOptionPane.showMessageDialog(AppCentralUB.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVisualitzarInformacio.addActionListener(e -> FrmVisualitzarInformacio.mostrarDialog(AppCentralUB.this, adaptador));

        btnFinalitzarDia.addActionListener(e -> {
            finalitzaDia();
            actualizarLabelDia();
        });

        btnCarregar.addActionListener(e -> {
            try {
                FrmCarregarDades frmcarregarDades = new FrmCarregarDades(AppCentralUB.this, adaptador);
                frmcarregarDades.setVisible(true);
                actualizarLabelDia();
                actualizarLabelDemanda();
                actualizarLabelGuanys();
            } catch (CentralUBException ex) {
                JOptionPane.showMessageDialog(AppCentralUB.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnGuardar.addActionListener(e -> {
            try {
                FrmGuardarDades frmGuardarDades = new FrmGuardarDades(AppCentralUB.this, adaptador);
                frmGuardarDades.setVisible(true);
            } catch (CentralUBException ex) {
                JOptionPane.showMessageDialog(AppCentralUB.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 🎨 Mode estètic activat/desactivat
        chkModeEstetic.addActionListener(e -> {
            boolean activat = chkModeEstetic.isSelected();

            // Colors estètics
            Color fonsBonic = new Color(230, 240, 255); // Blau clar
            Color botoBonic = new Color(80, 120, 200);  // Blau fosc
            Color textBlanc = Color.WHITE;

            JButton[] botons = {
                    btnGestioComponentsCentral,
                    btnVisualitzarInformacio,
                    btnFinalitzarDia,
                    btnGuardar,
                    btnCarregar
            };

            if (activat) {
                panelAppCentral.setBackground(fonsBonic);
                for (JButton boto : botons) {
                    boto.setBackground(botoBonic);
                    boto.setForeground(textBlanc);
                    boto.setFocusPainted(false);
                    boto.setBorderPainted(false);
                }
            } else {
                panelAppCentral.setBackground(null);
                for (JButton boto : botons) {
                    boto.setBackground(null);
                    boto.setForeground(null);
                    boto.setFocusPainted(true);
                    boto.setBorderPainted(true);
                }
            }
        });
    }

    // Mètodes auxiliars
    private void actualizarLabelDia() {
        lblNumDia.setText("Dia: " + adaptador.getDia());
    }

    private void actualizarLabelDemanda() {
        lblDemandaPotencia.setText("Demanda: " + demandaPotencia);
    }

    private void actualizarLabelGuanys() {
        lblGuanyAcumulat.setText("Guanys: " + adaptador.getGuanysAcumulats());
    }

    private float generaDemandaPotencia() {
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > 1800) return 1800;
        else if (valor < 250) return 250;
        else return valor;
    }

    private void finalitzaDia() {
        String info = adaptador.finalitzaDia(demandaPotencia);
        JOptionPane.showMessageDialog(this, info, "Dia finalitzat", JOptionPane.INFORMATION_MESSAGE);

        demandaPotencia = generaDemandaPotencia();
        actualizarLabelDia();
        actualizarLabelDemanda();
        actualizarLabelGuanys();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB appCentralUB = new AppCentralUB();
            appCentralUB.setVisible(true);
        });
    }
}
