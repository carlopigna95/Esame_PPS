package dao;

import java.util.Vector;

import Business.UtenteRegistratoBusiness;
import dbconnection.DbConnection;
import model.Sessione;
import model.UtenteRegistrato;
import view.GuiLogin;
public class UtenteRegistratoDAO {
    private static UtenteRegistratoDAO instance;
    
    public static UtenteRegistratoDAO getInstance()
    {
    	if (instance==null)
    	{
    		instance = new UtenteRegistratoDAO();
    	}
    	return instance;
    }
    
	
   public boolean utenteEsiste(UtenteRegistrato u)
   {
	  String username = u.getUsername();
	  String password = u.getPassword();
	Vector <String[]> result = DbConnection.getInstance().eseguiQuery("select * from Utente_Registrato where "
			+ "username=\""+username 
			+"\" and password=\""+password+"\"");
     if (result.isEmpty() == true)
    	 return false;
     else 
    			  u.setUsername(result.get(0)[0]);
    			  u.setNome(result.get(0)[1]);
    			  u.setCognome(result.get(0)[2]);
    			  u.setPassword(result.get(0)[3]);
    			  u.setIdUtenteRegistrato(Integer.parseInt(result.get(0)[4]));
    			  u.setRuolo((result.get(0)[5]).charAt(0));
    	
        Sessione.getInstance().session.put("utente_corrente",u);
    	 return true;
	}  
  }
 
   

  
