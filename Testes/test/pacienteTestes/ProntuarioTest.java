package pacienteTestes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import paciente.Prontuario;
import paciente.TipoSanguineo;

public class ProntuarioTest {
	
	private LocalDate dataNascimento;
	private DateTimeFormatter formatador;
	private Prontuario prontuario;
	private TipoSanguineo sangue;
	private UUID id;
	
	@Before
	public void inicializa() throws Exception {
		this.formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataNascimento = LocalDate.parse("31/12/2000",formatador);
		this.sangue = TipoSanguineo.O_NEG;
		this.id = UUID.randomUUID();
		this.prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
	}
	
	@Test
	public void testProntuario() {
		
		try {
			UUID id = UUID.randomUUID();
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			assertTrue(prontuario.getQntProcedimentos() == 0);
		} catch (Exception e) {
			fail();
		}
		
		try {
			prontuario = new Prontuario("", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario(" ", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario(null, dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Nome do paciente nao pode ser vazio.", e.getMessage());
		}
		
		try {
			LocalDate data = LocalDate.parse("31/12/2050",formatador);
			prontuario = new Prontuario("Elton", data, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Data invalida.", e.getMessage());
		}
		
		try {
			LocalDate data = null;
			prontuario = new Prontuario("Elton", data, 60.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Data nao pode ser vazio.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, -100.0, "masculino", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Peso do paciente nao pode ser negativo.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, " ", "masculino", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, null, "masculino",sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Sexo biologico nao pode ser vazio.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Genero nao pode ser vazio.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", " ", sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Genero nao pode ser vazio.", e.getMessage());
		}

		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", null, sangue, id);
			fail();
		} catch (Exception e) {
			assertEquals("Genero nao pode ser vazio.", e.getMessage());
		}
		
		try {
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", null, id);
			fail();
		} catch (Exception e) {
			assertEquals("Tipo sanguineo nao pode ser vazio.", e.getMessage());
		}
		
		try {
			dataNascimento = LocalDate.parse("31/12/2000",formatador);
			prontuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", sangue, null);
			fail();
		} catch (Exception e) {
			assertEquals("ID nao pode ser vazio.", e.getMessage());
		}
	}
	
	@Test
	public void testGetInfoPaciente() {
		try {
			assertEquals("Elton", prontuario.getInfoPaciente("Nome"));
			assertEquals("2000-12-31", prontuario.getInfoPaciente("Data"));
			assertEquals("60.0", prontuario.getInfoPaciente("Peso"));
			assertEquals("masculino", prontuario.getInfoPaciente("Sexo"));
			assertEquals("masculino", prontuario.getInfoPaciente("Genero"));
			assertEquals("15", prontuario.getInfoPaciente("Idade"));
		} catch (Exception e) {
			fail();
		}
		
		try {
			String nome = prontuario.getInfoPaciente("");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
		
		try {
			String nome = prontuario.getInfoPaciente(" ");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
		
		try {
			String nome = prontuario.getInfoPaciente(null);
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}

		try {
			String nome = prontuario.getInfoPaciente("Endereco");
			fail();
		} catch (Exception e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
	}
	
	@Test
	public void testCompareTo() {
		try {
			UUID outroID = UUID.randomUUID();
			Prontuario outroProntuario = new Prontuario("Elton", dataNascimento, 60.0, "masculino", "masculino", sangue, outroID);
			assertTrue(prontuario.compareTo(outroProntuario) == 0);
		} catch (Exception e) {
			fail();
		}
		try {
			UUID outroID = UUID.randomUUID();
			Prontuario outroProntuario = new Prontuario("Helton", dataNascimento, 60.0, "masculino", "masculino", sangue, outroID);
			assertTrue(prontuario.compareTo(outroProntuario) < 0);
		} catch (Exception e) {
			fail();
		}
		
		try {
			UUID outroID = UUID.randomUUID();
			Prontuario outroProntuario = new Prontuario("Eltn", dataNascimento, 60.0, "masculino", "masculino", sangue, outroID);
			assertTrue(prontuario.compareTo(outroProntuario) > 0);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testEquals() {
		try {
			Prontuario outroProntuario = new Prontuario("Helton", dataNascimento, 60.0, "masculino", "masculino", sangue, id);
			assertTrue(prontuario.equals(outroProntuario));
		} catch (Exception e) {
			fail();
		}
		
		try {
			UUID outroID = UUID.randomUUID();
			Prontuario outroProntuario = new Prontuario("Helton", dataNascimento, 60.0, "masculino", "masculino", sangue, outroID);
			assertFalse(prontuario.equals(outroProntuario));
		} catch (Exception e) {
			fail();
		}
	}
}
