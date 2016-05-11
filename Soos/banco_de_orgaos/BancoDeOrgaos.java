package banco_de_orgaos;

import java.util.ArrayList;
import java.util.List;

import factories.FactoryOrgaos;
import paciente.TipoSanguineo;

/**
 * Classe utilizada para armazenar e gerenciar órgãos fornecidos por doadores.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public class BancoDeOrgaos {

	private List<Orgao> bancoDeOrgaos;
	private FactoryOrgaos factoryOrgaos;

	public BancoDeOrgaos() {
		bancoDeOrgaos = new ArrayList<Orgao>();
		factoryOrgaos = new FactoryOrgaos();
	}

	/**
	 * Metodo utilizado para verificar se determinado orgao existe.
	 * 
	 * @param nome
	 *            - nome do orgao que sera verificado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao que sera verificado
	 * @return - true, se o orgao existir ou false, caso nao exista
	 */
	public boolean existeOrgao(String nome, TipoSanguineo tipoSanguineo) {
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome) && orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo utilizado para verificar se determinado orgao existe.
	 * 
	 * @param nome
	 *            - nome do orgao que sera verificado
	 * @return - true, se o orgao existir ou false, caso nao exista
	 */
	public boolean existeOrgao(String nome) {
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo utilizado para retornar determinado orgao.
	 * 
	 * @param nome
	 *            - nome do orgao que sera retornado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao que sera retornado
	 * @return - orgao encontrado
	 * @throws Exception
	 *             - excecao lancada caso o orgao nao exista
	 */
	public Orgao buscaOrgao(String nome, TipoSanguineo tipoSanguineo) throws Exception {
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome) && orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				return orgao;
			}
		}
		throw new Exception("Esse orgao nao existe.");// ajeitar as exceptions
														// dps
	}

	/**
	 * Metodo utilizado para criar e adicionar orgaos ao banco de orgaos.
	 * 
	 * @param nome
	 *            - nome do orgao que sera criado e adicionado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do do orgao que sera criado e adicionado
	 * @throws Exception
	 */
	public void addOrgao(String nome, TipoSanguineo tipoSanguineo) throws Exception {
		Orgao orgao = factoryOrgaos.criaOrgao(nome, tipoSanguineo);
		bancoDeOrgaos.add(orgao);
	}

	/**
	 * Metodo utilizado para remover determinado orgao do banco de orgaos.
	 * 
	 * @param nome
	 *            - nome do orgao que sera removido
	 * @param tipoSanguineo
	 *            - tipo do orgao que sera removido
	 * @throws Exception
	 *             - excecao lancada caso nao exista nenhum orgao com o nome
	 *             passado como parametro
	 */
	public void removeOrgao(String nome, TipoSanguineo tipoSanguineo) throws Exception {
		if (!existeOrgao(nome, tipoSanguineo)) { // ajeitar as exceptions dps
			throw new Exception("Esse orgao nao existe.");
		}
		bancoDeOrgaos.remove(buscaOrgao(nome, tipoSanguineo));
	}

	/**
	 * Metodo utilizado para informar a quantidade de determinado orgao.
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @return - quantidade de orgaos
	 * @throws Exception
	 */
	public int qntOrgao(String nome) throws Exception {
		if (!existeOrgao(nome)) {
			throw new Exception("Esse orgao nao existe.");
		}
		int qntOrgao = 0;
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				qntOrgao++;
			}
		}
		return qntOrgao;
	}

	/**
	 * Metodo utilizado para informar a quantidade total de orgaos no banco de
	 * orgoas.
	 * 
	 * @return - quantidade total de orgao no banco de orgaos
	 */
	public int qntTotalOrgaos() {
		return bancoDeOrgaos.size();
	}

}
