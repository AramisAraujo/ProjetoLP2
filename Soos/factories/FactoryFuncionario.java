package factories;
 
import java.io.Serializable;
import java.time.LocalDate;
import funcionario.TipoCargo;
import funcionario.Funcionario;
 
/**
 * FactoryFuncionario
 * Classe responsavel por criar objetos do tipo funcionario.
 * 
 * @author Aramis Sales Araujo
 * @author Elton Dantas de Oliveira Mesquita
 * @author Gabriel de Araujo Coutinho
 * @author Mainara Cavalcanti de Farias
 */

public class FactoryFuncionario implements Serializable{
 
    /**
     * CriarFuncionario
     * Metodo responsavel pela criacao de funcionarios.
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
	
    public Funcionario criarFuncionario(String nome, LocalDate birthDate, String senha, String matricula, TipoCargo cargo) {
        
    	return new Funcionario(nome, birthDate, senha, matricula,cargo);
    
        }

}