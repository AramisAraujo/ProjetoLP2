package exceptions;

import java.util.List;

import paciente.TipoSanguineo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A classe LancaExcecao contem metodos que verificam a validade dos parametros passados aos outros
 * metodos do codigo. Caso nao seja o esperado, throws exception.
 * 
 * @author Elton Dantas
 */
public class VerificaExcecao {
	
	public static boolean checkEmptyString(String umaString,String atributo) throws PacienteException {
		if (umaString == null || umaString.trim().isEmpty()) {
			throw new PacienteException(atributo+" nao pode ser vazio");
		}
		return true;
	}
	
	public static boolean checarData(LocalDate birthDate) throws PacienteException {
		int thisYear = LocalDate.now().getYear();
		
		if(birthDate.getYear() > thisYear){
			throw new PacienteException("Data invalida.");
		}
		return true;
	}
	
	public static boolean checarPeso(double peso) throws PacienteException {
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
	
	public static boolean checarTipoSanguineo(String tipoSanguineo) throws PacienteException {
		if (!(TipoSanguineo.valueOf(tipoSanguineo) instanceof TipoSanguineo)) {
			throw new PacienteException("Tipo sanguineo invalido.");
		}
		return true;
	}

}
