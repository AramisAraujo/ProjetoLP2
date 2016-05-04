package exceptions;


import paciente.TipoSanguineo;
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
	
	public static boolean checarData(LocalDate birthDate) throws ProntuarioException {
		int thisYear = LocalDate.now().getYear();
		
		if(birthDate.getYear() > thisYear){
			throw new ProntuarioException("Data invalida.");
		}
		return true;
	}
	
	public static boolean checarPeso(double peso) throws ProntuarioException {
		if (peso < 0) {
			throw new ProntuarioException("Peso do paciente nao pode ser negativo.");
		}
		return true;
	}
	
	public static boolean checarSexoBiologico(String sexoBiologico) throws ProntuarioException {
		if ((!sexoBiologico.equalsIgnoreCase("masculino")) && (!sexoBiologico.equalsIgnoreCase("feminino"))) {
			throw new ProntuarioException("Sexo biologico nao identificado.");
		}
		return true;
	}

}
