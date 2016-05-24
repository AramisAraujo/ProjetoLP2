package farmacia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.CadastroException;
import exceptions.MedicamentoException;
import factories.FactoryDeMedicamentos;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;

public class FarmaciaTest {
	private Farmacia farmacia;
	private String categorias;
	@SuppressWarnings("unused")
	private FactoryDeMedicamentos factory;

	@Before
	public void inicializa() {
		farmacia = new Farmacia();
		categorias = "antibiotico,";
		factory = new FactoryDeMedicamentos();
	}

	@Test
	public void testCadastraMedicamento() {
		// caso normal
		try {
			farmacia.cadastraMedicamento("dorflex", "referencia", 34.0, 5,
					categorias);
			assertTrue(farmacia.existeMedicamento("dorflex"));
		} catch (Exception e) {
			fail();
		}

		// medicamento ja existente
		try {
			farmacia.cadastraMedicamento("dorflex", "referencia", 34.0, 5,
					categorias);
			farmacia.cadastraMedicamento("dorflex", "referencia", 34.0, 5,
					categorias);
			fail();
		} catch (CadastroException e) {
			assertEquals("Erro no cadastro de medicamento. Esse medicamento ja foi cadastrado.", e.getMessage());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetTipoMedicamento() {
		try {
			farmacia.cadastraMedicamento("dorflex", "referencia", 34.0, 5,
					categorias);
			assertEquals(farmacia.getTipoMedicamento("dorflex"),
					"de Referencia");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetPrecoMedicamento() {
		try {
			farmacia.cadastraMedicamento("dorflex", "referencia", 34.0, 5,
					categorias);
			assertEquals(farmacia.getPreco("dorflex"), 34.0, 0.0);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetQntMedicamento() {
		try {
			farmacia.cadastraMedicamento("dorflex", "referencia", 34.0, 5,
					categorias);
			assertEquals(farmacia.getQuantidade("dorflex"), 5);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetCategoriasMedicamento() {
		try {
			categorias = categorias + "antiemetico,";
			farmacia.cadastraMedicamento("dorflex", "referencia", 34.0, 5,
					categorias);
			String categorias = "antibiotico,antiemetico";
			assertEquals(farmacia.getCategoriasMedicamento("dorflex"),
					categorias);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAtualizaMedicamento() {
		try {
			farmacia.cadastraMedicamento("Nimesulida", "referencia", 15.0, 200,
					categorias);
			farmacia.atualizaMedicamento("Nimesulida", "preco", "15.00");
			farmacia.atualizaMedicamento("Nimesulida", "quantidade", "200");
			assertEquals(farmacia.getPreco("Nimesulida"), 15.0, 0.0);
			assertEquals(farmacia.getQuantidade("Nimesulida"), 200);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testExisteMedicamento() {
		try {
			farmacia.cadastraMedicamento("Nimesulida", "referencia", 15.0, 200,
					categorias);
			assertTrue(farmacia.existeMedicamento("Nimesulida"));
			assertFalse(farmacia.existeMedicamento("casa"));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testConsultaMedCategoria() {
		// casos normais
		try {
			String categoria1 = "";
			String categoria2 = "";
			String categoria3 = "";
			String categoria4 = "";
			String categoria5 = "";

			categoria1 = categoria1 + "analgesico";
			
			categoria2 = categoria2 + "analgesico,";
			categoria2 = categoria2 + "antitermico";
			
			categoria3 = categoria3 + "antiinflamatorio,";
			categoria3 = categoria3 + "antitermico,";
			categoria3 = categoria3 + "analgesico";
			
			farmacia.cadastraMedicamento("Valium", "generico", 21.50, 45,
					categoria1);
			farmacia.cadastraMedicamento("Metamizol", "referencia", 58.30, 466,
					categoria2);
			farmacia.cadastraMedicamento("Morfina", "referencia", 150, 600,
					categoria1);
			farmacia.cadastraMedicamento("Nimesulida", "referencia", 12.50, 150,
					categoria3);
			
			assertEquals("Nimesulida,Valium,Metamizol,Morfina",farmacia.consultaMedCategoria("analgesico"));
			
			
			categoria4 = categoria4 + "hormonal";
			farmacia.cadastraMedicamento("Duraston", "referencia", 52.9, 73,
					categoria4);
			farmacia.cadastraMedicamento("Medroxyprogesterona", "referencia",
					52.9, 73, categoria4);
			assertEquals(farmacia.consultaMedCategoria("hormonal"),
					"Duraston,Medroxyprogesterona");
			
			categoria5 = categoria5 + "antibiotico";
			farmacia.cadastraMedicamento("Penicilina", "generico", 32, 3,
					categoria5);
			assertEquals(farmacia.consultaMedCategoria("antibiotico"),
					"Penicilina");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		// medicamento nao cadastrado
		try {
			farmacia.consultaMedCategoria("antiemetico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Nao ha remedios cadastrados nessa categoria.",
					e.getMessage());
		}

		// categoria invalida
		try {
			farmacia.consultaMedCategoria("antialergico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Categoria invalida.",
					e.getMessage());
		}
	}

	@Test
	public void testConsultaMedNome() {
		/*
		 * 
		 * expect
		 * "Medicamento de Referencia: Metamizol - Preco: R$ 58,30 - Disponivel: 466 - Categorias: analgesico,antitermico"
		 * consultaMedNome nomeRemedio="Metamizol" expect
		 * "Medicamento Generico: Medroxyprogesterona - Preco: R$ 171,30 - Disponivel: 200 - Categorias: hormonal"
		 * consultaMedNome nomeRemedio="Medroxyprogesterona" expect
		 * "Medicamento de Referencia: Hioscina - Preco: R$ 10,00 - Disponivel: 300 - Categorias: antiemetico"
		 * consultaMedNome nomeRemedio="Hioscina"
		 */
		// medicamento nao cadastrado
		try {
			farmacia.consultaMedNome("Opium");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Medicamento nao cadastrado.",
					e.getMessage());
		}

		// casos normais
		try {
			String categoria1 = "";
			categoria1 = categoria1 + "analgesico,";
			categoria1 = categoria1 + "antitermico,";
			farmacia.cadastraMedicamento("Metamizol", "referencia", 58.30, 466,
					categoria1);
			assertEquals(
					farmacia.consultaMedNome("Metamizol").toString(),
					"Medicamento de Referencia: Metamizol - Preco: R$ 58,30 - Disponivel: 466 - Categorias: analgesico,antitermico");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetEstoqueFarmacia() {
		// ordenacao por preco
		try {
			farmacia.cadastraMedicamento("Valium", "referencia", 2, 10,
					categorias);
			farmacia.cadastraMedicamento("Metamizol", "referencia", 4, 10,
					categorias);
			farmacia.cadastraMedicamento("Penicilina", "referencia", 6, 10,
					categorias);
			farmacia.cadastraMedicamento("Medroxyprogesterona", "referencia",
					9, 10, categorias);
			farmacia.cadastraMedicamento("Hioscina", "referencia", 1, 10,
					categorias);
			farmacia.cadastraMedicamento("Nimesulida", "referencia", 3, 10,
					categorias);
			farmacia.cadastraMedicamento("Duraston", "referencia", 5, 10,
					categorias);
			farmacia.cadastraMedicamento("Morfina", "referencia", 8, 10,
					categorias);
			assertEquals(
					farmacia.getEstoqueFarmacia("preco"),
					"Hioscina,Valium,Nimesulida,Metamizol,Duraston,Penicilina,Morfina,Medroxyprogesterona");
		} catch (Exception e) {
			fail();
		}

		// ordem alfabetica
		try {
			assertEquals(
					farmacia.getEstoqueFarmacia("alfabetica"),
					"Duraston,Hioscina,Medroxyprogesterona,Metamizol,Morfina,Nimesulida,Penicilina,Valium");
		} catch (Exception e) {
			fail();
		}
		
		// ordenacao invalida
		try {
			farmacia.getEstoqueFarmacia("tipo");
			fail();
		} catch (Exception e) {
			assertEquals("Tipo de ordenacao invalida.", e.getMessage());
		}
	}
}