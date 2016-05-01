package farmacia;


import static org.junit.Assert.*;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import exceptions.StringException;
import factories.FactoryDeMedicamentos;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Medicamento;
import farmacia.MedicamentoGenerico;


public class FactoryDeMedicamentosTest {
	private FactoryDeMedicamentos factory;
	private HashSet<CategoriasDeMedicamentos> categorias;

	@Before
	public void inicializa() {
		factory = new FactoryDeMedicamentos();
		categorias = new HashSet<CategoriasDeMedicamentos>();
		categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
	}

	@SuppressWarnings("unused")
	@Test
	public void testCriaMedicamento() {
		// criacao de medicamento generico
		try {
			Medicamento dorflex = factory.criaMedicamento("dorflex", 38.0, 4, categorias, "medicamento generico");
			assertTrue(dorflex instanceof MedicamentoGenerico);
		} catch (Exception e) {
			fail();
		}

		// criacao de medicamento de referencia
		try {
			Medicamento dorflex = factory.criaMedicamento("dorflex", 38.0, 4, categorias, "medicamento de referencia");
			assertTrue(dorflex instanceof Medicamento);
			assertFalse(dorflex instanceof MedicamentoGenerico);
		} catch (Exception e) {
			fail();
		}

		// tipo de medicamento inexistente
		try {
			Medicamento dorflex = factory.criaMedicamento("dorflex", 38.0, 4, categorias, "barato");
			fail();
		} catch (StringException e) {
			assertEquals("Esse tipo de medicamento nao existe!", e.getMessage());
		} catch (Exception e) {
			fail();
		}
	}

}
