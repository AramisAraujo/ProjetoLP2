package usuario;

/**
 * Enum implementado para diferenciar os diferentes tipos de cargos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 * 
 */
public enum TipoCargo {
	DIRETOR("Diretor Geral"), 
	MEDICO("Medico"), 
	TECNICOADM("Tecnico Administrativo");
	
	private String cargo;
	
	private TipoCargo(String emString){
	      this.cargo = emString;
	   }

	@Override
	public String toString()
	   {
	      return this.cargo; 
	   }
}
