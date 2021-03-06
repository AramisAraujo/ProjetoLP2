package procedimento;

import java.time.LocalDate;

import paciente.Prontuario;


/**
 *RedesignacaoSexual
 * Classe que representa um procedimento de Redesignacao Sexual. 
 * Possui metodos que realizam tal operacao em um paciente, bonificando seu cartao fidelidade e somando 
 * gastos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

public class RedesignacaoSexual implements Procedimento {
	
	private final double PRECO = 9300.00;
	private final int PONTOS = 130;
	
	private String medicoResponsavel;
	private LocalDate dataDaConsulta;
	
	public RedesignacaoSexual(String nomeMedico, LocalDate dataRealizacao) {
		this.medicoResponsavel = nomeMedico;
		this.dataDaConsulta = dataRealizacao;
	}
	

    /**
     * RealizaProcedimento
     * Metodo que realiza o procedimento de uma cirurgia de redeseginacao
     * sexual (trocar o genero do paciente).
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
		
		String desc = "";
		
		 desc += String.format("--> Redesignacao sexual %n");
		 desc += String.format("....... Data: %s ", this.dataDaConsulta.toString());
		 desc += String.format("Medico: %s", this.medicoResponsavel);
			
		return desc;
	}

}
