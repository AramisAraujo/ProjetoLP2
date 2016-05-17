package procedimento;

import paciente.Prontuario;


/**
* Classe utilizada para implementar os procedimentos relacionados a
* Redesignacao Sexual.
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
     * Metodo utilizado para realizar os procedimentos de uma redeseginacao
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
