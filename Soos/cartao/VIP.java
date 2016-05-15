package cartao;

public class VIP implements Fidelidade{
	
	private final double TAXA_DISCONTO = 0.7;
	private final double BONUS_PONTOS = 0.9;

	@Override
	public int calcularPontos(int pontos) {
		
		pontos = (int) (pontos*BONUS_PONTOS);
		
		return pontos;
		
	}

	@Override
	public double aplicarDisconto(double valor) {
		
		return valor*TAXA_DISCONTO;
	}

}
