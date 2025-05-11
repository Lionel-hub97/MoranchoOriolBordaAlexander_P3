package prog2.model;


import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SistemaRefrigeracio implements InComponent, Serializable {

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

            if(!bomba.getForaDeServei()){
                bomba.desactiva();
            }
        }
    }

    @Override
    public boolean getActivat() {
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();

            if(!bomba.getForaDeServei() && bomba.getActivat()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();
            if(!bomba.getForaDeServei()){
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
            if(!bomba.getForaDeServei()){
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
            if(!bomba.getForaDeServei()){
                output += bomba.getCapacitat();

            }
        }


        return Math.min(output, input);
    }
    @Override
    public String toString() {
        String s = "";
        StringBuilder sb = new StringBuilder();
        s += "Sistema de Refrigeració:\n";
        Iterator<BombaRefrigerant> it = bombesRefrigerants.iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();
            s += "Bomba ID: " + bomba.getId() + " | Estat: " + (bomba.getActivat() ? "Activada" : "Desactivada") + "\n";
        }


        return s;
    }
}


