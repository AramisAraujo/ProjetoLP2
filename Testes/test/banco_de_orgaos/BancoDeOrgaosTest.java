package banco_de_orgaos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.FactoryOrgaos;
import paciente.TipoSanguineo;

public class BancoDeOrgaosTest {

	private BancoDeOrgaos bancoDeOrgaos;

	@Before
	public void inicializa() {
		bancoDeOrgaos = new BancoDeOrgaos();
	}

	@Test
	public void testExisteOrgao() {
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
	public void testBuscaOrgao() {
		// nao existe orgao
		try {
			bancoDeOrgaos.getOrgao("rim", TipoSanguineo.B_POS);
			fail();
		} catch (Exception e) {
			assertEquals("Esse orgao nao existe.", e.getMessage());
		}
		
		// existe orgao
		try {
			bancoDeOrgaos.addOrgao("rim", TipoSanguineo.B_POS);
			FactoryOrgaos factory = new FactoryOrgaos();
			Orgao rim = factory.criaOrgao("rim", TipoSanguineo.B_POS);
			assertEquals(bancoDeOrgaos.getOrgao("rim", TipoSanguineo.B_POS), rim);
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
	public void testRemoveOrgao() {
		// nao existe orgao
		try {
			bancoDeOrgaos.removeOrgao("coracao", TipoSanguineo.A_NEG);
			fail();
		} catch (Exception e) {
			assertEquals("Esse orgao nao existe.", e.getMessage());
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
		} catch (Exception e) {
			assertEquals("Esse orgao nao existe.", e.getMessage());
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

	@Test
	public void testQntTotalOrgaos() {
		try {
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.A_NEG);
			bancoDeOrgaos.addOrgao("rim", TipoSanguineo.AB_NEG);
			bancoDeOrgaos.addOrgao("pulmao", TipoSanguineo.B_NEG);
			bancoDeOrgaos.addOrgao("coracao", TipoSanguineo.AB_NEG);
			assertEquals(bancoDeOrgaos.qntTotalOrgaos(), 4);
		} catch (Exception e) {
			fail();
		}
	}

}
