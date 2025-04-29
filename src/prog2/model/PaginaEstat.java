package prog2.model;

public class PaginaEstat extends PaginaBitacola {

    private float percentatgeInsercioBarres;
    private float grausReactor;
    private float grausSistemaRefrigeracio;
    private float grausGeneradorVapor;
    private float grausTurbina;

    public PaginaEstat(int dia, float grauInsercio, float grausReactor, float grausSistemaRefrigeracio, float grausGeneradorVapor, float grausTurbina) {
        super(dia);
        this.percentatgeInsercioBarres = grauInsercio;
        this.grausReactor = grausReactor;
        this.grausSistemaRefrigeracio = grausSistemaRefrigeracio;
        this.grausGeneradorVapor = grausGeneradorVapor;
        this.grausTurbina = grausTurbina;
    }

    //GETERS i SETTERS
    public float getPercentatgeInsercioBarres() {
        return percentatgeInsercioBarres;
    }
    public void setPercentatgeInsercioBarres(float percentatgeInsercioBarres) {
        this.percentatgeInsercioBarres = percentatgeInsercioBarres;
    }
    public float getGrausReactor() {
        return grausReactor;
    }
    public void setGrausReactor(float grausReactor) {
        this.grausReactor = grausReactor;
    }
    public float getGrausSistemaRefrigeracio() {
        return grausSistemaRefrigeracio;
    }
    public void setGrausSistemaRefrigeracio(float grausSistemaRefrigeracio) {
        this.grausSistemaRefrigeracio = grausSistemaRefrigeracio;
    }
    public float getGrausGeneradorVapor() {
        return grausGeneradorVapor;
    }
    public void setGrausGeneradorVapor(float grausGeneradorVapor) {
        this.grausGeneradorVapor = grausGeneradorVapor;
    }
    public float getGrausTurbina() {
        return grausTurbina;
    }
    public void setGrausTurbina(float grausTurbina) {
        this.grausTurbina = grausTurbina;
    }

    //OTROS METODOS
    public String toString(){
        return "# Pàgina Estat\n" +
                "- Dia: "+ getDia() +"\n" +
                "- Inserció Barres: "+ getPercentatgeInsercioBarres() +" %\n" +
                "- Output Reactor: "+ getGrausReactor() +" Graus\n" +
                "- Output Sistema de Refrigeració: "+ getGrausSistemaRefrigeracio() +" Graus\n" +
                "- Output Generador de Vapor: "+ getGrausGeneradorVapor() +" Graus\n" +
                "- Output Turbina: "+ getGrausTurbina() +" Unitats de Potència";
    }
}
