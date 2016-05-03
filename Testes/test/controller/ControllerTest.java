package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import control.Controller;
import exceptions.CadastroException;
import exceptions.ConsultaException;

public class ControllerTest {
	Controller control;

	@Before
	public void initialize(){
		
		control = new Controller();
	}
	
	@Test
	public void testinho() throws CadastroException{
		try {
			control.liberaSistema("c041ebf8", "Elton", "12/07/1995");
		} catch (Exception e) {
			e.printStackTrace();
			fail("oie");
		}
		assertEquals("22016002", control.cadastraFuncionario("Edgar Allan Poe", "Medico", "19/01/1989"));
		
	}
	
	@Test
	public void testCadastro() {
		
		try {
			control.liberaSistema("c041ebf8", "Elton", "12/07/1995");
		} catch (Exception e) {
			e.printStackTrace();
			fail("oie");
		}
		
		try {
			assertEquals("Elton", control.getInfoFuncionario("12016001", "Nome"));
		} catch (ConsultaException e) {
			fail(e.getMessage());
		}
		
		try {
			control.cadastraFuncionario("Mainara", "Medico", "24/10/1888");
		} catch (CadastroException e) {
			fail(e.getMessage());
		}
		
		try {
			assertEquals("Mainara", control.getInfoFuncionario("22016002", "Nome"));
		} catch (ConsultaException e) {
			fail(e.getMessage());
		}
		
		try {
			control.cadastraFuncionario("Gabriel", "Tecnico Administrativo", "17/02/1874");
		} catch (CadastroException e) {
			fail(e.getMessage());
	
		}
		
		try {
			assertEquals("Gabriel", control.getInfoFuncionario("32016003", "Nome"));
		} catch (ConsultaException e) {
			fail(e.getMessage());
			
		}
		
		
	}
	
	@Test
	public void otherTest(){
		try {
			control.liberaSistema("c041ebf8", "Elton", "12/07/1995");
		} catch (Exception e) {
			e.printStackTrace();
			fail("oie");
		}
		
		String matriculaPoe = "";
		try {
			matriculaPoe = control.cadastraFuncionario("Edgar Allan Poe","Medico","19/01/1989");
		} catch (CadastroException e) {
			e.printStackTrace();
		}
		System.out.println(matriculaPoe);
		try {
			assertEquals("Edgar Allan Poe", control.getInfoFuncionario(matriculaPoe,"Nome"));
			System.out.println(control.getInfoFuncionario(matriculaPoe, "Nome"));
		} catch (ConsultaException e) {
			e.printStackTrace();
		}
	}

}
