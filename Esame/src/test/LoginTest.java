package test;

import static org.junit.Assert.*;


import org.junit.Test;

import Business.UtenteRegistratoBusiness;

public class LoginTest {
	
	private String username = "MarcelloD";
	private String password = "marcello";
	private char ruolo = 'C';

	@Test
	public void utenteEsistetest() {
		boolean utenteEsiste = UtenteRegistratoBusiness.getInstance().verificaLogin(username, password);
		assertTrue(utenteEsiste);
		
	}
	

}
