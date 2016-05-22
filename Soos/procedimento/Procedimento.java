package procedimento;

import paciente.Prontuario;

/**
 * Procedimento
 * Interface criada para definir comportamentos essenciais a um procedimento.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public interface Procedimento {
	

    /**
     * RealizaProcedimento
     * Metodo que realiza o procedimento.
     * 
     * @param paciente
     *            - paciente que realizara o procedimento
     * @throws Exception
     *             - excecao lancada caso ocorra algum erro
     */
	
	public abstract void realizaProcedimento(Prontuario prontuario) throws Exception;
	
	public abstract double getPreco();
}
