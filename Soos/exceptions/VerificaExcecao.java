package exceptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import procedimento.TipoProcedimento;

/**
 * VerificaExcecao
 * A classe VerificaExcecao contem metodos que verificam a validade de parametros
 * passados aos outros metodos. Caso tais parametros nao sejam corretos, exceptions
 * correspondentes sao lancadas.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */

public class VerificaExcecao {

	/**
	 * CheckEmptyString
	 * Metodo que verifica se determinada String eh nula ou vazia.
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
	 * CheckEmptyParameter
	 * Metodo que verifica se determinado objeto eh nulo (ou vazio).
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
	 * ChecarData
	 * Metodo que determina se uma data eh valida.
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
	 * ChecarValor
	 * Metodo que verifica se um double eh negativo.
	 * 
	 * @param valor
	 *            - double que sera verificado
	 * @param atributo
	 *            - nome do atributo que esta sendo verificado
	 * @throws Exception
	 *             - excecao lancada caso o double seja negativo
	 */
	
	public static void checarValor(double valor, String atributo) throws Exception {
		if (valor < 0) {
			throw new Exception(atributo + " nao pode ser negativo.");
		}
	}

	/**
	 * ChecarValor
	 * Metodo que verifica se um inteiro eh negativo.
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
	 * ChecarSexoBiologico
	 * Metodo que verifica se uma String que representa um sexo biologico eh valida para o sistema.
	 * 
	 * @param sexoBiologico
	 *            - sexo biologico que sera verificado
	 * @return - true, se o sexo biologico eh valido ou false caso nao seja
	 *         valido
	 * @throws Exception
	 *             - excecao lancada caso o sexo biologico nao seja valido
	 */
	
	public static boolean checarSexoBiologico(String sexoBiologico) throws Exception {
		
		if (!((sexoBiologico.equalsIgnoreCase("masculino")) || (sexoBiologico
				.equalsIgnoreCase("feminino")))) {
			
			throw new Exception("Sexo biologico nao identificado.");
		}
		
		return true;
	}

	/**
	 * ChecarGenero
	 * Metodo que verifica se uma String que representa um genero eh valida para o sistema.
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
	 * ChecarProcedimento
	 * Metodo que verifica se um procedimento eh reconhecido pelo sistema.
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
