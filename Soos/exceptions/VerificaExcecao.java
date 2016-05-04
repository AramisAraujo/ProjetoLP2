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
	
	public static boolean checkEmptyString(String umaString,String atributo) throws PacienteException {
		if (umaString == null || umaString.trim().isEmpty()) {
			throw new PacienteException(atributo+" nao pode ser vazio.");
		}
		return true;
	}
	
	public static boolean checarData(LocalDate data) throws PacienteException {
		int thisYear = LocalDate.now().getYear();
		if(data.getYear() > thisYear){
			throw new PacienteException("Data invalida.");
		}
		return true;
	}
	
	public static boolean checarPeso(double valor) throws PacienteException {
		if (valor < 0) {
			throw new PacienteException("Peso do paciente nao pode ser negativo.");
		}
		return true;
	}
	
	public static boolean checarSexoBiologico(String sexoBiologico) throws PacienteException {
		if (!(sexoBiologico.equalsIgnoreCase("feminino") || sexoBiologico.equalsIgnoreCase("masculino"))) {
			throw new PacienteException("Sexo biologico nao identificado.");
		}
		return true;
	}
	
	public static boolean checarTipoSanguineo(String tipoSanguineo) throws PacienteException {
		for (TipoSanguineo tipo : TipoSanguineo.values()) {
			if (tipo.toString().equals(tipoSanguineo)) {
				return true;
			}
		}
		throw new PacienteException("Tipo sanguineo invalido.");
	}

}
