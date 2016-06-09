package dao;

import java.util.Vector;

import org.omg.CORBA.PRIVATE_MEMBER;

import dbconnection.DbConnection;
import model.Ordine;
import model.Progetto;
import model.Sessione;
import model.UtenteRegistrato;

public class ProgettoDAO {

	private static ProgettoDAO instance;
	
	 public static ProgettoDAO getInstance()
	    {
	    	if (instance==null)
	    	{
	    		instance = new ProgettoDAO();
	    	}
	    	return instance;
	    }
	
	 public Vector<String[]> getNomeProgetto (){
			DbConnection con = new DbConnection();
			UtenteRegistrato u = (UtenteRegistrato) Sessione.getInstance().session.get("utente_corrente");
			int idUtente = u.getIdUtenteRegistrato();
			Vector<String[]> progetto = con.eseguiQuery("select NomeProgetto from progetto where idDipendente="+idUtente);
			return progetto;
		}
   
	 public void aggionaSpesaProgetto(){
	   DbConnection con = new DbConnection();
	   Object [] info = Ordine.getInstance().spesaTotaleProgetto.get("spesa_totale_progetto");
	   String nome_progetto = (String) info[0];
	   Float spesa = (Float) info[1];
	   con.eseguiAggiornamento("update progetto set TotaleSpesaProg = TotaleSpesaProg +"+spesa+" WHERE NomeProgetto= \""+nome_progetto+"\"");
	   
   }	
}
