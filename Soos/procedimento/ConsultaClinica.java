package procedimento;

import java.time.LocalDate;

import paciente.Prontuario;

/**
 * 
 * @author dantaselton
 *
 */
public class ConsultaClinica implements Procedimento {
	
	private final double PRECO = 350.00;
	private final int PONTOS = 50;
	
	private String nomeMedico;
	private LocalDate dataDaConsulta;
	
	public ConsultaClinica(String nomeMedico, LocalDate dataRealizacao) {
		
		this.nomeMedico = nomeMedico;
		this.dataDaConsulta = dataRealizacao;
		
	}

	/**
	 * Consulta clinica apenas eh cobrado o procedimento
	 * @throws Exception 
	 */
	@Override
	public void realizaProcedimento(Prontuario prontuario){
		
		prontuario.somaGastos(PRECO);
		prontuario.somaPontos(PONTOS);
	}
	
	@Override
	public double getPreco() {
		return this.PRECO;
	}
	
	public String toString() {
		return "--> Consulta clinica:\n"+
				"....... Data: "+this.dataDaConsulta.toString()+" Medico: "+this.nomeMedico;
	}

}
