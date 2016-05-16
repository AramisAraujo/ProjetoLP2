package procedimento;

import paciente.Prontuario;

/**
 * 
 * @author dantaselton
 *
 */
public class RedesignacaoSexual implements Procedimento {
	
	private final double PRECO = 9300.00;
	private final int PONTOS = 130;
	
	public RedesignacaoSexual() {
		
	}
	
	/**
	 * A redesignacao sexual troca o genero do paciente.
	 */
	@Override
	public void realizaProcedimento(Prontuario prontuario){
		prontuario.trocarGenero();
		prontuario.somaGastos(PRECO);
		prontuario.somaPontos(PONTOS);
	}
	
	@Override
	public double getPreco() {
		return this.PRECO;
	}
	
	public String toString() {
		return "Redesignacao sexual";
	}

}
