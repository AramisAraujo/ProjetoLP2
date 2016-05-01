package farmacia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import exceptions.StringException;
import exceptions.ValorException;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;
import farmacia.Medicamento;

public class MedicamentoTest {

	@SuppressWarnings("unused")
	private Farmacia farmacia;
	private HashSet<CategoriasDeMedicamentos> categorias;

	@Before
	public void inicializa() {
		farmacia = new Farmacia();
		categorias = new HashSet<CategoriasDeMedicamentos>();
		categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
	}
	
	@SuppressWarnings("unused")
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
		} catch (StringException exp) {
			assertEquals("O nome do medicamento nao pode ser nulo." , exp.getMessage());
		} catch (Exception exp) {
			fail();
		}
		
		// nome vazio
		try {
			Medicamento morfina = new Medicamento("", 49, 4, categorias);
			fail("Nao deveria criar um medicamento com o nome vazio.");
		} catch (Exception exp) {
			assertEquals("O nome do medicamento nao pode ser vazio." , exp.getMessage());
		}
		
		// preco negativo.
		try {
			Medicamento morfina = new Medicamento("Morfina", -49, 4, categorias);
			fail("Nao deveria criar um medicamento com o preco negativo.");
		} catch (ValorException exp) {
			assertEquals("O preco do medicamento nao pode ser negativo." , exp.getMessage());
		} catch (Exception exp) {
			fail();
		}
		
		// quantidade vazia.
		try {
			Medicamento morfina = new Medicamento("Morfina", 49, 0, categorias);
			fail("Nao deveria criar um medicamento com a quantidade vazia.");
		} catch (Exception exp) {
			assertEquals("A quantidade do medicamento nao pode ser zero." , exp.getMessage());
		}
		
		// quantidade negativa.
		try {
			Medicamento morfina = new Medicamento("Morfina", 49, -4, categorias);
			fail("Nao deveria criar um medicamento com a quantidade negativa.");
		} catch (Exception exp) {
			assertEquals("A quantidade do medicamento nao pode ser negativa." , exp.getMessage());
		}
		
		// categorias null.
		try {
			Medicamento morfina = new Medicamento("Morfina", 49, 4, null);
			fail("Nao deveria criar um medicamento com a categoria null.");
		} catch (Exception exp) {
			assertEquals("A categoria do medicamento nao pode ser nula." , exp.getMessage());
		}
	}

}
