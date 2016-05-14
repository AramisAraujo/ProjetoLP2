package procedimento;

public enum TipoProcedimento {
	
	CIRURGIABARIATRICA("Cirurgia bariatrica"),
	CONSULTACLINICA("Consulta clinica"),
	REDESIGNACAOSEXUAL("Redesignacao sexual"),
	TRANSPLANTEDEORGAOS("Transplante de orgaos");
	
	
	private String title;
	
	private TipoProcedimento(String title) {
		this.title = title;
	}

	public String toString(){
		return this.title;
	}
	
}
