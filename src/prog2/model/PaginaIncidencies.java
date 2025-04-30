package prog2.model;

import java.util.ArrayList;

public class PaginaIncidencies extends PaginaBitacola {

    private ArrayList<String> incidencies;

    public PaginaIncidencies(int dia) {
        super(dia);
        incidencies = new ArrayList<>();
    }

    //GETTERS i SETTERS
    public ArrayList<String> getIncidencies() {
        return incidencies;
    }
    public void setIncidencies(ArrayList<String> incidencies) {
        this.incidencies = incidencies;
    }

    //OTROS METODOS
    public void afegeixIncidencia(String descIncidencia){
        this.incidencies.add("Descripció Incidència: "+descIncidencia);
    }

    public String imprimirIncidencies() {
        String cadena = "";
        for (String item : incidencies) {
            cadena += item + "\n";
        }
        return cadena;
    }

    public String toString(){
        return "# Pàgina Incidències\n" +
                "- Dia: "+ getDia() +"\n" + imprimirIncidencies() + "\n";
    }
}
