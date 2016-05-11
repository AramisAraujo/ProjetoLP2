package factories;

import banco_de_orgaos.Orgao;
import paciente.TipoSanguineo;

public class FactoryOrgaos {

	/**
	 * Metodo utilizado para criar um orgao.
	 * 
	 * @param nome
	 *            - nome do orgao que sera criado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao que sera criado
	 * @return - orgao que foi criado
	 * @throws Exception - excecao lancada caso ocorra algum erro
	 */
	public Orgao criaOrgao(String nome, TipoSanguineo tipoSanguineo) throws Exception {
		Orgao orgao = new Orgao(nome, tipoSanguineo);
		return orgao;
	}
}
