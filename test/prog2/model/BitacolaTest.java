package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BitacolaTest {

    private Bitacola bitacola;

    @BeforeEach
    void setUp() {
        bitacola = new Bitacola();
    }

    @Test
    void afegeixPagina() {
        PaginaEconomica econ = new PaginaEconomica(1, 100, 120, 95, 1000, 50, 300, 12000);
        bitacola.afegeixPagina(econ);
        assertTrue(bitacola.toString().contains("# Pàgina Econòmica"));
    }

    @Test
    void getIncidencies() {
        PaginaIncidencies inc1 = new PaginaIncidencies(2);
        inc1.afegeixIncidencia("Error test getIncidencies");
        PaginaEconomica econ = new PaginaEconomica(1, 100, 120, 95, 5000, 50, 300, 12000);

        bitacola.afegeixPagina(inc1);
        bitacola.afegeixPagina(econ);

        List<PaginaIncidencies> result = bitacola.getIncidencies();
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getDia());
        assertTrue(result.get(0).toString().contains("Error test getIncidencies"));
    }

    @Test
    void testToString() {
        PaginaEconomica econ = new PaginaEconomica(1, 100, 120, 95, 5000, 50, 300, 12000);
        PaginaEstat estat = new PaginaEstat(2, 75, 500, 250, 200, 180);
        PaginaIncidencies inc = new PaginaIncidencies(3);
        inc.afegeixIncidencia("Error test toString");

        bitacola.afegeixPagina(econ);
        bitacola.afegeixPagina(estat);
        bitacola.afegeixPagina(inc);

        String result = bitacola.toString();
        assertTrue(result.contains("# Pàgina Econòmica"));
        assertTrue(result.contains("# Pàgina Estat"));
        assertTrue(result.contains("# Pàgina Incidències"));
        assertTrue(result.contains("Error test toString"));
    }
}
