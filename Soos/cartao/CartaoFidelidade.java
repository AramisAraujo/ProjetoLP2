package cartao;

/**
 * CartaoFidelidade
 * Classe que representa um cartao fidelidade qual 
 * estah associado a um paciente.
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
	 * AddPontos
	 * Metodo que adiciona pontos no cartao fidelidade.
	 * 
	 * @param pontos
	 *            - pontos que serao adicinados
	 */
	
	public void addPontos(int pontos) {

		pontos = this.nivelFidelidade.calcularPontos(pontos);
		
		this.pontos = this.pontos + pontos;
		
		this.checarFidelidade();

	}

	/**
	 * AplicarDesconto
	 * Metodo que aplica o descontos referentes as vantagens do cartao fidelidade.
	 * 
	 * @param valor
	 *            - valor que sera descontado
	 * @return - valor com o desconto
	 */
	
	public double aplicarDesconto(double valor) {

		valor = this.nivelFidelidade.aplicarDesconto(valor);

		return valor;

	}

	public int getPontos() {

		return this.pontos;
	}

	/**
	 * ChecarFidelidade
	 * Metodo que verifica a quantidade de pontos do cartao fidelidade
	 * possibilitando a troca dinâmica do nivel de fidelidade do cartao.
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
