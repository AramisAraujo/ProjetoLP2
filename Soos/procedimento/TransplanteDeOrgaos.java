package procedimento;

import paciente.Paciente;

/**
 * 
 * @author Elton Dantas
 *
 */
public class TransplanteDeOrgaos implements Procedimento {
	
	private final double PRECO = 12500.00;
	private final int PONTOS = 160;
	
	public TransplanteDeOrgaos() {
		
	}

	
	@Override
	public void realizaProcedimento(Paciente paciente) throws Exception {
		
		paciente.somaGastos(PRECO);
		paciente.somaPontos(PONTOS);
	}

	@Override
	public double getPreco() {
		return this.PRECO;
	}
	
	public String toString() {
		return "Transpante de orgao";
	}


}
