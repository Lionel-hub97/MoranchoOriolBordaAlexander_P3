package prog2.adaptador;


import prog2.model.*;
import prog2.vista.CentralUBException;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class Adaptador {
    private Dades dades;

    public Adaptador() {
        this.dades = new Dades();
    }

    public int getDia(){
        return dades.getDia();
    }

    public float getGuanysAcumulats() {
        return dades.getGuanysAcumulats();
    }

    public String finalitzaDia(float demandaPotencia) {
        return dades.finalitzaDia(demandaPotencia).toString();
    }

    public float getInsercioBarres() {
        return dades.getInsercioBarres();
    }

    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        dades.setInsercioBarres(insercioBarres);
    }

    public void activaReactor() throws CentralUBException {
        dades.activaReactor();
    }

    public void desactivaReactor() {
        dades.desactivaReactor();
    }

    public Reactor mostraReactor() {
        return dades.mostraReactor();
    }

    public boolean getEstatReactor() {
        return dades.getEstatReactor();
    }

    public void activaBomba(int id) throws CentralUBException {
        dades.activaBomba(id);
    }

    public void desactivaBomba(int id) {
        dades.desactivaBomba(id);
    }

    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return dades.mostraSistemaRefrigeracio();
    }

    public float calculaPotencia() {
        return dades.calculaPotencia();
    }

    public PaginaEstat mostraEstat() {
        return dades.mostraEstat();
    }

    public Bitacola mostraBitacola() {
        return dades.mostraBitacola();
    }

    public String mostraIncidencies() {
        String s = "Llista Incidencies:" + "\n";
        Iterator<PaginaIncidencies> it = dades.mostraIncidencies().iterator();
        while (it.hasNext()) {
            s += it.next() + "\n";
        }
        return s;
    }

    public void guardaDades(String camiDesti) throws CentralUBException {
        ObjectOutputStream sortida = null;
        String filename = camiDesti.endsWith(".dat") ? camiDesti : camiDesti + ".dat";

        try {
            FileOutputStream fitxer = new FileOutputStream(filename);
            sortida = new ObjectOutputStream(fitxer);

            sortida.writeObject(dades);


        } catch (IOException e) {
            throw new CentralUBException("No s'han pogut desar les dades degut a un error d'entrada/sortida: " + e.getMessage());
        } finally {
            if (sortida != null) {
                try {
                    sortida.close();
                } catch (IOException e) {
                    System.err.println("No s'ha pogut tancar el flux de sortida: " + e.getMessage());
                }
            }
        }
    }


    public void carregaDades(String camiOrigen) throws CentralUBException {
        String filename = camiOrigen.endsWith(".dat") ? camiOrigen : camiOrigen + ".dat";

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = entrada.readObject();

            if (obj instanceof Dades dadesCarregades) {
                this.dades = dadesCarregades;

            } else {
                throw new CentralUBException("El fitxer no conté dades vàlides per a aquesta aplicació.");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new CentralUBException("Error durant la càrrega de dades: " + e.getMessage());
        }
    }
    public void activarBombes() throws CentralUBException {
        mostraSistemaRefrigeracio().activa();
    }
    public void desactivarBombes() throws CentralUBException {
        mostraSistemaRefrigeracio().desactiva();
    }

    public boolean getEstatBomba(int id) throws CentralUBException {
        return dades.getEstatBomba(id);
    }

    public float getTemperatura() throws CentralUBException {
        return dades.getTemperatura();
    }

    public boolean getForaDeServeiBomba(int id) throws CentralUBException {
        return dades.getForaDeServeiBomba(id);
    }

}
