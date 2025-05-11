package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Bitacola implements InBitacola, Serializable {
    private ArrayList<PaginaBitacola> paginesBitacoles;
    public Bitacola() {
        paginesBitacoles = new ArrayList<>();
    }
    @Override
    public void afegeixPagina(PaginaBitacola p) {

        paginesBitacoles.add(p);
    }

    @Override
    public List<PaginaIncidencies> getIncidencies() {
        ArrayList<PaginaIncidencies> incidencies = new ArrayList<>();
        Iterator<PaginaBitacola> it = paginesBitacoles.iterator();
        while(it.hasNext()){
            PaginaBitacola p = it.next();
            if(p instanceof PaginaIncidencies){
                incidencies.add((PaginaIncidencies)p);
            }
        }
        return incidencies;
    }
    public String toString() {
        StringBuilder res = new StringBuilder();

        Iterator<PaginaBitacola> it = paginesBitacoles.iterator();
        while(it.hasNext()){
            PaginaBitacola p = it.next();
            res.append(p.toString());
            if(it.hasNext()){
                res.append("\n");
            }
        }
        return res.toString();
    }
}