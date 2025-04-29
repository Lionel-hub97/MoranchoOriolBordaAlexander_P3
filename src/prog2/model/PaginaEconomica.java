package prog2.model;

public class PaginaEconomica extends PaginaBitacola{

    private float potencia;
    private float potenciaGenerada;
    private float percentatgePotenciaSatisfeta;
    private float beneficis;
    private float penalitzacioExcesProduccio;
    private float costOperatiu;
    private float guanysAcumulats;

    public PaginaEconomica(int dia, float potencia, float potenciaGenerada, float percentatgePotenciaSatisfeta, float beneficis, float penalitzacioExcesProduccio, float costOperatiu, float guanysAcumulats) {
        super(dia);
        this.potencia = potencia;
        this.potenciaGenerada = potenciaGenerada;
        this.percentatgePotenciaSatisfeta = percentatgePotenciaSatisfeta;
        this.beneficis = beneficis;
        this.penalitzacioExcesProduccio = penalitzacioExcesProduccio;
        this.costOperatiu = costOperatiu;
        this.guanysAcumulats = guanysAcumulats;
    }

    //GETTERS i SETTERS
    public float getPotencia() {
        return potencia;
    }
    public void setPotencia(float potencia) {
        this.potencia = potencia;
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
}
