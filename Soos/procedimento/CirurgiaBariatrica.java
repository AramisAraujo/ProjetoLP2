package procedimento;

import java.time.LocalDate;

import paciente.Prontuario;

/**
 * 
 * @author dantaselton
 *
 */
public class CirurgiaBariatrica implements Procedimento {
	
	private final double PRECO = 7600.00;
	private final int PONTOS = 100;
	private final double PERCENT = 0.15;
	
	private String nomeMedico;
	private LocalDate dataDaConsulta;
	
	public CirurgiaBariatrica(String nomeMedico, LocalDate dataRealizacao) {
		this.nomeMedico = nomeMedico;
		this.dataDaConsulta = dataRealizacao;
	}
	
	/**
	 * A cirurgia bariatrica reduz o peso do paciente em 15%.
	 */
	@Override
	public void realizaProcedimento(Prontuario prontuario) throws Exception {
		double novoPeso = prontuario.getPeso() - prontuario.getPeso()*PERCENT;
		prontuario.corrigePeso(novoPeso);
		prontuario.somaGastos(PRECO);
		prontuario.somaPontos(PONTOS);
	}
	
	@Override
	public double getPreco() {
		return this.PRECO;
	}
	
	public String toString() {
		return "--> Cirurgia bariatrica:\n"+
				"....... Data: "+this.dataDaConsulta.toString()+" Medico: "+this.nomeMedico;
	}

}
