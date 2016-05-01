package farmacia;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import farmacia.CategoriasDeMedicamentos;
import farmacia.Medicamento;
import farmacia.MedicamentoGenerico;

public class MedicamentoGenericoTest {

	@Test
	public void testGetPreco() {
		try {
			Set<CategoriasDeMedicamentos> categorias = new HashSet<CategoriasDeMedicamentos>();
			categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
			Medicamento tylenol = new MedicamentoGenerico("tylenol", 10, 3, categorias);
			assertEquals(tylenol.getPreco(), 6, 0.0);
		} catch (Exception e) {
			fail();
		}
	}

}
