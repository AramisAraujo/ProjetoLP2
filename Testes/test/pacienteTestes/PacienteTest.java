package pacienteTestes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import paciente.Paciente;
import paciente.TipoSanguineo;

public class PacienteTest {
	
	private Paciente paciente;
	private LocalDate dataNascimento;
	private DateTimeFormatter formatador;
	private TipoSanguineo sangue;
	private UUID id;

	@Before
	public void inicializa() throws Exception {
		this.formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataNascimento = LocalDate.parse("31/12/2000",formatador);
		this.sangue = TipoSanguineo.O_NEG;
		this.id = UUID.randomUUID();
		this.paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
	}
	
	@Test
	public void testPaciente() {
		
		try {			
			assertEquals("Elton", paciente.getNome());
			assertEquals("2000-12-31", paciente.getDataNascimento());
			assertTrue(paciente.getPeso() == 60.0);
			assertEquals("masculino", paciente.getSexoBiologico());
			assertEquals("masculino", paciente.getGenero());
			assertEquals("O-", paciente.getTipoSanguineo());
			
		} catch (Exception e) {
			fail();
		}
		
		try {
			paciente = new Paciente("", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			System.out.println(paciente.getNome());
			fail();
		} catch (Exception e) {
			assertEquals("Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			paciente = new Paciente(" ", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			paciente = new Paciente(null, dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			LocalDate data = LocalDate.parse("31/12/2050",formatador);
			paciente = new Paciente("Elton", data, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Data invalida.", e.getMessage());
		}
		
		try {
			LocalDate data = null;
			paciente = new Paciente("Elton", data, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Data nao pode ser vazio.", e.getMessage());
		}
		
		try {
			paciente = new Paciente("Elton", dataNascimento, -100.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Peso do paciente nao pode ser negativo.", e.getMessage());
		}
		
		try {
			paciente = new Paciente("Elton", dataNascimento, 60.0, "", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			paciente = new Paciente("Elton", dataNascimento, 60.0, " ", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			paciente = new Paciente("Elton", dataNascimento, 60.0, null, "masculino",sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Genero nao pode ser vazio.", e.getMessage());
		}
		
		try {
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", " ", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Genero nao pode ser vazio.", e.getMessage());
		}

		try {
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", null, sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Genero nao pode ser vazio.", e.getMessage());
		}
		
		try {
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", null, id);
			fail();
		} catch (Exception e) {
			assertEquals("Tipo sanguineo nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			paciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", sangue, null);
			fail();
		} catch (Exception e) {
			assertEquals("ID nao pode ser vazio.", e.getMessage());
		}
	}
	
	
	@Test
	public void testEquals() {
		try {
			Paciente outroPaciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			assertTrue(paciente.equals(outroPaciente));
		} catch(Exception e) {
			fail();
		}
		
		try {
			UUID outroId = UUID.randomUUID();
			Paciente outroPaciente = new Paciente("Elton", dataNascimento, 60.0, "masculino", "masculino", sangue, outroId);
			assertFalse(paciente.equals(outroPaciente));
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testCompareTo() {
		try {
			UUID outroId = UUID.randomUUID();
			Paciente outroPaciente = new Paciente("Elton", dataNascimento, 58.0, "masculino", "feminino", sangue, outroId);
			assertTrue(paciente.compareTo(outroPaciente) == 0);
		} catch(Exception e) {
			fail();
		}
		
		try {
			UUID outroId = UUID.randomUUID();
			Paciente outroPaciente = new Paciente("Helton", dataNascimento, 58.0, "masculino", "feminino", sangue, outroId);
			assertTrue(paciente.compareTo(outroPaciente) < 0);
		} catch(Exception e) {
			fail();
		}
		
		try {
			UUID outroId = UUID.randomUUID();
			Paciente outroPaciente = new Paciente("Eltn", dataNascimento, 58.0, "masculino", "feminino", sangue, outroId);
			assertTrue(paciente.compareTo(outroPaciente) > 0);
		} catch(Exception e) {
			fail();
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testGetInfoPaciente() {
		try {
			assertEquals("Elton", paciente.getInfoPaciente("Nome"));
			assertEquals("2000-12-31", paciente.getInfoPaciente("Data"));
			assertEquals("60.0", paciente.getInfoPaciente("Peso"));
			assertEquals("masculino", paciente.getInfoPaciente("Sexo"));
			assertEquals("masculino", paciente.getInfoPaciente("Genero"));
			assertEquals("15", paciente.getInfoPaciente("Idade"));
		} catch (Exception e) {
			fail();
		}
		
		try {
			String nomeInvalido = paciente.getInfoPaciente("");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
		
		try {
			String nomeInvalido = paciente.getInfoPaciente(" ");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
		
		try {
			String nomeInvalido = paciente.getInfoPaciente(null);
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
		
		try {
			String nomeInvalido = paciente.getInfoPaciente("Endereco");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
	}
}
