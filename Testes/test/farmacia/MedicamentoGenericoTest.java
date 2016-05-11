package farmacia;
 
import static org.junit.Assert.*;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.Test;
 
import farmacia.CategoriasDeMedicamentos;
import farmacia.Medicamento;
import farmacia.MedicamentoGenerico;
 
public class MedicamentoGenericoTest {
 
    @Test
    public void testGetPreco() {
        try {
            List<CategoriasDeMedicamentos> categorias = new ArrayList<CategoriasDeMedicamentos>();
            categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
            Medicamento tylenol = new MedicamentoGenerico("tylenol", 10, 3, categorias);
            assertEquals(tylenol.getPreco(), 6, 0.0);
        } catch (Exception e) {
            fail();
        }
    }
 
}