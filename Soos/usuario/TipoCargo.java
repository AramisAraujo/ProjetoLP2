package usuario;

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
