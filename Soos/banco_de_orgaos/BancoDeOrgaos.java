package banco_de_orgaos;

import java.util.ArrayList;
import java.util.List;

import exceptions.BancoOrgaoException;
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
	 * Metodo utilizado para verificar se determinado orgao existe.
	 * 
	 * @param nome
	 *            - nome do orgao que sera verificado
	 * @param tipoSanguineo
	 *            - tipo sanguineo do orgao que sera verificado
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


	public Boolean existeOrgao(String nome, TipoSanguineo tipoSanguineo){
		
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome) && 
					orgao.getTipoSanguineo().equals(tipoSanguineo)) {
				return true;
			}
		}
		return false;
	}
	
	public List<String> getOrgaoPorSangue(TipoSanguineo tipoSanguineo){
		
		List<String> orgaosEncontrados = new ArrayList<String>();
		
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getTipoSanguineo().equals(tipoSanguineo)){
				
				String nomeOrgao = orgao.getNome();
				
				if(!orgaosEncontrados.contains(nomeOrgao)){
					orgaosEncontrados.add(nomeOrgao);

				}
			}
		}
		
		return orgaosEncontrados;
	}
	
	public List<String> getOrgaoPorNome (String nome){
		
		List<String> tiposDisponiveis = new ArrayList<String>();
		
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)){
				
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
	 * @throws Exception
	 */
	public boolean addOrgao(String nome, TipoSanguineo tipoSanguineo)throws BancoOrgaoException {
		
		Orgao orgao = factoryOrgaos.criaOrgao(nome, tipoSanguineo);
		
		return bancoDeOrgaos.add(orgao);
	}


	public boolean removeOrgao(String nome, TipoSanguineo tipoSanguineo)throws Exception {
		
		if (!this.existeOrgao(nome, tipoSanguineo)) { 
			throw new Exception("Orgao nao cadastrado.");
		}
		
		Orgao orgaoProcurado = null;
		
		for (Orgao orgaoAtual : bancoDeOrgaos) {
			
			if (orgaoAtual.getNome().equals(nome) && 
					orgaoAtual.getTipoSanguineo().equals(tipoSanguineo)) {
				
				orgaoProcurado = orgaoAtual;
			}
		}
		
		return bancoDeOrgaos.remove(orgaoProcurado);
	}

	/**
	 * Metodo utilizado para informar a quantidade de determinado orgao.
	 * 
	 * @param nome
	 *            - nome do orgao
	 * @return - quantidade de orgaos
	 * @throws Exception
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
	 * orgoas.
	 * 
	 * @return - quantidade total de orgao no banco de orgaos
	 */
	public int qntTotalOrgaos() {
		return bancoDeOrgaos.size();
	}

}
