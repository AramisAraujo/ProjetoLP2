package cartao;

/**
 * Classe criada para ser implementados os metodos do cartao fidelidade de
 * acordo com o nivel VIP.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */
public class VIP implements Fidelidade {

	private final double TAXA_DISCONTO = 0.7;
	private final double BONUS_PONTOS = 1.10;

	@Override
	public int calcularPontos(int pontos) {
		
		//pontos = (int) (pontos*BONUS_PONTOS);  //Bonificacao ignorada

		return pontos;

	}

	@Override
	public double aplicarDisconto(double valor) {

		return valor * TAXA_DISCONTO;
	}

}
