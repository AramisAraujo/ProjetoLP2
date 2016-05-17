package procedimento;

import paciente.Prontuario;

/**
 * Interface criada para definir os metodos de procedimentos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public interface Procedimento {
	

    /**
     * Metodo utilizado para realizar determinado procedimento.
     * 
     * @param paciente
     *            - paciente que realizara o procedimento
     * @throws Exception
     *             - excecao lancada caso ocorra algum erro
     */
	public abstract void realizaProcedimento(Prontuario prontuario) throws Exception;
	
	public abstract double getPreco();
}
