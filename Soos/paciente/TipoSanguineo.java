package paciente;

/**
 * 
 * @author Elton Dantas
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
