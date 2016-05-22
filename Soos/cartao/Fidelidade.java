package cartao;

/**
 * Fidelidade
 * Interface que define os metodos que um nivel de fidelidade deve
 * implementar.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public interface Fidelidade {

	/**
	 * CalcularPontos
	 * Metodo que calcula os pontos que serao adicionados ao cartao
	 * podendo ser aplicado um bonus a essa pontuacao.
	 * 
	 * @param pontos
	 *            - pontos que serao adicionados de acordo com o calculo
	 * @return - inteiro representando a quantidade de pontos que foi adicionada
	 */
	public int calcularPontos(int pontos);

	/**
	 * AplicarDesconto
	 * Metodo utilizado para aplicar o desconto em um valor de acordo com o
	 * nivel de fidelidade do cartao.
	 * 
	 * @param valor
	 *            - valor que sera descontado
	 * @return - valor com desconto
	 */
	public double aplicarDesconto(double valor);

}
