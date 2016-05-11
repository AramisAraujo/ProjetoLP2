package farmacia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.MedicamentoException;
import factories.FactoryDeMedicamentos;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;
import farmacia.Medicamento;

public class FarmaciaTest {
	private Farmacia farmacia;
	private List<CategoriasDeMedicamentos> categorias;
	private FactoryDeMedicamentos factory;

	@Before
	public void inicializa() {
		farmacia = new Farmacia();
		categorias = new ArrayList<CategoriasDeMedicamentos>();
		categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
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
		} catch (MedicamentoException e) {
			assertEquals("Esse medicamento ja foi cadastrado.", e.getMessage());
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
			categorias.add(CategoriasDeMedicamentos.ANTIEMETICO);
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
			List<CategoriasDeMedicamentos> categoria1 = new ArrayList<CategoriasDeMedicamentos>();
			List<CategoriasDeMedicamentos> categoria_2 = new ArrayList<CategoriasDeMedicamentos>();
			List<CategoriasDeMedicamentos> categoria_3 = new ArrayList<CategoriasDeMedicamentos>();

			categoria1.add(CategoriasDeMedicamentos.ANALGESICO);
			
			categoria_2.add(CategoriasDeMedicamentos.ANALGESICO);
			categoria_2.add(CategoriasDeMedicamentos.ANTITERMICO);
			
			categoria_3.add(CategoriasDeMedicamentos.ANTIINFLAMATORIO);
			categoria_3.add(CategoriasDeMedicamentos.ANTITERMICO);
			categoria_3.add(CategoriasDeMedicamentos.ANALGESICO);
			
			farmacia.cadastraMedicamento("Valium", "generico", 21.50, 45,
					categoria1);
			farmacia.cadastraMedicamento("Metamizol", "referencia", 58.30, 466,
					categoria_2);
			farmacia.cadastraMedicamento("Morfina", "referencia", 150, 600,
					categoria1);
			farmacia.cadastraMedicamento("Nimesulida", "referencia", 12.50, 150,
					categoria_3);
			
			assertEquals("Valium,Nimesulida,Metamizol,Morfina",farmacia.consultaMedCategoria("analgesico"));
			
			List<CategoriasDeMedicamentos> categoria2 = new ArrayList<CategoriasDeMedicamentos>();
			categoria2.add(CategoriasDeMedicamentos.HORMONAL);
			farmacia.cadastraMedicamento("Duraston", "referencia", 52.9, 73,
					categoria2);
			farmacia.cadastraMedicamento("Medroxyprogesterona", "referencia",
					52.9, 73, categoria2);
			assertEquals(farmacia.consultaMedCategoria("hormonal"),
					"Duraston,Medroxyprogesterona");
			List<CategoriasDeMedicamentos> categoria3 = new ArrayList<CategoriasDeMedicamentos>();
			categoria3.add(CategoriasDeMedicamentos.ANTIBIOTICO);
			farmacia.cadastraMedicamento("Penicilina", "generico", 32, 3,
					categoria3);
			assertEquals(farmacia.consultaMedCategoria("antibiotico"),
					"Penicilina");
		} catch (Exception e) {
			fail();
		}

		// medicamento nao cadastrado
		try {
			farmacia.consultaMedCategoria("antiemetico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro na consulta de medicamentos. Nao ha remedios cadastrados nessa categoria.",
					e.getMessage());
		}

		// categoria invalida
		try {
			farmacia.consultaMedCategoria("antialergico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro na consulta de medicamentos. Categoria invalida.",
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
			List<CategoriasDeMedicamentos> categoria1 = new ArrayList<CategoriasDeMedicamentos>();
			categoria1.add(CategoriasDeMedicamentos.ANALGESICO);
			categoria1.add(CategoriasDeMedicamentos.ANTITERMICO);
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