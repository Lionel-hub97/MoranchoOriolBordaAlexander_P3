package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TurbinaTest {

    private Turbina turbina;

    @BeforeEach
    void setUp() {
        turbina = new Turbina();
    }

    @Test
    void testConstructor() {
        assertTrue(turbina.getActivat());
    }

    @Test
    void testActiva() {
        turbina.desactiva();
        turbina.activa();
        assertTrue(turbina.getActivat());
    }

    @Test
    void testDesactiva() {
        turbina.desactiva();
        assertFalse(turbina.getActivat());
    }

    @Test
    void testGetActivatInicial() {
        assertTrue(turbina.getActivat());
    }

    @Test
    void testRevisa() {
        // Comprova que no llança excepcions
        assertDoesNotThrow(() -> turbina.revisa(new PaginaIncidencies(1)));
    }

    @Test
    void testGetCostOperatiu() {
        assertEquals(20, turbina.getCostOperatiu());
    }

    @Test
    void testCalculaOutputActivaInputMajor100() {
        float input = 150;
        assertEquals(300, turbina.calculaOutput(input)); // 150 * 2 = 300
    }

    @Test
    void testCalculaOutputActivaInputMenor100() {
        float input = 50;
        assertEquals(0, turbina.calculaOutput(input));
    }

    @Test
    void testCalculaOutputDesactiva() {
        turbina.desactiva();
        float input = 150;
        assertEquals(0, turbina.calculaOutput(input));
    }

}