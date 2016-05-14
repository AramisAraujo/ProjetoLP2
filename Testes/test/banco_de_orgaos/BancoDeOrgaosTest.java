package banco_de_orgaos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.BancoOrgaoException;
import factories.FactoryOrgaos;
import paciente.TipoSanguineo;

public class BancoDeOrgaosTest {

	private BancoDeOrgaos bancoDeOrgaos;

	@Before
	public void inicializa() {
		bancoDeOrgaos = new BancoDeOrgaos();
	}

	@Test
	public void testExisteOrgaoString() {
		// nao existe orgao
		try {
			assertFalse(bancoDeOrgaos.existeOrgao("coracao"));
		} catch (Exception e) {
			fail();
		}

		// existe orgao
		try {
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.AB_POS);
			assertTrue(bancoDeOrgaos.existeOrgao("coracao"));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testExisteOrgaoStringTipoSanguineo() {
		// nao existe orgao
		try {
			assertFalse(bancoDeOrgaos.existeOrgao("coracao", TipoSanguineo.A_NEG));
		} catch (Exception e) {
			fail();
		}
		// existe orgao
		try {
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.AB_NEG);
			assertTrue(bancoDeOrgaos.existeOrgao("coracao", TipoSanguineo.AB_NEG));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetOrgaoPorSangue() {
		try {
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.A_NEG);
			bancoDeOrgaos.addOrgao("rim", TipoSanguineo.A_NEG);
			bancoDeOrgaos.addOrgao("pulmao", TipoSanguineo.AB_NEG);
			List<String> orgaos = new ArrayList<String>();
			orgaos.add("coracao");
			orgaos.add("rim");
			assertEquals(bancoDeOrgaos.getOrgaoPorSangue(TipoSanguineo.A_NEG), orgaos);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetOrgaoPorNome() {
		try {
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.A_NEG);
			bancoDeOrgaos.addOrgao("rim", TipoSanguineo.A_NEG);
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.AB_NEG);
			List<String> tipos = new ArrayList<String>();
			tipos.add("A-");
			tipos.add("AB-");
			assertEquals(bancoDeOrgaos.getOrgaoPorNome("coracao"), tipos);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAddOrgao() {
		try {
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.A_NEG);
			assertTrue(bancoDeOrgaos.existeOrgao("coracao", TipoSanguineo.A_NEG));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testRemoveOrgao() { // nao existe orgao
		try {
			bancoDeOrgaos.removeOrgao("coracao", TipoSanguineo.A_NEG);
			fail();
		} catch (Exception e) {
			assertEquals("Orgao nao cadastrado.", e.getMessage());
		}

		// existe orgao
		try {
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.A_NEG);
			bancoDeOrgaos.addOrgao("rim", TipoSanguineo.AB_NEG);
			bancoDeOrgaos.addOrgao("pulmao", TipoSanguineo.B_NEG);
			bancoDeOrgaos.removeOrgao("coracao", TipoSanguineo.A_NEG);
			assertFalse(bancoDeOrgaos.existeOrgao("coracao", TipoSanguineo.A_NEG));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testQntOrgao() {
		// nao existe orgao
		try {
			bancoDeOrgaos.qntOrgao("coracao");
			fail();
		} catch (BancoOrgaoException e) {
			assertEquals("O banco de orgaos apresentou um erro. Orgao nao cadastrado.", e.getMessage());
		}

		// existe orgao
		try {
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.A_NEG);
			bancoDeOrgaos.addOrgao("rim", TipoSanguineo.AB_NEG);
			bancoDeOrgaos.addOrgao("pulmao", TipoSanguineo.B_NEG);
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.AB_NEG);
			assertEquals(bancoDeOrgaos.qntOrgao("coracao"), 2);
		} catch (Exception e) {
			fail();
		}
	}
	/*
	 * @Test public void testQntTotalOrgaos() { try {
	 * bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.A_NEG);
	 * bancoDeOrgaos.addOrgao("rim", TipoSanguineo.AB_NEG);
	 * bancoDeOrgaos.addOrgao("pulmao", TipoSanguineo.B_NEG);
	 * bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.AB_NEG);
	 * assertEquals(bancoDeOrgaos.qntTotalOrgaos(), 4); } catch (Exception e) {
	 * fail(); } }
	 */
}
