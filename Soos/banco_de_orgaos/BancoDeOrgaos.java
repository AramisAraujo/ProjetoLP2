package banco_de_orgaos;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerificaExcecao;
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

	private static List<Orgao> bancoDeOrgaos;
	private FactoryOrgaos factoryOrgaos;

	public BancoDeOrgaos() {
		bancoDeOrgaos = new ArrayList<Orgao>();
		factoryOrgaos = new FactoryOrgaos();
	}

	/**
	 * Checa se um orgao existe e se eh compativel com o sangue especificado
	 * 
	 * @param nome
	 *            - nome do orgao que sera verificado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao que sera verificado
	 */
	public static boolean checarOrgaoCompativel(String nome, TipoSanguineo tipoSanguineo) throws Exception {
		
		VerificaExcecao.checkEmptyParameter(nome, "Nome do orgao");
		VerificaExcecao.checkEmptyParameter(tipoSanguineo, "TipoSanguineo");
		
		boolean existe = false;
		boolean compativel = false;
		
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				existe = true;
				if (orgao.getTipoSanguineo().equals(tipoSanguineo)) {
					compativel = true;
				}
			}
		}
		
		if (existe == false) {
			throw new Exception("O banco de orgaos nao possui o orgao especificado.");
		}
		
		if (compativel == false) {
			throw new Exception("O paciente nao eh compativel com o orgao disponivel no banco.");	
		}
		
		return compativel;
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
	public Orgao getOrgao(String nome, TipoSanguineo tipoSanguineo)
			throws Exception {
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)
					&& orgao.getTipoSanguineo().equals(tipoSanguineo)) {
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
	public boolean addOrgao(String nome, TipoSanguineo tipoSanguineo)
			throws Exception {
		Orgao orgao = factoryOrgaos.criaOrgao(nome, tipoSanguineo);
		return bancoDeOrgaos.add(orgao);
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
	public static boolean removeOrgao(String nome, TipoSanguineo tipoSanguineo)
			throws Exception {
		if (!checarOrgaoCompativel(nome, tipoSanguineo)) { // ajeitar as exceptions dps
			throw new Exception("Esse orgao nao existe.");
		}
		
		Orgao orgao = null;
		
		for (Orgao orgaoAtual : bancoDeOrgaos) {
			
			if (orgaoAtual.getNome().equals(nome)
					&& orgaoAtual.getTipoSanguineo().equals(tipoSanguineo)) {
				
				orgao = orgaoAtual;
			}
		}
		return bancoDeOrgaos.remove(orgao);
	}

	/**
	 * Metodo utilizado para informar a quantidade de determinado orgao.
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @return - quantidade de orgaos
	 * @throws Exception
	 */
	public static int qntOrgao(String nome) throws Exception {
		int qntOrgao = 0;
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				qntOrgao++;
			}
		}
		if (qntOrgao == 0) {
			throw new Exception("Esse orgao nao existe."); // ajeitar exceptions
															// dos
		}
		return qntOrgao;

	}

	/**
	 * Metodo utilizado para informar a quantidade total de orgaos no banco de
	 * orgoas.
	 * 
	 * @return - quantidade total de orgao no banco de orgaos
	 */
	public static int qntTotalOrgaos() {
		return bancoDeOrgaos.size();
	}

}
