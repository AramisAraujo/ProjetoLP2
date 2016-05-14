package banco_de_orgaos;

import exceptions.BancoOrgaoException;
import exceptions.VerificaExcecao;
import paciente.TipoSanguineo;

/**
 * Classe utilizada para criar orgaos.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class Orgao {
	private String nome;
	private TipoSanguineo tipoSanguineo;

	public Orgao(String nome, TipoSanguineo tipoSanguineo) throws BancoOrgaoException {
		try {
			VerificaExcecao.checkEmptyString(nome, "Nome do orgao");
			verificaTipoSanguineo(tipoSanguineo);
		} catch (Exception e) {
			throw new BancoOrgaoException(e.getMessage());
		}
		
		this.nome = nome;
		this.tipoSanguineo = tipoSanguineo;
	}

	public void verificaTipoSanguineo(TipoSanguineo tipoSanguineo) throws Exception {
		if (tipoSanguineo == null) {
			throw new Exception("Tipo sanguineo invalido.");
		}
	}

	public String getNome() {
		return nome;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	/**
	 * Hashcode implementado considerando que dois orgaos se iguais se possuem o
	 * mesmo nome e o mesmo tipo sanguineo.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipoSanguineo == null) ? 0 : tipoSanguineo.hashCode());
		return result;
	}

	/**
	 * Equals implementado considerando que dois orgaos se iguais se possuem o
	 * mesmo nome e o mesmo tipo sanguineo.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Orgao) {
            Orgao outroOrgao = (Orgao) obj;
            if (outroOrgao.nome.equals(this.nome)) {
                if (outroOrgao.tipoSanguineo.equals(this.tipoSanguineo)) {
                    return true;
                }
            }
        }
        return false;
	}
}
