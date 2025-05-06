package prog2.model;

import prog2.vista.CentralUBException;

public class Reactor implements InComponent{

    private boolean activitat;
    private float temperatura;

    public Reactor(){}

    //GETTERS i SETTERS
    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    //METODOS IMPLEMENTADOS
    @Override
    public void activa() throws CentralUBException {
        if (1000 < temperatura){
            throw new CentralUBException("No es pot activar el reactor mentre es superi la temperatura màxima de 1.000 graus.");
        }
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
        if (1000 < temperatura){
            this.activitat = false;
            p.afegeixIncidencia("El reactor es va desactivar per superar la temperatura màxima");
        }
    }

    @Override
    public float getCostOperatiu() {
        if (activitat){
            return 35;
        }
        return 0;
    }

    @Override
    public float calculaOutput(float input) {
        if (activitat){
            return temperatura + (100 - input) * 10;
        }
        return temperatura;
    }
}
