package cartao;

public class Padrao implements Fidelidade{

	private final double TAXA_DISCONTO = 1.0;
	private final double BONUS_PONTOS = 1.0;
	
	@Override
	public int calcularPontos(int pontos) {
		
		pontos = (int) (pontos * BONUS_PONTOS);
		
		return pontos;
		
	}

	@Override
	public double aplicarDisconto(double valor) {
		
		return valor * TAXA_DISCONTO;
	}

}
