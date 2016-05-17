package factories;
 
import java.time.LocalDate;
 
import usuario.Diretor;
import usuario.Medico;
import usuario.TecnicoAdm;
import usuario.TipoCargo;
import usuario.Usuario;
 
/**
 * Classe responsavel por criar todos os usuarios.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */
public class FactoryUsuario {
 
    /**
     * Metodo utilizado para criar usuarios.
     * 
     * @param nome
     *            - nome do usuario que sera criado
     * @param birthDate
     *            - data de nascimento do usuario que sera criado
     * @param senha
     *            - senha do usuario que sera criado
     * @param matricula
     *            - matricula do usuario que sera criado
     * @param cargo
     *            - cargo do usuario que sera criado
     * @return - o usuario que foi criado ou null caso o cargo seja diferente se
     *         Diretor, Medico ou Tecnico administrativo
     */
    public Usuario criarUsuario(String nome, LocalDate birthDate, String senha, String matricula, TipoCargo cargo) {
        switch (cargo) {
        case DIRETOR:
            return new Diretor(nome, birthDate, senha, matricula);
        case MEDICO:
            return new Medico(nome, birthDate, senha, matricula);
        case TECNICOADM:
            return new TecnicoAdm(nome, birthDate, senha, matricula);
        default:
            return null;
        }
    }
}