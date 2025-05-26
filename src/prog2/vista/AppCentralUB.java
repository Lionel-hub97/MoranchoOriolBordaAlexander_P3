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
    private JLabel lblNumDia;
    private JLabel lblDemandaPotencia;
    private JLabel lblGuanyAcumulat;
    private JButton btnGuardar;
    private JButton btnCarregar;
    private Adaptador adaptador;

    private VariableNormal variableNormal;
    private float demandaPotencia;

    // Constructor on configures la finestra
    public AppCentralUB() {
        adaptador = new Adaptador();

        variableNormal = new VariableNormal(1000, 800, 123);  // mismos valores que en consola
        demandaPotencia = generaDemandaPotencia();  // primer valor del día

        setTitle("Prova GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelAppCentral); // IMPORTANT: perquè es mostri el que has dissenyat al .form
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la finestra a la pantalla

        actualizarLabelDia();  // Actualiza el label al inicio
        actualizarLabelDemanda();
        actualizarLabelGuanys();

        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral dialog = null;
                try {
                    dialog = new FrmGestioComponentsCentral(AppCentralUB.this, adaptador);
                    dialog.setVisible(true);
                } catch (CentralUBException ex) {
                    System.err.println(ex.getMessage());
                }


            }
        });

        btnVisualitzarInformacio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVisualitzarInformacio.mostrarDialog(AppCentralUB.this, adaptador);
            }
        });

        btnFinalitzarDia.addActionListener(e -> {
            finalitzaDia();           // Avanza el día en el adaptador (como antes en consola)
            actualizarLabelDia();     // Refresca el label con el nuevo número de día
        });
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarregarDades carregarDades = null;

                carregarDades = new CarregarDades(AppCentralUB.this, adaptador);
                carregarDades.setVisible(true);
            }
        });
    }

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

        demandaPotencia = generaDemandaPotencia();  // nueva demanda

        // Actualizamos los labels
        actualizarLabelDia();
        actualizarLabelDemanda();
        actualizarLabelGuanys(); // si tienes ganancias acumuladas
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////     MAIN     ////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB appCentralUB = new AppCentralUB();
            appCentralUB.setVisible(true);
        });
    }
}