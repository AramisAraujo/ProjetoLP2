package usuario;

public class TecnicoAdm extends Usuario{

	public TecnicoAdm(String nome, int dataNascimento, int senha, int matricula) {
		super(nome, dataNascimento, senha, matricula);
	}

	@Override
	public TipoCargo getCargo() {
		return null;
	}
}
