package Business;

import model.UtenteRegistrato;

public class UtenteRegistratoBusiness {
  
	private static UtenteRegistratoBusiness instance;
	public static UtenteRegistratoBusiness getInstance()
	{
		if(instance == null)
			instance = new UtenteRegistratoBusiness();
		return instance;
	}
	public boolean verificaLogin(String username, String password) 
	{
		UtenteRegistrato u = new UtenteRegistrato(username, password);
		return u.login();
	}
	
	/*public char verificaRuolo(String username, String password){
		UtenteRegistrato u = new UtenteRegistrato(username, password);
		return u.Controllo_Ruolo();
	}
	*/
	public Object sessione(String username, String password,int idUtente){
		UtenteRegistrato u = new UtenteRegistrato(username,password,idUtente);
		return u;
	}/*
	public int get_IdUtente(String username){
		  UtenteRegistrato u = new UtenteRegistrato(username);
		  return u.get_IdUtente(username);
		 }
*/
}
