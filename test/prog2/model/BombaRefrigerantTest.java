package prog2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class BombaRefrigerantTest {

    private BombaRefrigerant bomba;
    private VariableUniforme variable;

    @BeforeEach
    void setUp() {
        variable = new VariableUniforme(123); // semilla fija para predictibilidad
        bomba = new BombaRefrigerant(variable, 1);
    }

    @Test
    void getId() {
        assertEquals(1, bomba.getId());
    }

    @Test
    void getActivatInitiallyFalse() {
        assertFalse(bomba.getActivat());
    }

    @Test
    void getForaDeServeiInitiallyFalse() {
        assertFalse(bomba.getForaDeServei());
    }

    @Test
    void getCapacitatIfInactiveIsZero() {
        assertEquals(0, bomba.getCapacitat());
    }

    @Test
    void getCapacitatIfActiveIs250() throws CentralUBException {
        bomba.activa();
        assertEquals(250, bomba.getCapacitat());
    }

    @Test
    void getCostOperatiuAlways130() {
        assertEquals(130, bomba.getCostOperatiu());
    }

    @Test
    void activaSetsActivitatToTrue() throws CentralUBException {
        bomba.activa();
        assertTrue(bomba.getActivat());
    }

    @Test
    void activaThrowsExceptionIfForaDeServei() {
        bomba.revisa(new PaginaIncidencies(1)); // fuerza posible "fora de servei"
        if (bomba.getForaDeServei()) {
            CentralUBException exception = assertThrows(CentralUBException.class, () -> bomba.activa());
            assertEquals("EXCEPCIO: Central fora de servei", exception.getMessage());
        }
    }

    @Test
    void desactivaSetsActivitatToFalse() throws CentralUBException {
        bomba.activa();
        bomba.desactiva();
        assertFalse(bomba.getActivat());
    }

    @Test
    void revisaSetsForaDeServeiAndAddsIncidencia() {
        VariableUniforme v = new VariableUniforme(1); // semilla que da valor entre 0 y 23
        BombaRefrigerant bomba2 = new BombaRefrigerant(v, 99);
        PaginaIncidencies pagina = new PaginaIncidencies(5);

        bomba2.revisa(pagina);

        assertTrue(bomba2.getForaDeServei());
        assertFalse(pagina.getIncidencies().isEmpty());
        assertTrue(pagina.getIncidencies().get(0).contains("La bomba refrigerant 99 està fora de servei"));
    }

    @Test
    void testToStringFormat() {
        String result = bomba.toString();
        assertTrue(result.contains("ID=1"));
        assertTrue(result.contains("Activitat=false"));
        assertTrue(result.contains("Fora de servei=false"));
    }
}
