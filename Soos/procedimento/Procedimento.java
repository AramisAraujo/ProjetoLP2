package procedimento;

import paciente.Paciente;

/**
 * 
 * @author dantaselton
 *
 */
public interface Procedimento {
	
	public abstract void realizaProcedimento(Paciente paciente) throws Exception;
	
	public abstract double getPreco();
}
