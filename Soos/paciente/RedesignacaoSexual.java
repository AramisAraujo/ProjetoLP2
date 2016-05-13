package paciente;

/**
 * 
 * @author dantaselton
 *
 */
public class RedesignacaoSexual implements Procedimento {
	
	private final double PRECO = 9300.00;
	
	public RedesignacaoSexual() {}
	
	/**
	 * A redesignacao sexual troca o genero do paciente.
	 */
	@Override
	public void realizaProcedimento(Paciente paciente) throws Exception {
		paciente.trocarGenero();
		paciente.somaGastos(PRECO);
	}
	
	@Override
	public double getPreco() {
		return this.PRECO;
	}
	
	public String toString() {
		return "Redesignacao sexual";
	}

}
