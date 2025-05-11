package prog2.model;

import java.io.Serializable;

public class PaginaEconomica extends PaginaBitacola implements Serializable {

    private float demandaPotencia;
    private float potenciaGenerada;
    private float percentatgePotenciaSatisfeta;
    private float beneficis;
    private float penalitzacioExcesProduccio;
    private float costOperatiu;
    private float guanysAcumulats;

    public PaginaEconomica(int dia, float potencia, float potenciaGenerada, float percentatgePotenciaSatisfeta, float beneficis, float penalitzacioExcesProduccio, float costOperatiu, float guanysAcumulats) {
        super(dia);
        this.demandaPotencia = potencia;
        this.potenciaGenerada = potenciaGenerada;
        this.percentatgePotenciaSatisfeta = percentatgePotenciaSatisfeta;
        this.beneficis = beneficis;
        this.penalitzacioExcesProduccio = penalitzacioExcesProduccio;
        this.costOperatiu = costOperatiu;
        this.guanysAcumulats = guanysAcumulats;
    }

    //GETTERS i SETTERS
    public float getDemandaPotencia() {
        return demandaPotencia;
    }
    public void setDemandaPotencia(float demandaPotencia) {
        this.demandaPotencia = demandaPotencia;
    }
    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }
    public void setPotenciaGenerada(float potenciaGenerada) {
        this.potenciaGenerada = potenciaGenerada;
    }
    public float getPercentatgePotenciaSatisfeta() {
        return percentatgePotenciaSatisfeta;
    }
    public void setPercentatgePotenciaSatisfeta(float percentatgePotenciaSatisfeta) {
        this.percentatgePotenciaSatisfeta = percentatgePotenciaSatisfeta;
    }
    public float getBeneficis() {
        return beneficis;
    }
    public void setBeneficis(float beneficis) {
        this.beneficis = beneficis;
    }
    public float getPenalitzacioExcesProduccio() {
        return penalitzacioExcesProduccio;
    }
    public void setPenalitzacioExcesProduccio(float penalitzacioExcesProduccio) {
        this.penalitzacioExcesProduccio = penalitzacioExcesProduccio;
    }
    public float getCostOperatiu() {
        return costOperatiu;
    }
    public void setCostOperatiu(float costOperatiu) {
        this.costOperatiu = costOperatiu;
    }
    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }
    public void setGuanysAcumulats(float guanysAcumulats) {
        this.guanysAcumulats = guanysAcumulats;
    }

    //OTROS METODOS
    public String toString(){
        return "# Pàgina Econòmica\n" +
                "- Dia: "+ getDia() +"\n" +
                "- Demanda de Potència: "+ getDemandaPotencia() +"\n" +
                "- Potència Generada: "+ getPotenciaGenerada() +"\n" +
                "- Demanda de Potència Satisfeta: "+ getPercentatgePotenciaSatisfeta() +" %\n" +
                "- Beneficis: "+ getBeneficis() +" Unitats Econòmiques\n" +
                "- Penalització Excés Producció: "+ getPenalitzacioExcesProduccio() +" Unitats Econòmiques\n" +
                "- Cost Operatiu: "+ getCostOperatiu() +" Unitats Econòmiques\n" +
                "- Guanys acumulats: "+ getGuanysAcumulats() +" Unitats Econòmiques\n";
    }
}
