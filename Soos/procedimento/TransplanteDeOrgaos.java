package procedimento;

import java.time.LocalDate;

import paciente.Prontuario;

/**
 * TransplanteDeOrgaos
 * Classe que representa um procedimento de um Transplante de Orgaos. 
 * Possui metodos que realizam tal operacao em um paciente, bonificando seu cartao fidelidade e somando 
 * gastos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

public class TransplanteDeOrgaos implements Procedimento {
	
	private final double PRECO = 12500.00;
	private final int PONTOS = 160;
	
	private String medicoResponsavel, nomeOrgao;
	private LocalDate dataDaConsulta;
	
	public TransplanteDeOrgaos(String nomeMedico, LocalDate dataRealizacao, String nomeOrgao) {
		this.medicoResponsavel = nomeMedico;
		this.dataDaConsulta = dataRealizacao;
		this.nomeOrgao = nomeOrgao;
	}
	
	@Override
	public void realizaProcedimento(Prontuario prontuario) throws Exception {
		prontuario.somaGastos(PRECO);
		prontuario.somaPontos(PONTOS);
	}

	@Override
	public double getPreco() {
		return this.PRECO;
	}
	
public String toString() {
		
		String desc = "";
		
		 desc += String.format("--> Transplante de orgaos: %n");
		 desc += String.format("....... Data: %s ", this.dataDaConsulta.toString());
		 desc += String.format("Medico: %s", this.medicoResponsavel);
			
		return desc;
	}


}
