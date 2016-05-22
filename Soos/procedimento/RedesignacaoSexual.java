package procedimento;

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
	
	public RedesignacaoSexual() {
		
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
		return "Redesignacao sexual";
	}

}
