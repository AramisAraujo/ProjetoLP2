package cartao;

/**
 * Master
 * Classe que representa um nivel intermediario de fidelidade,
 * concedendo descontos e bonus em diversas operacoes.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

public class Master implements Fidelidade{

	private final double TAXA_DISCONTO = 0.85;
	private final double BONUS_PONTOS = 1.05;

	@Override
	public int calcularPontos(int pontos) {
		
		//pontos = (int) (pontos*BONUS_PONTOS);  //Bonificacao ignorada
		
		return pontos;

	}

	@Override
	public double aplicarDesconto(double valor) {

		return valor * TAXA_DISCONTO;
	}

}
