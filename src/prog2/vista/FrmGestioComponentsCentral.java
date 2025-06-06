package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioComponentsCentral extends JDialog {
    private JPanel contentPane;
    private JButton btnAplicarModificacions;
    private JButton btnCancelarModificacions;
    private JSlider sldBarresControl;
    private JTextField txtIntroduirInsercioBarresControl;
    private JButton btnIntroduirInsercioBarresControl;
    private JLabel lblPercentarge;
    private JButton btnActivarReactor;
    private JButton btnDesactivarReactor;
    private JButton btnBomba0;
    private JButton btnBomba3;
    private JButton btnBomba2;
    private JButton btnBomba1;
    private JButton btnElementsForaServei;

    private Adaptador adaptador;
    private boolean bomba0, bomba1, bomba2, bomba3;
    private boolean bomba0Orig, bomba1Orig, bomba2Orig, bomba3Orig;
    private float valor;

    private boolean estatReactor;

    public FrmGestioComponentsCentral(JFrame parent, Adaptador adaptador, boolean modeEstetic) throws CentralUBException {
        super(parent, "Gestio Central");

        setContentPane(contentPane);

        setSize(600, 350);
        setLocationRelativeTo(parent);
        setModal(true);
        if (modeEstetic) {
            aplicaEstilEstetic();
        }
        estatReactor = adaptador.getEstatReactor();

        valor = adaptador.getInsercioBarres();
        sldBarresControl.setValue((int) adaptador.getInsercioBarres());
        lblPercentarge.setText(adaptador.getInsercioBarres() + "%");

        this.adaptador = adaptador;

        configuraBomba(btnBomba0, 0);
        configuraBomba(btnBomba1, 1);
        configuraBomba(btnBomba2, 2);
        configuraBomba(btnBomba3, 3);

        if (estatReactor) {
            btnActivarReactor.setSelected(true);
            btnDesactivarReactor.setSelected(false);
        } else {
            btnActivarReactor.setSelected(false);
            btnDesactivarReactor.setSelected(true);

        }
        DefaultListModel<String> model = new DefaultListModel<>();






        sldBarresControl.addChangeListener(e -> {
            lblPercentarge.setText(sldBarresControl.getValue() + "%");
            valor = sldBarresControl.getValue();
        });

        btnIntroduirInsercioBarresControl.addActionListener(e -> {
            try {
                valor = Float.parseFloat(txtIntroduirInsercioBarresControl.getText().replace(',', '.'));
                if (valor >= 0.0f && valor <= 100.0f) {
                    sldBarresControl.setValue((int) valor);
                    lblPercentarge.setText(valor + "%");
                } else {
                    mostrarError("El valor ha d'estar entre 0.0 i 100.0");
                }
            } catch (NumberFormatException ex) {
                mostrarError("Has d'introduir un número decimal vàlid.");
            }
        });

        btnActivarReactor.addActionListener(e -> {
            try {
                if(adaptador.getTemperatura() > 1000) {
                    mostrarError("No es pot activar el reactor mentre es superi la temperatura màxima de 1.000 graus.");

                }
                else if(adaptador.getEstatReactor()) {
                    JOptionPane.showMessageDialog(this, "El reactor ja estava activat.", "Gestió reactor", JOptionPane.INFORMATION_MESSAGE);


                }
                else{
                    estatReactor = true;
                    JOptionPane.showMessageDialog(this, "Reactor pendent de ser activat", "Gestió reactor", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (CentralUBException ex) {
                mostrarError(ex.getMessage());
            }

        });

        btnDesactivarReactor.addActionListener(e -> {
            if(!adaptador.getEstatReactor()) {
                JOptionPane.showMessageDialog(this, "El reactor ja estava desactivat.", "Gestió reactor", JOptionPane.INFORMATION_MESSAGE);


            }
            else{
                estatReactor = false;
                JOptionPane.showMessageDialog(this, "Reactor pendent de ser desactivat", "Gestió reactor", JOptionPane.INFORMATION_MESSAGE);

            }

        });

        btnAplicarModificacions.addActionListener(e -> {
            float valorOriginalBarres = adaptador.getInsercioBarres();
            boolean reactorOriginal = adaptador.getEstatReactor();

            boolean canviBombes = false;
            boolean canviBarres = false;
            boolean canviReactor = false;
            boolean hiHaError = false;

            // BOMBA 0
            if (bomba0 != bomba0Orig) {
                try {
                    if (bomba0) {
                        adaptador.activaBomba(0);
                    } else {
                        adaptador.desactivaBomba(0);
                    }
                    canviBombes = true;
                } catch (CentralUBException ex) {
                    mostrarError("Bomba 1: " + ex.getMessage());
                    bomba0 = false;
                    actualitzaBotonBomba(btnBomba0, false, 0);
                    hiHaError = true;
                }
            }

            // BOMBA 1
            if (bomba1 != bomba1Orig) {
                try {
                    if (bomba1) {
                        adaptador.activaBomba(1);
                    } else {
                        adaptador.desactivaBomba(1);
                    }
                    canviBombes = true;
                } catch (CentralUBException ex) {
                    mostrarError("Bomba 2: " + ex.getMessage());
                    bomba1 = false;
                    actualitzaBotonBomba(btnBomba1, false, 1);
                    hiHaError = true;
                }
            }

            // BOMBA 2
            if (bomba2 != bomba2Orig) {
                try {
                    if (bomba2) {
                        adaptador.activaBomba(2);
                    } else {
                        adaptador.desactivaBomba(2);
                    }
                    canviBombes = true;
                } catch (CentralUBException ex) {
                    mostrarError("Bomba 3: " + ex.getMessage());
                    bomba2 = false;
                    actualitzaBotonBomba(btnBomba2, false, 2);
                    hiHaError = true;
                }
            }

            // BOMBA 3
            if (bomba3 != bomba3Orig) {
                try {
                    if (bomba3) {
                        adaptador.activaBomba(3);
                    } else {
                        adaptador.desactivaBomba(3);
                    }
                    canviBombes = true;
                } catch (CentralUBException ex) {
                    mostrarError("Bomba 4: " + ex.getMessage());
                    bomba3 = false;
                    actualitzaBotonBomba(btnBomba3, false, 3);
                    hiHaError = true;
                }
            }

            // REACTOR
            if (estatReactor != reactorOriginal) {
                try {
                    if (estatReactor) {
                        adaptador.activaReactor();
                    } else {
                        adaptador.desactivaReactor();
                    }
                    canviReactor = true;
                } catch (CentralUBException ex) {
                    mostrarError("Reactor: " + ex.getMessage());
                    hiHaError = true;
                }
            }

            // BARRES DE CONTROL
            if (valor != valorOriginalBarres) {
                try {
                    adaptador.setInsercioBarres(valor);
                    canviBarres = true;
                } catch (CentralUBException ex) {
                    mostrarError("Barres de control: " + ex.getMessage());
                    hiHaError = true;
                }
            }

            // Missatge de NO CANVIS
            if (!canviBarres && !canviReactor && !canviBombes) {
                if (!hiHaError) {
                    JOptionPane.showMessageDialog(this,
                            "No s'han produït canvis.",
                            "Sense canvis",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                return;
            }

            // Missatge de CANVIS APLICATS
            String missatge = "Modificacions aplicades:";
            if (canviBombes) missatge += "\n- Estat bombes actualitzat";
            if (canviBarres) missatge += "\n- Percentatge de barres: " + valor + "%";
            if (canviReactor) missatge += "\n- Reactor " + (estatReactor ? "Activat" : "Desactivat");

            JOptionPane.showMessageDialog(this, missatge, "Canvis aplicats", JOptionPane.INFORMATION_MESSAGE);
        });



        btnCancelarModificacions.addActionListener(e -> dispose());
        btnElementsForaServei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder missatge = new StringBuilder("Elements fora de servei:");

                boolean algunForaServei = false;

                try {
                    // Comprovació del reactor
                    if (adaptador.getTemperatura() > 1000) {
                        missatge.append("\n- Reactor");
                        algunForaServei = true;
                    }

                    // Comprovació de les bombes
                    if (adaptador.getForaDeServeiBomba(0)) {
                        missatge.append("\n- Bomba 1");
                        algunForaServei = true;
                    }
                    if (adaptador.getForaDeServeiBomba(1)) {
                        missatge.append("\n- Bomba 2");
                        algunForaServei = true;
                    }
                    if (adaptador.getForaDeServeiBomba(2)) {
                        missatge.append("\n- Bomba 3");
                        algunForaServei = true;
                    }
                    if (adaptador.getForaDeServeiBomba(3)) {
                        missatge.append("\n- Bomba 4");
                        algunForaServei = true;
                    }

                    // Si no hi ha res fora de servei
                    if (!algunForaServei) {
                        missatge = new StringBuilder("Tots els components estan actius.");
                    }

                    JOptionPane.showMessageDialog(
                            FrmGestioComponentsCentral.this,
                            missatge.toString(),
                            "Estat components",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } catch (CentralUBException ex) {
                    mostrarError(ex.getMessage());
                }
            }
        });

    }

    private void configuraBomba(JButton btn, int index) throws CentralUBException {
        boolean estat = adaptador.getEstatBomba(index);
        switch (index) {
            case 0 -> bomba0Orig = bomba0 = estat;
            case 1 -> bomba1Orig = bomba1 = estat;
            case 2 -> bomba2Orig = bomba2 = estat;
            case 3 -> bomba3Orig = bomba3 = estat;
        }

        actualitzaBotonBomba(btn, estat, index);

        btn.addActionListener(e -> {
            boolean nouEstat;
            switch (index) {
                case 0 -> {
                    bomba0 = !bomba0;
                    nouEstat = bomba0;
                }
                case 1 -> {
                    bomba1 = !bomba1;
                    nouEstat = bomba1;
                }
                case 2 -> {
                    bomba2 = !bomba2;
                    nouEstat = bomba2;
                }
                case 3 -> {
                    bomba3 = !bomba3;
                    nouEstat = bomba3;
                }
                default -> throw new IllegalStateException("Índex de bomba no vàlid: " + index);
            }
            actualitzaBotonBomba(btn, nouEstat, index);
        });
    }

    private void actualitzaBotonBomba(JButton btn, boolean estat, int index) {
        btn.setBackground(estat ? Color.GREEN : Color.RED);
        btn.setText("Bomba " + (index + 1) + ": " + (estat ? "Activada" : "Desactivada"));
    }

    private void mostrarError(String missatge) {
        JOptionPane.showMessageDialog(this, missatge, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void createUIComponents() {
        // Per si s'han de definir components personalitzats
    }private void aplicaEstilEstetic() {
        Color fons = new Color(245, 250, 255);
        Color boto = new Color(100, 140, 220);
        Color text = Color.WHITE;

        contentPane.setBackground(fons);

        JButton[] botons = {
                btnAplicarModificacions,
                btnCancelarModificacions,
                btnIntroduirInsercioBarresControl,
                btnActivarReactor,
                btnDesactivarReactor,

                btnElementsForaServei
        };

        for (JButton b : botons) {
            b.setBackground(boto);
            b.setForeground(text);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
        }
    }

}
