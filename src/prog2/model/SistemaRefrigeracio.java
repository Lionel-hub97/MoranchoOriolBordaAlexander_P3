package prog2.model;


import prog2.vista.CentralUBException;

import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent{

    private boolean activitat;
    private ArrayList<BombaRefrigerant> bombesRefrigerants;

    public SistemaRefrigeracio(){
        bombesRefrigerants = new ArrayList<>();
    }

    //GETTERS i SETTERS
    public ArrayList<BombaRefrigerant> getBombesRefrigerants() {
        return bombesRefrigerants;
    }

    public void setBombesRefrigerants(ArrayList<BombaRefrigerant> bombesRefrigerants) {
        this.bombesRefrigerants = bombesRefrigerants;
    }

    //METODOS IMPLEMENTADOS
    @Override
    public void activa() throws CentralUBException {

    }

    @Override
    public void desactiva() {
        this.activitat = false;
    }

    @Override
    public boolean getActivat() {
        return activitat;
    }

    @Override
    public void revisa(PaginaIncidencies p) {

    }

    @Override
    public float getCostOperatiu() {
        return 0;
    }

    @Override
    public float calculaOutput(float input) {
        return 0;
    }
}
