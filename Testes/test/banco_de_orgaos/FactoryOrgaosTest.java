package banco_de_orgaos;

import static org.junit.Assert.*;

import org.junit.Test;

import bancoorgaos.Orgao;
import factories.FactoryOrgaos;
import paciente.TipoSanguineo;

public class FactoryOrgaosTest {

	@Test
	public void testCriaOrgao() {
		try {
			FactoryOrgaos factory = new FactoryOrgaos();
			Orgao coracao = factory.criaOrgao("coracao", TipoSanguineo.A_NEG);
			assertEquals(coracao.getNome(), "coracao");
			assertEquals(coracao.getTipoSanguineo(), TipoSanguineo.A_NEG);
		} catch (Exception e) {
			fail();
		}
	}

}
