package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;
import static org.junit.jupiter.api.Assertions.*;

class ReactorTest {

    private Reactor reactor;
    private PaginaIncidencies paginaIncidencies;

    @BeforeEach
    void setUp() {
        reactor = new Reactor();
        paginaIncidencies = new PaginaIncidencies(1);
    }

    @Test
    void testConstructor() {
        assertFalse(reactor.getActivat());
        assertEquals(25f, reactor.getTemperatura());
    }

    @Test
    void testActivaAmbTemperaturaSegura() throws CentralUBException {
        reactor.setTemperatura(500f);
        reactor.activa();
        assertTrue(reactor.getActivat());
    }

    @Test
    void testActivaAmbTemperaturaAltaLlançaExcepció() {
        reactor.setTemperatura(1001f);
        assertThrows(CentralUBException.class, () -> reactor.activa());
    }

    @Test
    void testDesactiva() {
        reactor.desactiva();
        assertFalse(reactor.getActivat());
    }

    @Test
    void testRevisaTemperaturaNormal() {
        reactor.setTemperatura(800f);
        reactor.revisa(paginaIncidencies);
        assertTrue(paginaIncidencies.getIncidencies().isEmpty());
    }

    @Test
    void testRevisaTemperaturaAltaDesactiva() {
        reactor.setTemperatura(1001f);
        reactor.revisa(paginaIncidencies);
        assertFalse(reactor.getActivat());
        assertFalse(paginaIncidencies.getIncidencies().isEmpty());
    }

    @Test
    void testGetCostOperatiuDesactivat() {
        assertEquals(0f, reactor.getCostOperatiu());
    }

    @Test
    void testCalculaOutputActivat() throws CentralUBException {
        reactor.activa();
        reactor.setTemperatura(500f);
        float output = reactor.calculaOutput(80f);
        assertEquals(700f, output); // 500 + (100-80)*10 = 700
    }

    @Test
    void testCalculaOutputDesactivat() {
        reactor.setTemperatura(500f);
        float output = reactor.calculaOutput(80f);
        assertEquals(500f, output);
    }

    @Test
    void testToString() {
        String resultat = reactor.toString();
        assertTrue(resultat.contains("Activat: No"));
        assertTrue(resultat.contains("Temperatura: 25.0 ºC"));
    }
}