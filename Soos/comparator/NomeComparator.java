package comparator;

import java.util.Comparator;

import medicamentos.Medicamento;

public class NomeComparator implements Comparator<Medicamento> {

	@Override
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		return medicamento.getNome().compareTo(outroMedicamento.getNome());
	}

}
