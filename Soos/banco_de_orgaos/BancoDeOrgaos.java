package banco_de_orgaos;

import java.util.ArrayList;
import java.util.List;


import exceptions.BancoOrgaoException;
import exceptions.VerificaExcecao;
import factories.FactoryOrgaos;
import paciente.TipoSanguineo;

/**
 * Classe utilizada para armazenar e gerenciar orgaos fornecidos por doadores.
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

	 * Metodo utilizado para verificar se determinado orgao existe apenas
	 * informando o nome do mesmo.
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
	 * Metodo utilizado para verificar se determinado orgao existe.
	 * Checa se um orgao existe e se eh compativel com o sangue especificado
	 * 
	 * @param nome
	 *            - nome do orgao que sera verificado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao que sera verificado
	 */
	public Boolean existeOrgao(String nome, TipoSanguineo tipoSanguineo) {

		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome) && orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				return true;
			}
		}
		return false;
	}
			
	public boolean checarOrgaoCompativel(String nome, TipoSanguineo tipoSanguineo) throws Exception {
		
		VerificaExcecao.checkEmptyParameter(nome, "Nome do orgao");
		VerificaExcecao.checkEmptyParameter(tipoSanguineo, "TipoSanguineo");
				
		if (!existeOrgao(nome)) {
			throw new Exception("Banco nao possui o orgao especificado.");
		}
		
		if (!existeOrgao(nome, tipoSanguineo)) {
			throw new Exception("Banco nao possui o orgao especificado.");	
		}
		
		return true;
	}

	/**
	 * Metodo utilizado para retornar todos os orgaos que sao de um determinado
	 * tipo sanguineo.
	 * 
	 * @param tipoSanguineo
	 *            - tipo sanguineo informado
	 * @return - lista com todos os orgaos que pertencem ao tipo sanguineo
	 *         especificado
	 */
	public List<String> getOrgaoPorSangue(TipoSanguineo tipoSanguineo) {

		List<String> orgaosEncontrados = new ArrayList<String>();

		for (Orgao orgao : this.bancoDeOrgaos) {
			
			if (orgao.getTipoSanguineo().equals(tipoSanguineo)) {

				String nomeOrgao = orgao.getNome();

				if (!orgaosEncontrados.contains(nomeOrgao)) {
					orgaosEncontrados.add(nomeOrgao);

				}
			}
		}

		return orgaosEncontrados;
	}

	/**
	 * Metodo utilizado para retornar todos os orgaos que possuem o nome igual
	 * ao informado como parametro.
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @return - lista com todos os orgaos que possuem o mesmo nome do informado
	 *         como parametro
	 */
	public List<String> getOrgaoPorNome(String nome) {

		List<String> tiposDisponiveis = new ArrayList<String>();

		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {

				String tipo = orgao.getTipoSanguineo().toString();

				tiposDisponiveis.add(tipo);
			}

		}

		return tiposDisponiveis;

	}

	/**
	 * Metodo utilizado para criar e adicionar orgaos ao banco de orgaos.
	 * 
	 * @param nome
	 *            - nome do orgao que sera criado e adicionado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do do orgao que sera criado e adicionado
	 * @throws BancoOrgaoException
	 *             - excecao lancada caso ocorra algum erro
	 */
	public boolean addOrgao(String nome, TipoSanguineo tipoSanguineo) throws BancoOrgaoException {

		Orgao orgao = factoryOrgaos.criaOrgao(nome, tipoSanguineo);
		

		return bancoDeOrgaos.add(orgao);
	}

	/**
	 * Metodo utilizado para remover determinado orgao.
	 * 
	 * @param nome
	 *            - nome do orgao que sera removido
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao que sera removido
	 * @return - true, se o orgao foi removido e nao ocorreu nenhum erro
	 * @throws Exception
	 *             - excecao lancada caso nao exista nenhum orgao com as
	 *             informacoes passadas como parametro
	 */

	public boolean removeOrgao(String nome, TipoSanguineo tipoSanguineo) throws Exception {

		if (!this.existeOrgao(nome, tipoSanguineo)) {
			throw new Exception("Orgao nao cadastrado.");
		}

		Orgao orgaoProcurado = null;

		for (Orgao orgaoAtual : bancoDeOrgaos) {

			if (orgaoAtual.getNome().equals(nome) && orgaoAtual.getTipoSanguineo().equals(tipoSanguineo)) {

				orgaoProcurado = orgaoAtual;
			}
		}

		return this.bancoDeOrgaos.remove(orgaoProcurado);
	}

	/**
	 * Metodo utilizado para informar a quantidade de determinado orgao.
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @return - quantidade de orgaos
	 * @throws BancoOrgaoException
	 *             - execao lancada caso nao exista nenhum orgao com o nome
	 *             passado como parametro cadastrado
	 */
	public int qntOrgao(String nome) throws BancoOrgaoException {

		int qntOrgao = 0;

		for (Orgao orgao : bancoDeOrgaos) {

			if (orgao.getNome().equals(nome)) {

				qntOrgao++;
			}
		}
		if (qntOrgao == 0) {

			throw new BancoOrgaoException("Orgao nao cadastrado.");

		}
		return qntOrgao;

	}

	/**
	 * Metodo utilizado para informar a quantidade total de orgaos no banco de
	 * orgaos.
	 * 
	 * @return - quantidade total de orgao no banco de orgaos
	 */
	public int qntTotalOrgaos() {
		return bancoDeOrgaos.size();
	}

}
