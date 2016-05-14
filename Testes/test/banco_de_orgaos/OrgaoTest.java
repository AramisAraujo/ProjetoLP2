package banco_de_orgaos;

import static org.junit.Assert.*;

import org.junit.Test;

import paciente.TipoSanguineo;

public class OrgaoTest {

	@Test
	public void testOrgao() {
		// caso normal
		try {
			Orgao pulmao = new Orgao("pulmao", TipoSanguineo.A_POS);
			assertEquals(pulmao.getNome(), "pulmao");
			assertEquals(pulmao.getTipoSanguineo(), TipoSanguineo.A_POS);
		} catch (Exception e) {
			fail();
		}

		// nome vazio
		try {
			Orgao vazio = new Orgao("", TipoSanguineo.AB_NEG);
			fail();
		} catch (Exception e) {
			assertEquals("O banco de orgaos apresentou um erro. Nome do orgao nao pode ser vazio.", e.getMessage());
		}

		// nome nulo
		try {
			Orgao mulo = new Orgao(null, TipoSanguineo.AB_NEG);
			fail();
		} catch (Exception e) {
			assertEquals("O banco de orgaos apresentou um erro. Nome do orgao nao pode ser vazio.", e.getMessage());
		}

		// tipo sanguineo nulo
		try {
			Orgao pulmao = new Orgao("Pulmao", null);
			fail();
		} catch (Exception e) {
			assertEquals("O banco de orgaos apresentou um erro. Tipo sanguineo invalido.", e.getMessage());
		}
	}

}
