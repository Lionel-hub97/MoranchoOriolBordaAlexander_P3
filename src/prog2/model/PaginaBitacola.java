package prog2.model;

import java.io.Serializable;

public class PaginaBitacola implements Serializable {

    private int dia;

    PaginaBitacola (int dia) {
        this.dia = dia;
    }

    //GETTERS i SETTERS
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
}


