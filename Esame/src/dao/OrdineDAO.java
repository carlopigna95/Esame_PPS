package dao;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import dbconnection.DbConnection;
import model.Carrello;
import model.Ordine;
import model.Prodotto;
import model.Sessione;
import model.UtenteRegistrato;

public class OrdineDAO {
	private static OrdineDAO instance;
	
	 public static OrdineDAO getInstance()
	    {
	    	if (instance==null)
	    	{
	    		instance = new OrdineDAO();
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
public int getCodiceMagazzino(int codice_prodotto){
	  
	       DbConnection con = new DbConnection();
		   Vector<String[]> codice = con.eseguiQuery("select prodottoinmagazzino.idMagazzino from prodottoinmagazzino inner join prodotto on prodotto.CodiceProdotto = CodiceProdottoInMagazzino where prodotto.CodiceProdotto="+codice_prodotto);
		   int codice_magazzino = Integer.parseInt(codice.get(0)[0]);
		   return codice_magazzino;
			 }
public void inserisciOrdine(){
	Vector<Object[]> ordine_mag1 = Ordine.getInstance().ordine_magazzino.get("magazzino1");
	//Vector<String[]> ordine_mag2 = Ordine.getInstance().ordine_magazzino.get("magazzino2");
	int status = 0;
	Object codice_ordine = (int) ordine_mag1.get(0)[0];
	float totale_spesa =  (float) ordine_mag1.get(0)[1];
	String nome_progetto = (String) ordine_mag1.get(0)[2];
	int codice_dipendente = (int) ordine_mag1.get(0)[3];
	int codice_magazzino = (int) ordine_mag1.get(0)[4];
	System.out.println("Ordine"+codice_ordine+"\tTotale:"+totale_spesa+"\tNome:"+nome_progetto+"\tDipen:"+codice_dipendente);
    DbConnection con = new DbConnection();
    String query = "insert into ordine (CodiceOrdine,TotaleSpesa,NomeProgetto,CodiceDipendente,StatoOrdine) "
    		+ "values (CodiceOrdine="+codice_ordine+",TotaleSpesa="+totale_spesa+
    		",NomeProgetto= \""+nome_progetto+"\",CodiceDipendente="+codice_dipendente+
    		",StatoOrdine="+status+")";
    boolean inserimento = con.eseguiAggiornamento(query);
	System.out.println(inserimento);
   }
}
