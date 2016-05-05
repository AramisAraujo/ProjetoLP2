package farmacia;
  
/**
 * Enum implementado para diferenciar as diferentes categorias de medicamentos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 * 
 */
public enum CategoriasDeMedicamentos {//Favor manter ordem alfabetica
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
 
    @Override
    public String toString()
       {
          return this.tipo; 
       }
}