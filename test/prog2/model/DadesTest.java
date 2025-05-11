package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class DadesTest {

    private Dades dades;

    @BeforeEach
    void setUp() {
        dades = new Dades();
    }

    @Test
    void testFinalitzaDia() {
        Bitacola bitacola = dades.finalitzaDia(500);
        assertNotNull(bitacola);
        assertFalse(bitacola.getIncidencies().isEmpty());
        assertEquals(1, bitacola.getIncidencies().size()); //Cada dia es creen les tres pàgines
    }

    @Test
    void testInsercioBarres() {
        assertEquals(100, dades.getInsercioBarres()); // 100 per defecte
    }


    @Test
    void testSetInsercioBarres() throws CentralUBException {
        dades.setInsercioBarres(80);
        assertEquals(80, dades.getInsercioBarres());
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(150));
    }

    @Test
    void testActivaReactor() throws CentralUBException {
        dades.activaReactor();
        assertTrue(dades.mostraReactor().getActivat());
        dades.mostraReactor().setTemperatura(1500);
        assertThrows(CentralUBException.class, () -> dades.activaReactor()); //Temperatura fora de limits
    }

    @Test
    void testDesactivaReactor() throws CentralUBException {
        dades.activaReactor();
        dades.desactivaReactor();
        assertFalse(dades.mostraReactor().getActivat());
    }

    @Test
    void testActivaBomba() throws CentralUBException {
        dades.activaBomba(0);
        assertTrue(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).getActivat());
    }

    @Test
    void testDesactivaBomba() throws CentralUBException {
        dades.activaBomba(0);
        dades.desactivaBomba(0);
        assertFalse(dades.mostraSistemaRefrigeracio().getBombesRefrigerants().get(0).getActivat());
    }

    @Test
    void testCalculaPotencia() throws CentralUBException {
        // Exemple:
        // Amb 90% d'inserció barres
        // Reactor: actiu, 25 + (100-90)*10 = 125
        // Refrigeració: 4 bombes actives = 250*4 = 1000, MIN(125,1000) = 125
        // Generador: 125*0,9 = 112,5
        // Turbina: 112,5*2 = 225

        dades.setInsercioBarres(90);
        dades.activaReactor();
        dades.activaBomba(0);
        dades.activaBomba(1);
        dades.activaBomba(2);
        dades.activaBomba(3);
        assertTrue(dades.calculaPotencia() >= 0);
        assertEquals(225, dades.calculaPotencia());
    }

    @Test
    void testGuanysAcumulats() throws CentralUBException {
        assertEquals(0, dades.getGuanysAcumulats());
        // Exemple:
        // Amb 90% d'inserció barres
        // Reactor: actiu, 25 + (100-90)*10 = 125
        // Refrigeració: 4 bombes actives = 250*4 = 1000, MIN(125,1000) = 125
        // Generador: 125*0,9 = 112,5
        // Turbina: 112,5*2 = 225
        dades.setInsercioBarres(90);
        dades.activaReactor();
        dades.activaBomba(0);
        dades.activaBomba(1);
        dades.activaBomba(2);
        dades.activaBomba(3);
        dades.finalitzaDia(250); // per defecte
        //225 - cost operatiu cada part = 225 - 5 - 35 - (130 * 4 (actives)) - 25 - 20 = -400
        System.out.println(dades.mostraBitacola());
        assertEquals(-380, dades.getGuanysAcumulats());
    }


    @Test
    void testMostraBitacola() {
        assertNotNull(dades.mostraBitacola());
        dades.finalitzaDia(250);
        String s = dades.mostraBitacola() + "";
        assertTrue(s.contains("# Pàgina Incidències"));
        assertTrue(s.contains("# Pàgina Econòmica"));
        assertTrue(s.contains("# Pàgina Estat"));
    }

    @Test
    void testMostraIncidencies() {
        List<PaginaIncidencies> incidencies = dades.mostraIncidencies();
        assertNotNull(incidencies);
        assertTrue(incidencies.isEmpty());
    }
}