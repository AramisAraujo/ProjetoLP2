package banco_de_orgaos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.BancoOrgaoException;
import paciente.TipoSanguineo;

public class BancoDeOrgaosTest {

	private BancoDeOrgaos bancoDeOrgaos;

	@Test
	public void testExisteOrgaoString() {
		bancoDeOrgaos = new BancoDeOrgaos();
		// nao existe orgao
		try {
			assertFalse(bancoDeOrgaos.existeOrgao("coracao"));
		} catch (Exception e) {
			fail();
		}

		// existe orgao
		try {
			bancoDeOrgaos.cadastraOrgao("coracao", "AB+");
			assertTrue(bancoDeOrgaos.existeOrgao("coracao"));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testExisteOrgaoStringTipoSanguineo() {
		bancoDeOrgaos = new BancoDeOrgaos();
		// nao existe orgao
		try {
			assertFalse(bancoDeOrgaos.existeOrgao("coracao", TipoSanguineo.AB_NEG));
		} catch (Exception e) {
			fail();
		}
		// existe orgao
		try {
			bancoDeOrgaos.cadastraOrgao("coracao", "AB-");
			assertTrue(bancoDeOrgaos.existeOrgao("coracao", TipoSanguineo.AB_NEG));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetOrgaoPorSangue() {
		bancoDeOrgaos = new BancoDeOrgaos();
		try {
			bancoDeOrgaos.cadastraOrgao("coracao", "AB-");
			bancoDeOrgaos.cadastraOrgao("rim", "AB-");
			bancoDeOrgaos.cadastraOrgao("pulmao", "AB-");
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
		bancoDeOrgaos = new BancoDeOrgaos();
		try {
			bancoDeOrgaos.cadastraOrgao("coracao", "A-");
			bancoDeOrgaos.cadastraOrgao("rim",  "A-");
			bancoDeOrgaos.cadastraOrgao("coracao",  "A-");
			List<String> tipos = new ArrayList<String>();
			tipos.add("A-");
			tipos.add("AB-");
			assertEquals(bancoDeOrgaos.getOrgaoPorNome("coracao"), tipos);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testcadastraOrgao() {
		bancoDeOrgaos = new BancoDeOrgaos();
		try {
			bancoDeOrgaos.cadastraOrgao("coracao",  "A-");
			assertTrue(bancoDeOrgaos.existeOrgao("coracao", TipoSanguineo.A_NEG));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testRemoveOrgao() { 
		bancoDeOrgaos = new BancoDeOrgaos();
		// nao existe orgao
		try {
			bancoDeOrgaos.removeOrgao("coracao", TipoSanguineo.A_NEG);
			fail();
		} catch (Exception e) {
			assertEquals("Orgao nao cadastrado.", e.getMessage());
		}

		// existe orgao
		try {
			bancoDeOrgaos.cadastraOrgao("coracao",  "A-");
			bancoDeOrgaos.cadastraOrgao("rim", "AB-");
			bancoDeOrgaos.cadastraOrgao("pulmao", "B-");
			bancoDeOrgaos.removeOrgao("coracao",  TipoSanguineo.A_NEG);
			assertFalse(bancoDeOrgaos.existeOrgao("coracao",  TipoSanguineo.A_NEG));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testQntOrgao() {
		bancoDeOrgaos = new BancoDeOrgaos();
		// nao existe orgao
		try {
			bancoDeOrgaos.qntOrgao("coracao");
			fail();
		} catch (BancoOrgaoException e) {
			assertEquals("O banco de orgaos apresentou um erro. Orgao nao cadastrado.", e.getMessage());
		}

		// existe orgao
		try {
			bancoDeOrgaos.cadastraOrgao("coracao", "A-");
			bancoDeOrgaos.cadastraOrgao("rim",  "AB-");
			bancoDeOrgaos.cadastraOrgao("pulmao",  "B-");
			bancoDeOrgaos.cadastraOrgao("coracao",  "AB-");
			assertEquals(bancoDeOrgaos.qntOrgao("coracao"), 2);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testQntTotalOrgaos() {
		bancoDeOrgaos = new BancoDeOrgaos();
		try {
			bancoDeOrgaos.cadastraOrgao("coracao",  "A-");
			bancoDeOrgaos.cadastraOrgao("rim",  "AB-");
			bancoDeOrgaos.cadastraOrgao("pulmao",  "B-");
			bancoDeOrgaos.cadastraOrgao("coracao",  "AB-");
			assertEquals(bancoDeOrgaos.qntTotalOrgaos(), 4);
		} catch (Exception e) {
			fail();
		}
	}

}