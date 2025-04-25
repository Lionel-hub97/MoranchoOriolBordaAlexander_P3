package prog2.model;

import prog2.vista.CentralUBException;

public class BombaRefrigerant implements InBombaRefrigerant {

    private VariableUniforme variableUniforme;
    private int id;
    private boolean activitat;
    private boolean foraDeServei;

    public BombaRefrigerant(int id, VariableUniforme variableUniforme) {
        this.id = id;
        this.variableUniforme = variableUniforme;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void activa() throws CentralUBException {
        if (foraDeServei)
            throw new CentralUBException("EXCEPCIO: Central fora de servei");
        this.activitat = true;
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
    public boolean getForaDeServei() {
        return false;
    }

    @Override
    public float getCapacitat() {
        return 0;
    }

    @Override
    public float getCostOperatiu() {
        return 0;
    }
}
