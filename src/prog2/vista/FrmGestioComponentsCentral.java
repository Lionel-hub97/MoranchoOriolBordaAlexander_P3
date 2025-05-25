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
    private JButton btnBomba1;
    private JButton btnBomba4;
    private JButton btnBomba3;
    private JButton btnBomba2;
    private Adaptador adaptador;
    private boolean bomba1, bomba2, bomba3, bomba4;
    private boolean bomba1Orig, bomba2Orig, bomba3Orig, bomba4Orig;
    private float valor;

    private boolean estatReactor;

    public FrmGestioComponentsCentral(JFrame parent, Adaptador adaptador) throws CentralUBException {
        super(parent, "Gestio Central");
        setContentPane(contentPane);

        setSize(600, 350);
        setLocationRelativeTo(parent);
        setModal(true);
        estatReactor = adaptador.getEstatReactor();

        sldBarresControl.setValue((int)adaptador.getInsercioBarres());
        lblPercentarge.setText(adaptador.getInsercioBarres() + "%");

        this.adaptador = adaptador;

        configuraBomba(btnBomba1, 0);
        configuraBomba(btnBomba2, 1);
        configuraBomba(btnBomba3, 2);
        configuraBomba(btnBomba4, 3);

        if(estatReactor) {
            btnActivarReactor.setSelected(true);
            btnDesactivarReactor.setSelected(false);
        } else{
            btnActivarReactor.setSelected(false);
            btnDesactivarReactor.setSelected(true);
        }

        sldBarresControl.addChangeListener(e -> {
            lblPercentarge.setText(sldBarresControl.getValue() + "%");
            valor = sldBarresControl.getValue();
        });

        btnIntroduirInsercioBarresControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    valor = Float.parseFloat(txtIntroduirInsercioBarresControl.getText().replace(',', '.'));
                    if (valor >= 0.0f && valor <= 100.0f) {
                        float valor2 = valor;

                        sldBarresControl.setValue((int)valor);
                        lblPercentarge.setText(valor2 + "%");
                        valor = valor2;
                    } else {
                        JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this,
                                "El valor ha d'estar entre 0.0 i 100.0",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this,
                            "Has d'introduir un número decimal vàlid.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnActivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estatReactor = true;
                JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this,
                        "Reactor pendent de ser activat",
                        "Gestió reactor",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });

        btnDesactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estatReactor = false;
                JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this,
                        "Reactor pendent de ser desactivat",
                        "Gestió reactor",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnAplicarModificacions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                float valorOriginalBarres = adaptador.getInsercioBarres();
                boolean reactorOriginal = adaptador.getEstatReactor();
                boolean canviBombes = false;

                boolean canviBarres = valor != valorOriginalBarres;
                boolean canviReactor = estatReactor != reactorOriginal;
                if(bomba1 && !bomba1Orig){
                    try {
                        adaptador.activaBomba(0);
                        canviBombes = true;
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if(bomba1Orig){
                    adaptador.desactivaBomba(0);
                    canviBombes = true;
                }

                if(bomba2 && !bomba2Orig){
                    try {
                        adaptador.activaBomba(1);
                        canviBombes = true;
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if(bomba2Orig){
                    adaptador.desactivaBomba(1);
                    canviBombes = true;
                }
                if(bomba3 && !bomba3Orig){
                    try {
                        adaptador.activaBomba(2);
                        canviBombes = true;
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if(bomba3Orig){
                    adaptador.desactivaBomba(2);
                    canviBombes = true;
                }

                if(bomba4 && !bomba4Orig){
                    try {
                        adaptador.activaBomba(3);
                        canviBombes = true;
                    } catch (CentralUBException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if(bomba4Orig){
                    adaptador.desactivaBomba(3);
                    canviBombes = true;
                }

                if (!canviBarres && !canviReactor && !canviBombes) {
                    JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this,
                            "No s'han produït canvis. ",
                            "Sense canvis",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    return;
                }

                if (canviReactor) {
                    try {
                        if (estatReactor) {
                            adaptador.activaReactor();
                        } else {
                            adaptador.desactivaReactor();
                        }
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this,
                                ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (canviBarres) {
                    try {
                        adaptador.setInsercioBarres(valor);
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this,
                                ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }

                String missatge = "Modificacions aplicades: " + "\nEstat bombes actualitzat";
                if (canviBarres) missatge += "\nPercentatge de barres: " + valor + "%";
                if (canviReactor) missatge += "\nReactor " + (estatReactor ? "Activat" : "Desactivat");

                JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this,
                        missatge,
                        "Canvis aplicats",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        btnCancelarModificacions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void configuraBomba(JButton btn, int index) throws CentralUBException {
        boolean estat = adaptador.getEstatBomba(index);
        switch (index) {
            case 0 -> bomba1Orig = bomba1 = estat;
            case 1 -> bomba2Orig = bomba2 = estat;
            case 2 -> bomba3Orig = bomba3 = estat;
            case 3 -> bomba4Orig = bomba4 = estat;
        }

        actualitzaBotonBomba(btn, estat, index);

        btn.addActionListener(e -> {
            boolean nouEstat;
            switch (index) {
                case 0 -> {
                    bomba1 = !bomba1;
                    nouEstat = bomba1;
                }
                case 1 -> {
                    bomba2 = !bomba2;
                    nouEstat = bomba2;
                }
                case 2 -> {
                    bomba3 = !bomba3;
                    nouEstat = bomba3;
                }
                case 3 -> {
                    bomba4 = !bomba4;
                    nouEstat = bomba4;
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
