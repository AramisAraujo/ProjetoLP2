package paciente;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BancoProntuarios implements Serializable{

	
	private Map<String,Prontuario> prontuarios;
	
	
	public BancoProntuarios(){
		
		this.prontuarios = new HashMap<String,Prontuario>();
	}
}
