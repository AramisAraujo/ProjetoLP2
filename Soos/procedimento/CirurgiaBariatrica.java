package procedimento;

import java.io.Serializable;
import java.time.LocalDate;

import paciente.Prontuario;

/**
 * CirurgiaBariatrica
 * Classe que representa um procedimento de cirurgia bariatrica. 
 * Possui metodos que realizam tal operacao em um paciente, bonificando seu cartao fidelidade e somando 
 * gastos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

public class CirurgiaBariatrica implements Procedimento{
	
	private final double PRECO = 7600.00;
	private final int PONTOS = 100;
	private final double PERCENT = 0.10;
	
	private String medicoResponsavel;
	private LocalDate dataDaConsulta;
	
	public CirurgiaBariatrica(String nomeMedico, LocalDate dataRealizacao) {
		this.medicoResponsavel = nomeMedico;
		this.dataDaConsulta = dataRealizacao;
	}
	
	/**
	 * RealizaProcedimento
     * Metodo que realiza  o procedimentos de uma cirurgia bariatrica
     *  a qual reduz o peso do paciente em 10%.
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
		
		String desc = "";
		
		 desc += String.format("--> Cirurgia bariatrica: %n");
		 desc += String.format("....... Data: %s ", this.dataDaConsulta.toString());
		 desc += String.format("Medico: %s", this.medicoResponsavel);
			
		return desc;
	}

}
