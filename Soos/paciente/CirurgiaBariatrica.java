package paciente;

/**
 * 
 * @author dantaselton
 *
 */
public class CirurgiaBariatrica implements Procedimento {
	
	private final double PRECO = 7600.00;
	private final double PERCENT = 0.15;
	
	public CirurgiaBariatrica() {}
	
	/**
	 * A cirurgia bariatrica reduz o peso do paciente em 15%.
	 */
	@Override
	public void realizaProcedimento(Paciente paciente) throws Exception {
		double novoPeso = paciente.getPeso() - paciente.getPeso()*PERCENT;
		paciente.setPeso(novoPeso);
		paciente.somaGastos(PRECO);
	}
	
	@Override
	public double getPreco() {
		return this.PRECO;
	}
	
	public String toString() {
		return "Cirurgia bariatrica";
	}

}
