package procedimento;

import paciente.Prontuario;

/**
 * Classe utilizada para implementar os procedimentos relacionados a Consulta
 * Clinica.
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
	
	public ConsultaClinica() {
		
	}

	/**
     * Metodo utilizado para realizar os procedimentos de uma consulta clinica
     * (somar os gastos e os pontos do paciente).
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
		return "Consulta clinica";
	}

}
