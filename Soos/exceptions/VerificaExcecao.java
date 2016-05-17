package exceptions;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import procedimento.TipoProcedimento;

/**
 * A classe LancaExcecao contem metodos que verificam a validade dos parametros passados aos outros
 * metodos do codigo. Caso nao seja o esperado, throws exception.
 * 
 * @author Elton Dantas
 */
public class VerificaExcecao {
	
	/**
	 * Verifica se uma String eh vazia.
	 * @param umaString
	 * @param atributo
	 * @throws Exception
	 */
	public static void checkEmptyString(String umaString,String atributo) throws Exception {
		if (umaString == null || umaString.trim().isEmpty()) {
			throw new Exception(atributo+" nao pode ser vazio.");
		}
	}
	
	/**
	 * Verifica se o objeto eh null
	 * Se o objeto nao for null e for String, verifica se eh uma string vazia.
	 * @param object
	 * @param atributo
	 * @throws Exception
	 */
	public static void checkEmptyParameter(Object object,String atributo) throws Exception {
		if (object == null) {
			throw new Exception(atributo+" nao pode ser vazio.");
		}
		if (object instanceof String) {
			String palavra = (String) object;
			if (palavra.trim().isEmpty()) {
				throw new Exception(atributo+" nao pode ser vazio.");
			}
		}
	}

	
	public static boolean checarData(LocalDate birthDate) throws Exception {
		int thisYear = LocalDate.now().getYear();
		
		if(birthDate.getYear() > thisYear){
			throw new Exception("Data invalida.");
		}
		return true;
	}
	
	public static void checarValor(double valor,String atributo) throws Exception {
		if (valor < 0) {
			throw new Exception(atributo+" nao pode ser negativo.");
		}	
	}
	
	public static void checarValor(int valor,String atributo) throws Exception {

		if (valor < 0) {
			throw new Exception(atributo+" nao pode ser negativo.");
		}
			
	}
	
	public static boolean checarSexoBiologico(String sexoBiologico) throws Exception {
		if (!((sexoBiologico.equalsIgnoreCase("masculino")) || (sexoBiologico.equalsIgnoreCase("feminino")))) {
			throw new Exception("Sexo biologico nao identificado.");
		}
		return true;
	}
	
	public static boolean checarGenero(String genero) throws Exception {
		if (!((genero.equalsIgnoreCase("masculino")) || (genero.equalsIgnoreCase("feminino")))) {
			throw new Exception("Genero nao identificado.");
		}
		return true;
	}

	public static void checarProcedimento(String nomeProcedimento) throws Exception{
		
		List<String> nomeProcedimentos = new ArrayList<String>();
		
		for (TipoProcedimento procedure : TipoProcedimento.values()) {
			
			nomeProcedimentos.add(procedure.toString());
		}
		
		if(!nomeProcedimentos.contains(nomeProcedimento)){
			throw new Exception("Procedimento invalido.");
		}
		
		
	}

}
