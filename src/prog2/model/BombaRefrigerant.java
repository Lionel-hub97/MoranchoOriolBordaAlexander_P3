package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class BombaRefrigerant implements InBombaRefrigerant, Serializable {

    private VariableUniforme variableUniforme;
    private int id;
    private boolean activitat;
    private boolean foraDeServei;

    public BombaRefrigerant(VariableUniforme variableUniforme, int id) {
        this.id = id;
        this.variableUniforme = variableUniforme;
        this.activitat = false;
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
            return 250;
        }
        return 0;
    }

    @Override
    public float getCostOperatiu() {
        return 130; //En teoria el sistema de refrigeració és 130*nº Bombes, per tant retornarà 130 per fer-lo servir a sistema refrigeració
    }

    //OTROS METODOS

    public void setForaDeServeiFalse(){
        foraDeServei = false;
    }

    @Override
    public void activa() throws CentralUBException {
        if (foraDeServei)
            throw new CentralUBException("EXCEPCIO: Bomba " + (id+1) + " fora de servei, no es pot activar");
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
            this.activitat = false;
            p.afegeixIncidencia("La bomba refrigerant " + (id +1) + " està fora de servei");
        }
    }

    public String toString(){
        return "ID="+ id +", " +
                "Activitat="+ activitat +", " +
                "Fora de servei="+ foraDeServei +"\n";
    }
}
