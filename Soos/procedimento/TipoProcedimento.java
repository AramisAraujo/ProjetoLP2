package procedimento;


/**
 * TipoProcedimento
 * Enumerator que representa os diferentes tipos de procedimentos disponiveis no Soos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 * 
 */

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
