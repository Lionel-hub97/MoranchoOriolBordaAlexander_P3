package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeneradorVaporTest {

    private GeneradorVapor generador;

    @BeforeEach
    void setUp() {
        generador = new GeneradorVapor();
    }

    @Test
    void testActiva() {
        generador.desactiva();
        generador.activa();
        assertTrue(generador.getActivat());
    }

    @Test
    void testDesactiva() {
        generador.desactiva();
        assertFalse(generador.getActivat());
    }

    @Test
    void testGetActivatInicial() {
        assertTrue(generador.getActivat());
    }

    @Test
    void testRevisa() {
        // Comprova que no llança excepcions
        assertDoesNotThrow(() -> generador.revisa(new PaginaIncidencies(1)));
    }

    @Test
    void testGetCostOperatiuActiu() {
        assertEquals(25, generador.getCostOperatiu());
    }

    @Test
    void testGetCostOperatiuInactiu() {
        generador.desactiva();
        assertEquals(0, generador.getCostOperatiu());
    }

    @Test
    void testCalculaOutputActiu() {
        float input = 100;
        assertEquals(90, generador.calculaOutput(input));
    }

    @Test
    void testCalculaOutputInactiu() {
        generador.desactiva();
        assertEquals(25, generador.calculaOutput(100));
    }
}