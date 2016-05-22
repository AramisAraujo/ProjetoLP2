package farmacia;
  
/**
 * CategoriasDeMedicamentos
 * Enumerator que representa as categorias de medicamentos disponiveis no Soos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 * 
 */

public enum CategoriasDeMedicamentos {
    ANALGESICO ("analgesico"), 
    ANTIBIOTICO ("antibiotico"), 
    ANTIEMETICO ("antiemetico"), 
    ANTIINFLAMATORIO ("antiinflamatorio"),
    ANTITERMICO ("antitermico"),
    HORMONAL ("hormonal");
     
    private String tipo;
    private CategoriasDeMedicamentos(String emString){
          this.tipo = emString;
       }
 
    public String getTipo(){
    	return this.tipo;
    }
    
    @Override
    public String toString()
       {
          return this.tipo; 
       }
}