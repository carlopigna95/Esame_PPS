package dao;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import dbconnection.DbConnection;
import model.Dipendente;
import model.Ordine;
import model.Prodotto;
import model.Progetto;
import model.Sessione;
import model.UtenteRegistrato;

public class DipendenteDAO {
	
	private static DipendenteDAO instance;
	
	 public static DipendenteDAO getInstance()
	    {
	    	if (instance==null)
	    	{
	    		instance = new DipendenteDAO();
	    	}
	    	return instance;
	    }
	 
	 public Vector<String[]> GeneraCatalogo1(){  
		 
		 DbConnection con = DbConnection.getInstance();
		
		 //Seleziona i prodotti dal magazzino 1
		 Vector<String[]> prodotto1 = con.eseguiQuery("select Prodotto.Nome, Prodotto.Categoria, "
		 		+ "Prodotto.Descrizione, ProdottoInMagazzino.DisponibilitaInMagazzino,"
		 		+ " Prodotto.Prezzo, Prodotto.Produttore from Prodotto "
		 		+ "inner join ProdottoInMagazzino on Prodotto.CodiceProdotto = "
		 		+ "ProdottoInMagazzino.CodiceProdottoInMagazzino where idMagazzino = \""+1+"\"");  
		     
		 return prodotto1;	 
		 }	 
	 
	 public Vector<String[]> Disponibilita(){
		 DbConnection con = DbConnection.getInstance();
		 Vector<String[]> disponibilita= con.eseguiQuery("select DisponibilitaInMagazzino"
		 		+ " from prodottoinmagazzino where idMagazzino = "+1);
		 
				return disponibilita;
	 }

	public Vector<String[]> GeneraCatalogo2(){  
	 
		DbConnection con = DbConnection.getInstance();
	
		//Seleziona i prodotti dal magazzino 2
		Vector<String[]> prodotto2 = con.eseguiQuery("select Prodotto.Nome, Prodotto.Categoria, "
	 		+ "Prodotto.Descrizione, ProdottoInMagazzino.DisponibilitaInMagazzino,"
	 		+ " Prodotto.Prezzo, Prodotto.Produttore from Prodotto "
	 		+ "inner join ProdottoInMagazzino on Prodotto.CodiceProdotto = "
	 		+ "ProdottoInMagazzino.CodiceProdottoInMagazzino where idMagazzino = \""+2+"\"");
	 
		return prodotto2;
		 
	 } 
	public void aggiornaSpesa(){
		
		float spesa = Ordine.getInstance().spesaTotaleDipendente.get("spesa_totale_dipendente");
		UtenteRegistrato u = Sessione.getInstance().session.get("utente_corrente");
		DbConnection con = new DbConnection();
		int id = u.getIdUtenteRegistrato();
		con.eseguiAggiornamento("update dipendente set TotaleSpesaDip = TotaleSpesaDip +"+spesa+" WHERE idDipendente="+id);
	}
	
}

