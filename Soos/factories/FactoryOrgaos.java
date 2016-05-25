package factories;

import java.io.Serializable;

import bancoorgaos.Orgao;
import exceptions.BancoOrgaoException;
import paciente.TipoSanguineo;

/**
 * FactoryOrgaos
 * Classe que representa uma factory de objetos do tipo orgao.
 * Eh responsavel pela criacao de orgaos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */

public class FactoryOrgaos implements Serializable{

	/**
	 * CriaOrgao
	 * Metodo responsavel pela criacao de  um orgao.
	 * 
	 * @param nome
	 *            - nome do orgao que sera criado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao que sera criado
	 * @return - orgao que foi criado
	 * @throws BancoOrgaoException - excecao lancada caso ocorra algum erro
	 */
	
	public Orgao criaOrgao(String nome, TipoSanguineo tipoSanguineo) throws BancoOrgaoException {
		
		Orgao orgao = new Orgao(nome, tipoSanguineo);
		
		return orgao;
	}
}
