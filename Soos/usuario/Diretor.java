package usuario;

public class Diretor extends Usuario{

	public Diretor(String nome, int dataNascimento, int senha, int matricula) {
		super(nome, dataNascimento, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return null;
	}
	
}
