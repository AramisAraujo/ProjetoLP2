package exceptions;

import java.time.LocalDate;

/**
 * A classe LancaExcecao contem metodos que verificam a validade dos parametros passados aos outros
 * metodos do codigo. Caso nao seja o esperado, throws exception.
 * 
 * @author Elton Dantas
 */
public class VerificaExcecao {
	
	public static void checkEmptyString(String umaString,String atributo) throws Exception {
		if (umaString == null || umaString.trim().isEmpty()) {
			throw new Exception(atributo+" nao pode ser vazio.");
		}
	}
	
	public static boolean checarData(LocalDate birthDate) throws Exception {
		int thisYear = LocalDate.now().getYear();
		
		if(birthDate.getYear() > thisYear){
			throw new Exception("Data invalida.");
		}
		return true;
		
	}
	
	public static void checarPeso(double peso) throws PacienteException {
		if (peso < 0) {
			throw new PacienteException("Peso do paciente nao pode ser negativo.");
		}
		return true;
	}
	
	public static boolean checarSexoBiologico(String sexoBiologico) throws PacienteException {
		if (sexoBiologico != "feminino" || sexoBiologico != "masculino") {
			throw new PacienteException("Sexo biologico nao identificado.");
		}
		return true;
	}
	
	public static void checarTipoSanguineo(String tipoSanguineo) throws PacienteException {
		if (!tiposDeSangue.contains(tipoSanguineo)) {
			throw new PacienteException();
		}
	}
	

}
