package cartao;

/**
 * Interface criada para definir os metodos que os niveis de fidelidade irao
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
	 * Metodo utilizado para calcular os pontos que serao adicionados ao cartao.
	 * 
	 * @param pontos
	 *            - pontos que serao adicionados de acordo com o calculo
	 * @return - inteiro representando a quantidade de pontos que foi adicionada
	 */
	public int calcularPontos(int pontos);

	/**
	 * Metodo utilizado para aplicar o desconto em um valor de acordo com o
	 * nivel de fidelidade do cartao.
	 * 
	 * @param valor
	 *            - valor que sera descontado
	 * @return - valor com desconto
	 */
	public double aplicarDisconto(double valor);

}
