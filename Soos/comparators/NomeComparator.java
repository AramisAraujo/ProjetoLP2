package comparators;
 
import java.util.Comparator;
 
import farmacia.Medicamento;
 
/**
 * Classe que define um Comparator qual eh capaz de comparar dois medicamentos por ordem
 * alfabetica.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class NomeComparator implements Comparator<Medicamento> {
 
    @Override
    public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
        return medicamento.getNome().compareTo(outroMedicamento.getNome());
    }
 
}