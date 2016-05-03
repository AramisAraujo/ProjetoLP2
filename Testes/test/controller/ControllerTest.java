package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import control.Facade;
import exceptions.CadastroException;
import exceptions.ConsultaException;

public class ControllerTest {
	Facade facade;

	@Before
	public void initialize(){
		
		facade = new Facade();
	}
	
	@Test
	public void testCadastro() {
		
		try {
			facade.liberaSistema("c041ebf8", "Elton", "12/07/1995");
		} catch (Exception e) {
			e.printStackTrace();
			fail("oie");
		}
		
		try {
			assertEquals("Elton", facade.getInfoFuncionario("12016001", "Nome"));
		} catch (ConsultaException e) {
			fail(e.getMessage());
		}
		
		try {
			facade.cadastraFuncionario("Mainara", "Medico", "24/10/1888");
		} catch (CadastroException e) {
			fail(e.getMessage());
		}
		
		try {
			assertEquals("Mainara", facade.getInfoFuncionario("22016002", "Nome"));
		} catch (ConsultaException e) {
			fail(e.getMessage());
		}
		
		try {
			facade.cadastraFuncionario("Gabirel", "Tecnico Administrativo", "17/02/1874");
		} catch (CadastroException e) {
			fail(e.getMessage());
	
		}
		
		try {
			assertEquals("Gabriel", facade.getInfoFuncionario("32016003", "Nome"));
		} catch (ConsultaException e) {
			fail(e.getMessage());
			
		}
		
		
	}

}
