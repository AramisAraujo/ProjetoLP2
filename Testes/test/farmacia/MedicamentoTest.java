package farmacia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.MedicamentoException;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;
import farmacia.Medicamento;

public class MedicamentoTest {

	private Farmacia farmacia;
	private List<CategoriasDeMedicamentos> categorias;

	@Before
	public void inicializa() {
		farmacia = new Farmacia();
		categorias = new ArrayList<CategoriasDeMedicamentos>();
		categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
	}

	@Test
	public void testMedicamento() {
		// caso normal
		try {
			Medicamento tylenol = new Medicamento("tylenol", 49, 4, categorias);
			assertEquals(tylenol.getNome(), "tylenol");
			assertEquals(tylenol.getPreco(), 49, 0.0);
			assertEquals(tylenol.getQuantidade(), 4);
			assertEquals(tylenol.getCategorias(), categorias);
		} catch (Exception e) {
			fail();
		}

		// nome nulo
		try {
			Medicamento morfina = new Medicamento(null, 49, 4, categorias);
			fail("Nao deveria criar um medicamento com o nome null.");
		} catch (Exception exp) {
			assertEquals("Nome do medicamento nao pode ser vazio.", exp.getMessage());

			// nome vazio
			try {
				Medicamento morfina = new Medicamento("", 49, 4, categorias);
				fail("Nao deveria criar um medicamento com o nome vazio.");
			} catch (Exception e) {
				assertEquals("Nome do medicamento nao pode ser vazio.",
						e.getMessage());
			}

			// preco negativo.
			try {
				Medicamento morfina = new Medicamento("Morfina", -49, 4, categorias);
				fail("Nao deveria criar um medicamento com o preco negativo.");
			} catch (Exception e) {
				assertEquals("Preco do medicamento nao pode ser negativo.",
						e.getMessage());
			}

			// quantidade vazia.
			try {
				Medicamento morfina = new Medicamento("Morfina", 49, 0, categorias);
				fail("Nao deveria criar um medicamento com a quantidade vazia.");
			} catch (Exception erro) {
				assertEquals("A quantidade do medicamento nao pode ser zero.", erro.getMessage());
			}

			// quantidade negativa.
			try {
				Medicamento morfina = new Medicamento("Morfina", 49, -4, categorias);
				fail("Nao deveria criar um medicamento com a quantidade negativa.");
			} catch (Exception erro) {
				assertEquals("Quantidade do medicamento nao pode ser negativo.",
						erro.getMessage());
			}

			// categorias null.
			try {
				Medicamento morfina = new Medicamento("Morfina", 49, 4, null);
				fail("Nao deveria criar um medicamento com a categoria null.");
			} catch (Exception erro) {
				assertEquals("A categoria do medicamento nao pode ser nula.", erro.getMessage());
			}
		}
	}
}
