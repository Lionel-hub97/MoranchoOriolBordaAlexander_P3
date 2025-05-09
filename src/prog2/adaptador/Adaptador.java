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

    public float getGuanysAcumulats() {
        return dades.getGuanysAcumulats();
    }

    public PaginaEstat mostraEstat() {
        return dades.mostraEstat();
    }

    public Bitacola mostraBitacola() {
        return dades.mostraBitacola();
    }

    public List<PaginaIncidencies> mostraIncidencies() {
        return dades.mostraIncidencies();
    }

    public void guardaDades(String camiDesti) throws CentralUBException {
        ObjectOutputStream sortida = null;

        try {
            FileOutputStream fitxer = new FileOutputStream(camiDesti);
            sortida = new ObjectOutputStream(fitxer);

            sortida.writeObject(dades);

            System.out.println("S'han guardat les dades correctament al fitxer: " + camiDesti);
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
        try {
            FileInputStream fitxer = new FileInputStream(camiOrigen);
            ObjectInputStream entrada = new ObjectInputStream(fitxer);

            Object obj = entrada.readObject();

            if (obj instanceof Dades dadesCarregades) {
                this.dades = dadesCarregades;
                System.out.println("Dades carregades des de: " + camiOrigen);
            } else {
                throw new CentralUBException("El fitxer no conté dades vàlides per a aquesta aplicació.");
            }

            entrada.close();
            fitxer.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new CentralUBException("Error durant la càrrega de dades: " + e.getMessage());
        }
    }

}
