package prog2.model;



public class Turbina implements InComponent{

    private boolean activitat;

    public Turbina() {
        this.activitat = true;
    }

    //METODOS IMPLEMENTADOS

    @Override
    public void activa() {
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
        //NO HACE NADA
    }

    @Override
    public float getCostOperatiu() {
        return 20;
    }

    @Override
    public float calculaOutput(float input) {
        if(input > 100 && activitat){
            return input*2;
        } else {
            return 0;

        }
        //output =
        // si no activat                        ->  0
        // si activat i input < 100             ->  0
        // si activat i input ≥ 100             ->  input ∗ 2
    }
}
