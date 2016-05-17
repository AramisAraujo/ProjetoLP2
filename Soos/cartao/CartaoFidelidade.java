package cartao;

/**
 * Classe utilizada para gerenciar o cartao fidelidade.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public class CartaoFidelidade {

	private int pontos;
	private Fidelidade nivelFidelidade;

	private final int NIVEL_MASTER, NIVEL_VIP;

	public CartaoFidelidade() {

		this.pontos = 0;
		this.nivelFidelidade = new Padrao();

		this.NIVEL_MASTER = 150;
		this.NIVEL_VIP = 350;

	}

	/**
	 * Metodo utilizado para adicionar pontos ao cartao fidelidade.
	 * 
	 * @param pontos
	 *            - pontos que serao adicinados
	 */
	public void addPontos(int pontos) {

		pontos = this.nivelFidelidade.calcularPontos(pontos);

		this.pontos = pontos;

		this.checarFidelidade();

	}

	/**
	 * Metodo utilizado para aplicar o desconto no cartao fidelidade.
	 * 
	 * @param valor
	 *            - valor que sera descontado
	 * @return - valor com o desconto
	 */
	public double aplicarDisconto(double valor) {

		valor = this.nivelFidelidade.aplicarDisconto(valor);

		return valor;

	}

	public int getPontos() {

		return this.pontos;
	}

	/**
	 * Metodo utilizado para veficar a quantidade de pontos do cartao fidelidade
	 * e assim classificar o cartao de acordo com os niveis de fidelidade.
	 */
	private void checarFidelidade() {

		if (this.pontos < NIVEL_MASTER) {
			if (!this.nivelFidelidade.getClass().equals(Padrao.class)) {
				this.nivelFidelidade = new Padrao();

			}
		} else if (this.pontos >= NIVEL_MASTER && this.pontos < NIVEL_VIP) {

			if (!this.nivelFidelidade.getClass().equals(Master.class)) {
				this.nivelFidelidade = new Master();
			}
		} else {
			if (!this.nivelFidelidade.getClass().equals(VIP.class)) {
				this.nivelFidelidade = new VIP();
			}
		}

	}

}
