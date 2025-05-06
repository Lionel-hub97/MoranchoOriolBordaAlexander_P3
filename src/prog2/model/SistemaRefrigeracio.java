package prog2.model;


import prog2.vista.CentralUBException;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void afegirBomba(BombaRefrigerant b) {
        bombesRefrigerants.add(b);
    }
    @Override
    public void activa() throws CentralUBException {
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();

            if(!bomba.getForaDeServei()){
                bomba.activa();
            }
        }
    }

    @Override
    public void desactiva() {
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();

            if(bomba.getForaDeServei() == false){
                bomba.desactiva();
            }
        }
    }

    @Override
    public boolean getActivat() {
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();

            if(bomba.getForaDeServei() == false && activitat == true){
                return true;
            }
        }
        return false;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        //NO HACE NADA
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();
            if(bomba.getForaDeServei() == false){
                bomba.revisa(p);
            }
        }
    }

    @Override
    public float getCostOperatiu() {
        float cost = 0;
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();
            if(bomba.getForaDeServei() == false){
                cost += bomba.getCostOperatiu();
            }
        }
        return cost;

    }

    @Override
    public float calculaOutput(float input) {
        float output = 0;
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();
            if(bomba.getForaDeServei() == false){
                output += bomba.getCapacitat();

            }
        }


        return Math.min(output, input);
    }
}
