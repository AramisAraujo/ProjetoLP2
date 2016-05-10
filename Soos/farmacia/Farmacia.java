package farmacia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparators.NomeComparator;
import farmacia.CategoriasDeMedicamentos;
import farmacia.Medicamento;
import exceptions.ConsultaException;
import exceptions.MedicamentoException;
import factories.FactoryDeMedicamentos;

/**
 * Classe responsavel por gerenciar todos os medicamentos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class Farmacia {

	private FactoryDeMedicamentos factoryDeMedicamentos;
	private List<Medicamento> medicamentos;

	public Farmacia() {
		this.factoryDeMedicamentos = new FactoryDeMedicamentos();
		this.medicamentos = new ArrayList<Medicamento>();
	}

	/**
	 * Metodo utilizado para criar medicamentos de acordo com o seu tipo, que
	 * pode ser "medicamento generico" ou "medicamento de referencia".
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
	 * Metodo utilizado para verificar se um determinado medicamento existe
	 * informandando seu nome.
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
	 * Metodo utilizado para buscar um determinado medicamento pelo nome.
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
	 * Metodo utilizado para retornar o tipo do medicamento que tem o nome
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
	 * Metodo utilizado para retornar todos os medicamentos presentes no estoque
	 * da farmacia.
	 * 
	 * @return - lista de medicamentos ordenada pelo menor preco
	 */
	public List<Medicamento> getMedicamentosPreco() {
		Collections.sort(this.medicamentos);
		return this.medicamentos;
	}

	/**
	 * Metodo utilizado para retornar todos os medicamentos presentes no estoque
	 * da farmacia.
	 * 
	 * @return - lista de medicamentos ordenada por ordem alfabetica
	 */
	public List<Medicamento> getMedicamentosNome() {
		Collections.sort(this.medicamentos, new NomeComparator());
		return this.medicamentos;
	}

	/**
	 * Metodo utilizado para retornar o preco do medicamento que tem o nome
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
			throw new MedicamentoException(
					"Erro na consulta de medicamentos. Medicamento inexistente.");
		}
		return medicamento.getPreco();

	}

	/**
	 * Metodo utilizado para retornar a quantidade do medicamento que tem o nome
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
	 * Metodo utilizado para retornar as categorias que o medicamento que tem o
	 * nome passado como parametro pertence.
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
	 * Metodo utilizado para atualizar determinado atributo de um medicamento.
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
	 * Metodo utilizado para buscar medicamentos que pertencem a uma determinada
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
		
		ArrayList<String> medicamentosBusca = new ArrayList<String>();
		
	

		for (Medicamento medicamento : medicamentos) {
			if (medicamento.getCategorias().contains(categoria)) {
				medicamentosBusca.add(medicamento.getNome());
			}
		}

		if (medicamentos.isEmpty()) {
			throw new ConsultaException("medicamentos",
					"Nao ha remedios cadastrados nessa categoria.");
		}

		String resultado = "";

		for (int i = 0; i < medicamentos.size(); i++) {

			if (i == medicamentos.size() - 1) {

				resultado = resultado + medicamentos.get(i).getNome();
			} else {
				resultado = resultado + medicamentos.get(i).getNome() + ",";
			}
		}

		return resultado;
	}

	/**
	 * Metodo utilizado para buscar um determinado medicamento pelo nome.
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
		throw new MedicamentoException(
				"Erro na consulta de medicamentos. Medicamento nao cadastrado.");
	}

	/**
	 * Metodo utilizado para retornar todos os medicamentos presentes no estoque
	 * da farmacia, podendo ser organizada pelo preco ou por ordem alfabetica.
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
			Collections.sort(this.medicamentos);
			for (Medicamento medicamento : medicamentos) {
				estoque += medicamento.getNome() + ",";
			}
			if (estoque.length() > 0) {
				estoque = estoque.substring(0, estoque.length() - 1);
			}
			return estoque;
		} else if (ordenacao.equalsIgnoreCase("alfabetica")) {
			Collections.sort(this.medicamentos, new NomeComparator());
			for (Medicamento medicamento : medicamentos) {
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