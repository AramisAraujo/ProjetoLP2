package paciente;

/**
 * 
 * @author dantaselton
 *
 */
public class ConsultaClinica implements Procedimento {
	
	private final double PRECO = 350.00;
	
	public ConsultaClinica() {}

	@Override
	public void realizaProcedimento(Paciente paciente) {
		paciente.setGastos(PRECO);
	}
	
	@Override
	public double getPreco() {
		return this.PRECO;
	}
	
	public String toString() {
		return "Consulta clinica";
	}

}
