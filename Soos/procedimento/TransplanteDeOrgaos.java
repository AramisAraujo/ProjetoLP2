package procedimento;

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
	
	public TransplanteDeOrgaos() {
		
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
		return "Transpante de orgao";
	}


}
