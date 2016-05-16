package procedimento;

import paciente.Prontuario;

/**
 * 
 * @author dantaselton
 *
 */
public interface Procedimento {
	
	public abstract void realizaProcedimento(Prontuario prontuario) throws Exception;
	
	public abstract double getPreco();
}
