package usuario;

public class Medico extends Usuario{

	public Medico(String nome, int dataNascimento, int senha, int matricula) {
		super(nome, dataNascimento, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return null;
	}

}
