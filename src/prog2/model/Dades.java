/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Daniel Ortiz
 */
public class Dades implements InDades, Serializable {


    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;

    // Afegir atributs:
    private VariableUniforme variableUniforme;
    private float insercioBarres;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia;
    private float guanysAcumulats;

    public Dades(){
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.reactor.desactiva();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;

        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);

        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);

        this.sistemaRefrigeracio.desactiva();
    }


    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////       METODOS A IMPLEMENTAR      ///////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou
     * beneficis, penalització per excès de potència, costos operatius y
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        // Completar
        float penalitzacio = 0;
        float percent = (calculaPotencia()/demandaPotencia)*100;
        float beneficis = Math.min(calculaPotencia(), demandaPotencia) * PREU_UNITAT_POTENCIA;
        if(calculaPotencia()>demandaPotencia){
            penalitzacio = PENALITZACIO_EXCES_POTENCIA;
        }
        //Barres de control, com no hi ha classe els afegim manualment
        float costOperatiu = 5;
        if(reactor.getActivat()){
            costOperatiu += reactor.getCostOperatiu();
        }
        if(sistemaRefrigeracio.getActivat()){
            costOperatiu += sistemaRefrigeracio.getCostOperatiu();
        }
        if(generadorVapor.getActivat()){
            costOperatiu += generadorVapor.getCostOperatiu();
        }
        if(turbina.getActivat()){
            costOperatiu += turbina.getCostOperatiu();
        }
        float guanysNous = beneficis - costOperatiu - penalitzacio;
        guanysAcumulats += guanysNous;

        return new PaginaEconomica(dia, demandaPotencia, calculaPotencia(), percent, beneficis, penalitzacio, costOperatiu, guanysAcumulats);
    }

    /**
     * Aquest mètode ha de establir la nova temperatura del reactor.
     */
    private void refrigeraReactor() {
        float output = reactor.calculaOutput(insercioBarres); // genera calor
        output -= sistemaRefrigeracio.calculaOutput(output); // extreu calor



        // La temperatura no pot baixar de 25º
        if (output < 25f) {
            output = 25f;
        }

        reactor.setTemperatura(output);
    }

    /**
     * Aquest mètode ha de revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void revisaComponents(PaginaIncidencies paginaIncidencies) {
        // Completar
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);

    }

    public boolean getEstatReactor() {
        return reactor.getActivat();
    }

    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);

        // Genera pàgina d'estat amb la configuració escollida (la nova pàgina
        // d'estat inclou la nova configuració escollida pel operador abans de
        // refrigerar el reactor)
        PaginaEstat paginaEstat = mostraEstat();

        // Actualitza estat de la central...

        // Refrigera el reactor
        refrigeraReactor();

        // Revisa els components de la central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);

        // Incrementa dia
        dia += 1;

        // Guarda pàgines de bitacola
        this.bitacola.afegeixPagina(paginaEconomica);
        this.bitacola.afegeixPagina(paginaEstat);
        this.bitacola.afegeixPagina(paginaIncidencies);

        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }

    @Override
    public float getInsercioBarres() {
        return insercioBarres;
    }

    @Override
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres < 0 || insercioBarres > 100) {
            throw new CentralUBException("Insercio ha de ser entre (0 - 100)");
        }
        this.insercioBarres = insercioBarres;

    }

    @Override
    public void activaReactor() throws CentralUBException {
        reactor.activa();

    }

    @Override
    public void desactivaReactor() {
        reactor.desactiva();

    }

    @Override
    public Reactor mostraReactor() {
        return reactor;
    }

    @Override
    public void activaBomba(int id) throws CentralUBException {
        Iterator<BombaRefrigerant> it = sistemaRefrigeracio.getBombesRefrigerants().iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();

            if(bomba.getId() == id){
                bomba.activa();
                break;
            }
        }

    }

    @Override
    public void desactivaBomba(int id) {
        Iterator<BombaRefrigerant> it = sistemaRefrigeracio.getBombesRefrigerants().iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();

            if(bomba.getId() == id){
                bomba.desactiva();
                break;
            }
        }
    }

    public boolean getEstatBomba(int id) throws CentralUBException {
        Iterator<BombaRefrigerant> it = sistemaRefrigeracio.getBombesRefrigerants().iterator();
        while(it.hasNext()){
            BombaRefrigerant bomba = it.next();

            if(bomba.getId() == id){
                return bomba.getActivat();

            }
        }
        throw new CentralUBException("Bomba inexistent.");
    }

    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return sistemaRefrigeracio;
    }

    @Override
    public float calculaPotencia() {
        return turbina.calculaOutput(generadorVapor.calculaOutput(sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres))));
    }

    @Override
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    @Override
    public PaginaEstat mostraEstat() {
        float outReact = reactor.calculaOutput(insercioBarres);
        float outSist = sistemaRefrigeracio.calculaOutput(outReact);
        float outVap = generadorVapor.calculaOutput(outSist);
        float outTurb = turbina.calculaOutput(outVap);
        return new PaginaEstat(dia,insercioBarres, outReact, outSist, outVap, outTurb);
    }

    @Override
    public Bitacola mostraBitacola() {
        return bitacola;

    }

    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        return bitacola.getIncidencies();
    }

}
