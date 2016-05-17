package cartao;

public class Master implements Fidelidade{
	
	private final double TAXA_DISCONTO = 0.85;
	private final double BONUS_PONTOS = 1.05;

	@Override
	public int calcularPontos(int pontos) {
		
		//pontos = (int) (pontos*BONUS_PONTOS);
		
		return pontos;
		
	}

	@Override
	public double aplicarDisconto(double valor) {
		
		return valor*TAXA_DISCONTO;
	}

}
