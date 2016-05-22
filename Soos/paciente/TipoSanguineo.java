package paciente;

/**
 * Enum implementado para diferenciar os diferentes tipos sanguineos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 * 
 */
public enum TipoSanguineo {
	
	A_POS("A+"), 
	A_NEG("A-"), 
	B_POS("B+"), 
	B_NEG("B-"), 
	O_POS("O+"), 
	O_NEG("O-"), 
	AB_POS("AB+"), 
	AB_NEG("AB-"); 
	
	private String rH;
	private TipoSanguineo(String emString){
	      this.rH = emString;
	   }

	@Override
	public String toString()
	   {
	      return this.rH; 
	   }

}
