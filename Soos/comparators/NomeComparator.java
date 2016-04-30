package comparator;
 
import java.util.Comparator;
 
import medicamentos.Medicamento;
 
/**
 * Classe utilizada para comparar os medicamentos de acordo com o nome em ordem
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