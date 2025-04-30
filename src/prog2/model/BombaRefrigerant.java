package prog2.model;

import prog2.vista.CentralUBException;

public class BombaRefrigerant implements InBombaRefrigerant {

    private VariableUniforme variableUniforme;
    private int id;
    private boolean activitat;
    private boolean foraDeServei;

    public BombaRefrigerant(VariableUniforme variableUniforme, int id) {
        this.id = id;
        this.variableUniforme = variableUniforme;
    }

    //GETTERS i SETTERS
    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean getActivat() {
        return activitat;
    }

    @Override
    public boolean getForaDeServei() {
        return foraDeServei;
    }

    @Override
    public float getCapacitat() {
        if (activitat) {
            return
        }
        return 0;
    }

    @Override
    public float getCostOperatiu() {
        return 0;
    }

    //OTROS METODOS
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
    public void revisa(PaginaIncidencies p) {
        int x = variableUniforme.seguentValor();
        if (0 <= x && x < 24){
            this.foraDeServei = true;
            p.afegeixIncidencia("La bomba refrigerant " + id + " està fora de servei");
        }
    }
}
