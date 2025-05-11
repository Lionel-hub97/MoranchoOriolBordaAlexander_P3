package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;
import prog2.vista.VariableNormal;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SistemaRefrigeracioTest {

    private SistemaRefrigeracio sistema;
    private BombaRefrigerant bomba1;
    private BombaRefrigerant bomba2;
    private PaginaIncidencies paginaIncidencies;
    private VariableUniforme var;

    @BeforeEach
    void setUp() {
        var = new VariableUniforme(123);
        sistema = new SistemaRefrigeracio();
        bomba1 = new BombaRefrigerant(var, 1);
        bomba2 = new BombaRefrigerant(var, 2);
        paginaIncidencies = new PaginaIncidencies(1);

        sistema.afegirBomba(bomba1);
        sistema.afegirBomba(bomba2);
    }

    @Test
    void testGetBombesRefrigerants() {
        assertEquals(2, sistema.getBombesRefrigerants().size());
    }

    @Test
    void testSetBombesRefrigerants() {
        ArrayList<BombaRefrigerant> novesBombes = new ArrayList<>();
        novesBombes.add(new BombaRefrigerant(var, 1));
        sistema.setBombesRefrigerants(novesBombes);
        assertEquals(1, sistema.getBombesRefrigerants().size());
    }

    @Test
    void testAfegirBomba() {
        BombaRefrigerant bomba3 = new BombaRefrigerant(var, 1);
        sistema.afegirBomba(bomba3);
        assertEquals(3, sistema.getBombesRefrigerants().size());
    }

    @Test
    void testActiva() throws CentralUBException {
        sistema.activa();
        assertTrue(sistema.getActivat());
    }

    @Test
    void testDesactiva() {
        sistema.desactiva();
        assertFalse(sistema.getActivat());
    }

    @Test
    void testGetActivat() throws CentralUBException {
        sistema.activa();
        assertTrue(sistema.getActivat());
    }

    @Test
    void testRevisa() {
        sistema.revisa(paginaIncidencies);
        assertTrue(paginaIncidencies.getIncidencies().isEmpty());
    }

    @Test
    void testCalculaOutput() throws CentralUBException {
        sistema.activa();
        float output = sistema.calculaOutput(200f);
        assertEquals(200f, output); // 250 * 2 bombes = 500, MIN(200, 500) = 200
    }

    @Test
    void testCalculaOutputLimit() throws CentralUBException {
        sistema.activa();
        float output = sistema.calculaOutput(100f);
        assertEquals(100f, output); // Mínim entre 125 i 100
    }

    @Test
    void testToString() throws CentralUBException {
        sistema.activa();
        String resultat = sistema.toString();
        assertTrue(resultat.contains("Sistema de Refrigeració"));
        assertTrue(resultat.contains("Bomba ID"));
    }
}