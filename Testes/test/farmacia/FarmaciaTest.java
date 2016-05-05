package farmacia;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import exceptions.MedicamentoException;
import factories.FactoryDeMedicamentos;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Farmacia;

public class FarmaciaTest {
	private Farmacia farmacia;
	private Set<CategoriasDeMedicamentos> categorias;
	@SuppressWarnings("unused")
	private FactoryDeMedicamentos factory;

	@Before
	public void inicializa() {
		farmacia = new Farmacia();
		categorias = new HashSet<CategoriasDeMedicamentos>();
		categorias.add(CategoriasDeMedicamentos.ANTIBIOTICO);
		factory = new FactoryDeMedicamentos();
	}

	@Test
	public void testCadastraMedicamento() {
		// caso normal
		try {
		
			farmacia.criaMedicamento("dorflex",34.0, 5 ,categorias,"referencia");
			assertTrue(farmacia.existeMedicamento("dorflex", 34.0));
		} catch (Exception e) {
			fail();
		}

		// medicamento ja existente
		try {
			farmacia.criaMedicamento("dorflex",34.0, 5 ,categorias,"referencia");
			farmacia.criaMedicamento("dorflex",34.0, 5 ,categorias,"referencia");
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
			farmacia.criaMedicamento("dorflex",34.0, 5 ,categorias,"referencia");
			assertEquals(farmacia.getTipoMedicamento("dorflex"),
					"de Referencia");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetPrecoMedicamento() {
		try {
			farmacia.criaMedicamento("dorflex",34.0, 5 ,categorias,"referencia");
			assertEquals(farmacia.buscaMedicamento("dorflex").getPreco(), 34.0, 0.0);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetQntMedicamento() {
		try {
			farmacia.criaMedicamento("dorflex",34.0, 5 ,categorias,"referencia");
			assertEquals(farmacia.buscaMedicamento("dorflex").getQuantidade(), 5);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetCategoriasMedicamento() {
		try {
			categorias.add(CategoriasDeMedicamentos.ANTIEMETICO);
			farmacia.criaMedicamento("dorflex",34.0, 5 ,categorias,"referencia");
			String categorias = "antibiotico,antiemetico";
			assertEquals(farmacia.buscaMedicamento("dorflex").getCategorias(),
					categorias);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAtualizaMedicamento() {
		try {
			farmacia.criaMedicamento("dorflex",34.0, 5 ,categorias,"referencia");
			farmacia.atualizaMedicamento("Nimesulida", "preco", "15.00");
			farmacia.atualizaMedicamento("Nimesulida", "quantidade", "200");
			assertEquals(farmacia.buscaMedicamento("Nimesulida").getPreco(), 15.0, 0.0);
			assertEquals(farmacia.buscaMedicamento("Nimesulida").getQuantidade(), 200);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testExisteMedicamento() {
		try {
			farmacia.criaMedicamento("Nimesulida",15.0, 200, categorias,"referencia");
			assertTrue(farmacia.buscaMedicamento("Nimesulida").getNome().equals("Nimesulida"));
			assertFalse(farmacia.buscaMedicamento("casa").getNome().equals("casa"));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testConsultaMedCategoria() {
		// casos normais
		try {
			Set<CategoriasDeMedicamentos> categoria1 = new HashSet<CategoriasDeMedicamentos>();
			categoria1.add(CategoriasDeMedicamentos.ANALGESICO);
			farmacia.criaMedicamento("Valium", 29.0, 4,
					categoria1,"generico");
			farmacia.criaMedicamento("Nimesulida", 29.0, 4,
					categoria1,"generico");
			farmacia.criaMedicamento("Metamizol", 29.0, 4,
					categoria1, "generico");
			farmacia.criaMedicamento("Morfina", 29.0, 4,
					categoria1, "generico");
			assertEquals(farmacia.buscaMedicamentos(CategoriasDeMedicamentos.ANALGESICO),
					"Valium,Nimesulida,Metamizol,Morfina");
			Set<CategoriasDeMedicamentos> categoria2 = new HashSet<CategoriasDeMedicamentos>();
			categoria2.add(CategoriasDeMedicamentos.HORMONAL);
			farmacia.criaMedicamento("Duraston",52.9, 73,
					categoria2, "referencia" );
			farmacia.criaMedicamento("Medroxyprogesterona",52.9, 73, categoria2, "referencia");
			assertEquals(farmacia.buscaMedicamentos(CategoriasDeMedicamentos.HORMONAL),
					"Duraston,Medroxyprogesterona");
			Set<CategoriasDeMedicamentos> categoria3 = new HashSet<CategoriasDeMedicamentos>();
			categoria3.add(CategoriasDeMedicamentos.ANTIBIOTICO);
			farmacia.criaMedicamento("Penicilina", 32, 3,
					categoria3, "generico");
			assertEquals(farmacia.buscaMedicamentos(CategoriasDeMedicamentos.ANTIBIOTICO),
					"Penicilina");
		} catch (Exception e) {
			fail();
		}

		// medicamento nao cadastrado
		try {
			farmacia.buscaMedicamentos(CategoriasDeMedicamentos.ANTIEMETICO);
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro na consulta de medicamentos. Nao ha remedios cadastrados nessa categoria.",
					e.getMessage());
		}

		// categoria invalida
		try {
			farmacia.buscaMedicamentos(CategoriasDeMedicamentos.ANTITERMICO);
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
			farmacia.buscaMedicamento("Opium");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro na consulta de medicamentos. Medicamento nao cadastrado.",
					e.getMessage());
		}

		// casos normais
		try {
			Set<CategoriasDeMedicamentos> categoria1 = new HashSet<CategoriasDeMedicamentos>();
			categoria1.add(CategoriasDeMedicamentos.ANALGESICO);
			categoria1.add(CategoriasDeMedicamentos.ANTITERMICO);
			farmacia.criaMedicamento("Metamizol",58.30, 466,
					categoria1, "referencia");
			assertEquals(
					farmacia.buscaMedicamento("Metamizol").toString(),
					"Medicamento de Referencia: Metamizol - Preco: R$ 58,30 - Disponivel: 466 - Categorias: analgesico,antitermico");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetEstoqueFarmacia() {
		// ordenacao por preco
		try {
			farmacia.criaMedicamento("Valium",2, 10,
					categorias, "referencia");
			farmacia.criaMedicamento("Metamizol", 4.0, 10,
					categorias, "referencia");
			farmacia.criaMedicamento("Penicilina", 6, 10,
					categorias, "referencia");
			farmacia.criaMedicamento("Medroxyprogesterona",9, 10, categorias, "referencia");
			farmacia.criaMedicamento("Hioscina", 1, 10,
					categorias, "referencia");
			farmacia.criaMedicamento("Nimesulida", 3, 10,
					categorias, "referencia");
			farmacia.criaMedicamento("Duraston", 5, 10,
					categorias, "referencia");
			farmacia.criaMedicamento("Morfina", 8, 10,
					categorias, "referencia");
			assertEquals(
					farmacia.getMedicamentosPreco(),
					"Hioscina,Valium,Nimesulida,Metamizol,Duraston,Penicilina,Morfina,Medroxyprogesterona");
		} catch (Exception e) {
			fail();
		}

		// ordem alfabetica
		try {
			assertEquals(
					farmacia.getMedicamentosNome(),
					"Duraston,Hioscina,Medroxyprogesterona,Metamizol,Morfina,Nimesulida,Penicilina,Valium");
		} catch (Exception e) {
			fail();
		}
		
	}
}