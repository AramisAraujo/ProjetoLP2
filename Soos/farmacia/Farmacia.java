package farmacia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import comparators.NomeComparator;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Medicamento;
import exceptions.ConsultaException;
import exceptions.MedicamentoException;
import factories.FactoryDeMedicamentos;

/**
 * Farmacia
 * Classe que representa uma farmacia.
 * Eh responsavel por armazenar e gerenciar medicamentos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */

public class Farmacia implements Serializable{

	private FactoryDeMedicamentos factoryDeMedicamentos;
	private Set<Medicamento> medicamentos;

	public Farmacia() {
		this.factoryDeMedicamentos = new FactoryDeMedicamentos();
		this.medicamentos = new HashSet<Medicamento>();
	}

	/**
	 * CadastraMedicamento
	 * Metodo que delega a realizacao do cadastro de um medicamento de acordo com o seu tipo, que
	 * pode ser "Generico" ou "De referencia".
	 * 
	 * @param nome
	 *            - nome do medicamento que sera criado
	 * @param tipo
	 *            - tipo do medicamento que sera criado (medicamento generico ou
	 *            medicamento de refenrencia)
	 * @param preco
	 *            - preco do medicamento que sera criado
	 * @param quantidade
	 *            - quantidade do medicamento que sera criado
	 * @param categorias
	 *            - categorias as quais o medicamento que sera criado pertence
	 * @return - medicamento criado
	 * @throws Exception
	 *             - excecao lancada caso ocorra algum erro
	 */
	
	public boolean cadastraMedicamento(String nome, String tipo, double preco,
			int quantidade, List<CategoriasDeMedicamentos> categorias)
			throws Exception {
		if (existeMedicamento(nome)) {
			throw new MedicamentoException(
					"Esse medicamento ja foi cadastrado.");
		}
		Medicamento medicamento = factoryDeMedicamentos.criaMedicamento(nome,
				preco, quantidade, categorias, tipo);
		medicamentos.add(medicamento);
		return true;
	}

	/**
	 * ExisteMedicamento
	 * Metodoque verifica  se um determinado medicamento existe dado 
	 * um nome de medicamento.
	 * 
	 * @param nome
	 *            - nome do medicamento que sera verificado
	 * @return - true, se o medicamento existe ou false, se o medicamento nao
	 *         existe
	 */
	
	public boolean existeMedicamento(String nome) {
		for (Medicamento medicamento : medicamentos) {
			if (medicamento.getNome().equals(nome)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * BuscaMedicamento
	 * Metodo que realiza a busca por nome de  um medicamento  na colecao da farmacia.
	 * 
	 * @param nome
	 *            - nome do medicamento a ser procurado
	 * @return - medicamento com o nome especificado ou null se tal nao existir.
	 */
	
	public Medicamento buscaMedicamento(String nome) {
		
		for (Medicamento medicamento : medicamentos) {
			
			if (medicamento.getNome().equals(nome)) {
				
				return medicamento;
			}
		}
		return null;
	}

	/**
	 * GetTipoMedicamento
	 * Metodo que expressa o tipo do medicamento que tem o nome
	 * passado como parametro.
	 * 
	 * @param nomeMedicamento
	 *            - nome do medicamento
	 * @return - tipo do medicamento
	 * @throws LogicaException
	 *             - excessao lancada caso nao exista nenhum medicamento com o
	 *             nome especificado
	 */
	
	public String getTipoMedicamento(String nomeMedicamento)
			throws MedicamentoException {
		Medicamento medicamento = buscaMedicamento(nomeMedicamento);
		return medicamento.getTipo();

	}

	/**
	 * GetMedicamentosPreco
	 * Metodo que expressa todos os medicamentos presentes no estoque
	 * da farmacia ordenados por preco.
	 * 
	 * @return - lista de medicamentos ordenada pelo menor preco
	 */
	
	public List<Medicamento> getMedicamentosPreco() {
		
		List<Medicamento> meds = new ArrayList<Medicamento>();
		
		meds.addAll(this.medicamentos);
		
		Collections.sort(meds);
		return meds;
	}

	/**
	 * GetMedicamentosNome
	 * Metodo utilizado para retornar todos os medicamentos presentes no estoque
	 * da farmacia ordenados alfabeticamente.
	 * 
	 * @return - lista de medicamentos ordenada por ordem alfabetica
	 */
	
	public List<Medicamento> getMedicamentosNome() {
		
		List<Medicamento> meds = new ArrayList<Medicamento>();
		
		meds.addAll(this.medicamentos);
		
		Collections.sort(meds, new NomeComparator());
		return meds;
	}

	/**
	 * GetPreco
	 * Metodo que expressa o preco do medicamento que tem o nome
	 * passado como parametro.
	 * 
	 * @param nomeMedicamento
	 *            - nome do medicamento
	 * @return - preco do medicamento
	 * @throws MedicamentoException
	 *             - excessao lancada caso nao exista nenhum medicamento com o
	 *             nome especificado
	 */
	
	public double getPreco(String nomeMedicamento) throws MedicamentoException {
		
		Medicamento medicamento = buscaMedicamento(nomeMedicamento);
		
		if (medicamento == null) {
			
			throw new MedicamentoException("Medicamento nao cadastrado.");
		}
		
		return medicamento.getPreco();

	}

	/**
	 * GetQuantidade
	 * Metodo que expressa a quantidade de um medicamento no estoque da farmacia que tem o nome
	 * passado como parametro.
	 * 
	 * @param nomeMedicamento
	 *            - nome do medicamento
	 * @return - quantidade do medicamento
	 * @throws MedicamentoException
	 *             - excessao lancada caso nao exista nenhum medicamento com o
	 *             nome especificado
	 */
	
	public int getQuantidade(String nomeMedicamento)
			throws MedicamentoException {
		Medicamento medicamento = buscaMedicamento(nomeMedicamento);
		if (medicamento == null) {
			throw new MedicamentoException(
					"Erro na consulta de medicamentos. Medicamento inexistente.");
		}
		return medicamento.getQuantidade();

	}

	/**
	 * GetCategoriasMedicamento
	 * Metodo que expressa todas as categorias que um medicamento possui
	 * dado seu nome como parametro.
	 * 
	 * @param nomeMedicamento
	 *            - nome do medicamento
	 * @return - categorias as quais o medicamento pertence
	 * @throws MedicamentoException
	 *             - excessao lancada caso nao exista nenhum medicamento com o
	 *             nome especificado
	 */
	
	public String getCategoriasMedicamento(String nomeMedicamento)
			throws MedicamentoException {
		Medicamento medicamento = buscaMedicamento(nomeMedicamento);
		if (medicamento == null) {
			throw new MedicamentoException(
					"Erro na consulta de medicamentos. Medicamento inexistente.");
		}
		return medicamento.getCategorias();
	}

	/**
	 * AtualizaMedicamento
	 * Metodo que atualiza determinado atributo de um medicamento.
	 * As opcoes validas sao:
	 * 	-Preco
	 * 	-Quantidade
	 * 	-Nome
	 * 	-Tipo
	 * 
	 * @param nome
	 *            - nome do medicamento que sera atualizado
	 * @param atributo
	 *            - atributo que sera atualizado
	 * @param novoValor
	 *            - novo valor do atributo que sera atualizado
	 * 
	 * @throws Exception
	 *             representando um caso de erro.
	 */

	public void atualizaMedicamento(String nome, String atributo,
			String novoValor) throws Exception {

		if (buscaMedicamento(nome) != null) {

			switch (atributo.toUpperCase()) {

			case "PRECO":

				double preco = Double.valueOf(novoValor).doubleValue();
				buscaMedicamento(nome).setPreco(preco);

				break;

			case "QUANTIDADE":

				int quantidade = Integer.parseInt(novoValor);
				buscaMedicamento(nome).setQuantidade(quantidade);

				break;

			case "NOME":

				throw new MedicamentoException(
						"Nome do medicamento nao pode ser alterado.");

			case "TIPO":

				throw new MedicamentoException(
						"Tipo do medicamento nao pode ser alterado.");

			default:
				throw new MedicamentoException("Esse atributo nao existe.");
			}

		} else {
			throw new MedicamentoException("Medicamento nao cadastrado.");
		}
	}

	/**
	 * ConsultaMedCategoria
	 * Metodo que realiza a busca por medicamentos que pertencem a uma determinada
	 * categoria.
	 * 
	 * @param nomeCategoria
	 *            - nome da categoria a ser pesquisada
	 * @return - nome dos medicamentos que pertencem a categoria especificada
	 * @throws MedicamentoException
	 *             - excessao lancada caso ocorra algum erro
	 * @throws ConsultaException
	 */
	
	public String consultaMedCategoria(String categoria)
			throws MedicamentoException, ConsultaException {
		
		ArrayList<Medicamento> medicamentosBusca = new ArrayList<Medicamento>();
		CategoriasDeMedicamentos category;
		
		try {
			category = CategoriasDeMedicamentos.valueOf(categoria.toUpperCase());
		} catch (Exception e) {
			throw new MedicamentoException("Categoria invalida.");
		}
		
		for (Medicamento medicamento : this.medicamentos) {
			if (medicamento.getCategorias().contains(categoria)) {
				medicamentosBusca.add(medicamento);
			}
		}
		
		Collections.sort(medicamentosBusca);

		if (medicamentosBusca.isEmpty()) {
			throw new MedicamentoException("Nao ha remedios cadastrados nessa categoria.");
		}

		String resultado = "";

		for (Medicamento Med : medicamentosBusca) {
			
			resultado = resultado + Med.getNome() + ",";
			
		}
		
		resultado = resultado.substring(0,resultado.length() - 1);

		return resultado;
	}

	/**
	 * ConsultaMedNome
	 * Metodo que realiza a  busca por um determinado medicamento pelo nome.
	 * 
	 * @param nome
	 *            - nome do medicamento a ser procurado
	 * @return - medicamento com o nome especificado
	 * @throws MedicamentoInexistenteException
	 *             - excessao lancada caso nao exista nenhum medicamento com
	 *             esse nome
	 */
	
	public Medicamento consultaMedNome(String nome) throws MedicamentoException {
		for (Medicamento medicamento : medicamentos) {
			if (medicamento.getNome().equals(nome)) {
				return medicamento;
			}
		}
		throw new MedicamentoException("Medicamento nao cadastrado.");
	}

	/**
	 * GetEstoqueFarmacia
	 * Metodo que expressa todos os medicamentos presentes no estoque
	 * da farmacia, podendo ser organizados por preco ou por ordem alfabetica.
	 * 
	 * @param ordenacao
	 *            - tipo de ordenacao
	 * @return - lista com todos os medicamentos
	 * @throws MedicamentoException
	 *             - excessao lancada caso o tipo de ordenacao seja invalida
	 */
	
	public String getEstoqueFarmacia(String ordenacao)
			throws MedicamentoException {
		
		String estoque = "";
		
		if (ordenacao.equalsIgnoreCase("preco")) {
			
			List<Medicamento> meds = new ArrayList<Medicamento>();
			
			meds.addAll(this.medicamentos);
			
			Collections.sort(meds);
			
			
			for (Medicamento medicamento : meds) {
				estoque += medicamento.getNome() + ",";
			}
			if (estoque.length() > 0) {
				estoque = estoque.substring(0, estoque.length() - 1);
			}
			return estoque;
			
		} else if (ordenacao.equalsIgnoreCase("alfabetica")) {
			
			List<Medicamento> meds = new ArrayList<Medicamento>();
			
			meds.addAll(this.medicamentos);
			
			Collections.sort(meds, new NomeComparator());
			
			for (Medicamento medicamento : meds) {
				estoque += medicamento.getNome() + ",";
			}
			if (estoque.length() > 0) {
				estoque = estoque.substring(0, estoque.length() - 1);
			}
			return estoque;
		}
		throw new MedicamentoException("Tipo de ordenacao invalida.");
	}

}