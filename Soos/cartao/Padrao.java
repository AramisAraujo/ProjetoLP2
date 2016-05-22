package cartao;

/**
 * Padrao
 * Classe que representa um nivel basico de fidelidade de um cartao
 * nao possui vantagens ou bonus em operacoes.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 *
 */

public class Padrao implements Fidelidade {

	private final double TAXA_DISCONTO = 1.0;
	private final double BONUS_PONTOS = 1.0;

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
