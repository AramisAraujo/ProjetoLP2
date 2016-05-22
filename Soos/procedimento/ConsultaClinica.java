package procedimento;

import java.time.LocalDate;

import paciente.Prontuario;

/**
 * ConsultaClinica
 * Classe que representa uma consulta clinica. 
 * Possui metodos que  bonificam o  cartao fidelidade do paciente  e somam
 * gastos a seu prontuario.
 * 
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
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
     * Metodo que realiza uma consulta clinica.
     * (soma os gastos e os pontos do cartao fidelidade do paciente).
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
