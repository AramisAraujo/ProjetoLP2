package cartao;

public class CartaoFidelidade {
	
	private int pontos;
	private Fidelidade nivelFidelidade;
	
	private final int NIVEL_MASTER, NIVEL_VIP;
	
	public CartaoFidelidade(){
		
		this.pontos = 0;
		this.nivelFidelidade = new Padrao();
		
		this.NIVEL_MASTER = 150;
		this.NIVEL_VIP = 350;
		
	}
	
	public void addPontos(int pontos){
		
		pontos = this.nivelFidelidade.calcularPontos(pontos);
		
		this.pontos = this.pontos + pontos;
		
		this.checarFidelidade();
		
	}
	
	public double aplicarDisconto(double valor){
			
		valor = this.nivelFidelidade.aplicarDisconto(valor);
		
		return valor;
		
	}
	
	public int getPontos(){
		
		return this.pontos;
	}
	
	private void checarFidelidade(){
		
		if(this.pontos < NIVEL_MASTER){
			if(!this.nivelFidelidade.getClass().equals(Padrao.class)){
				this.nivelFidelidade = new Padrao();

			}
		}
		else if(this.pontos >= NIVEL_MASTER && this.pontos < NIVEL_VIP){
			
			if(!this.nivelFidelidade.getClass().equals(Master.class)){
				this.nivelFidelidade = new Master();
			}
		}
		else{
			if(!this.nivelFidelidade.getClass().equals(VIP.class)){
				this.nivelFidelidade = new VIP();
			}
		}
		
	}
	
	
	
	

}
