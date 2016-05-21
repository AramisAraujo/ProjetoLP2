package procedimento;

import java.time.LocalDate;

import paciente.Prontuario;

/**
 * 
 * @author dantaselton
 *
 */
public class RedesignacaoSexual implements Procedimento {
	
	private final double PRECO = 9300.00;
	private final int PONTOS = 130;
	
	private String nomeMedico;
	private LocalDate dataDaConsulta;
	
	public RedesignacaoSexual(String nomeMedico, LocalDate dataRealizacao) {
		this.nomeMedico = nomeMedico;
		this.dataDaConsulta = dataRealizacao;
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
		return "--> Redesignacao sexual:\n"+
				"....... Data: "+this.dataDaConsulta.toString()+" Medico: "+this.nomeMedico;
	}

}
