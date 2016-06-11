package dao;

import java.util.Vector;

import Business.CapoProgettoBusiness;
import dbconnection.DbConnection;

public class CapoProgettoDAO {
	
		
	private static CapoProgettoDAO instance;
		
	 public static CapoProgettoDAO getInstance()
		   {
		   	if (instance==null)
		   	{
	    		instance = new CapoProgettoDAO();
	    	}		    	
		   	
		   	return instance;
		   }
	
	 public Vector<String[]> TotaleSpesaProgetto(){
		 Vector <String[]> TotaleSpesa = DbConnection.getInstance().eseguiQuery("select NomeProgetto, TotaleSpesaProg from progetto");
		return TotaleSpesa;
	}  
	 
	 public Vector<String[]> TotaleSpesaDipendente(){
		 Vector<String[]> TotaleSpesa = DbConnection.getInstance().eseguiQuery("select utente_registrato.Nome, utente_registrato.cognome,"
				 																+ " Dipendente.TotaleSpesaDip from utente_registrato inner"
				 																+ " join dipendente on idUtenteRegistrato = idDipendente");
		 return TotaleSpesa;
	 }
		 
		 

}
