package exceptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import procedimento.TipoProcedimento;

/**
 * A classe LancaExcecao contem metodos que verificam a validade dos parametros
 * passados aos outros metodos do codigo. Caso nao seja o esperado, throws
 * exception.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class VerificaExcecao {

	/**
	 * Metodo utilizado para checar se determinada string eh nula ou vazia.
	 * 
	 * @param umaString
	 *            - string que sera verificada
	 * @param atributo
	 *            - nome do atributo que esta sendo verificado
	 * @throws Exception
	 *             - excecao lancada caso a string seja nula ou vazia
	 */
	public static void checkEmptyString(String umaString, String atributo)
			throws Exception {
		if (umaString == null || umaString.trim().isEmpty()) {
			throw new Exception(atributo + " nao pode ser vazio.");
		}
	}

	/**
	 * Metodo utilizado para checar se determinado objeto eh nulo (vazio).
	 * 
	 * @param object
	 *            - objeto que sera verificado
	 * @param atributo
	 *            - nome do atributo que esta sendo verificado
	 * @throws Exception
	 *             - excecao lancada caso o objeto seja nulo
	 */
	public static void checkEmptyParameter(Object object, String atributo)
			throws Exception {
		if (object == null) {
			throw new Exception(atributo + " nao pode ser vazio.");
		}
		if (object instanceof String) {
			String palavra = (String) object;
			if (palavra.trim().isEmpty()) {
				throw new Exception(atributo + " nao pode ser vazio.");
			}
		}
	}

	/**
	 * Metodo utilizado para determinar se determinada data eh valida.
	 * 
	 * @param birthDate
	 *            - data que sera verificada
	 * @return - true, se a data eh valida e false caso nao seja valida
	 * @throws Exception
	 *             - excecao lancada caso a data seja invalida
	 */
	public static boolean checarData(LocalDate birthDate) throws Exception {
		int thisYear = LocalDate.now().getYear();

		if (birthDate.getYear() > thisYear) {
			throw new Exception("Data invalida.");
		}
		return true;
	}

	/**
	 * Metodo utilizado para checar se determinado double eh negativo.
	 * 
	 * @param valor
	 *            - double que sera verificado
	 * @param atributo
	 *            - nome do atributo que esta sendo verificado
	 * @throws Exception
	 *             - excecao lancada caso o double seja negativo
	 */
	public static void checarValor(double valor, String atributo)
			throws Exception {
		if (valor < 0) {
			throw new Exception(atributo + " nao pode ser negativo.");
		}
	}

	/**
	 * Metodo utilizado para checar se determinado inteiro eh negativo.
	 * 
	 * @param valor
	 *            - inteiro que sera verificado
	 * @param atributo
	 *            - nome do atributo que esta sendo verificado
	 * @throws Exception
	 *             - excecao lancada caso o inteiro seja negativo
	 */
	public static void checarValor(int valor, String atributo) throws Exception {

		if (valor < 0) {
			throw new Exception(atributo + " nao pode ser negativo.");
		}

	}

	/**
	 * Metodo utilizado para verificar se o sexo biologico eh valido.
	 * 
	 * @param sexoBiologico
	 *            - sexo biologico que sera verificado
	 * @return - true, se o sexo biologico eh valido ou false caso nao seja
	 *         valido
	 * @throws Exception
	 *             - excecao lancada caso o sexo biologico nao seja valido
	 */
	public static boolean checarSexoBiologico(String sexoBiologico)
			throws Exception {
		if (!((sexoBiologico.equalsIgnoreCase("masculino")) || (sexoBiologico
				.equalsIgnoreCase("feminino")))) {
			throw new Exception("Sexo biologico nao identificado.");
		}
		return true;
	}

	/**
	 * Metodo utilizado para chegar se determinado genero eh valido.
	 * 
	 * @param genero
	 *            - genero que sera verificado
	 * @return - true, se o genero eh valido ou false caso nao seja valido
	 * @throws Exception
	 *             - excecao lancada caso o genero nao seja valido
	 */
	public static boolean checarGenero(String genero) throws Exception {
		if (!((genero.equalsIgnoreCase("masculino")) || (genero
				.equalsIgnoreCase("feminino")))) {
			throw new Exception("Genero nao identificado.");
		}
		return true;
	}

	/**
	 * Metodo utilizado para checar se determinado procedimento eh valido.
	 * 
	 * @param nomeProcedimento
	 *            - nome do procedimento que sera testado
	 * @throws Exception
	 *             - excecao lancada caso o nome do procedimento nao seja valido
	 */
	public static void checarProcedimento(String nomeProcedimento)
			throws Exception {

		List<String> nomeProcedimentos = new ArrayList<String>();

		for (TipoProcedimento procedure : TipoProcedimento.values()) {

			nomeProcedimentos.add(procedure.toString());
		}

		if (!nomeProcedimentos.contains(nomeProcedimento)) {
			throw new Exception("Procedimento invalido.");
		}

	}

}
