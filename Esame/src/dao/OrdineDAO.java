package dao;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

public int getCodiceMagazzino(int codice_prodotto){
	  
	       DbConnection con = new DbConnection();
		   Vector<String[]> codice = con.eseguiQuery("select prodottoinmagazzino.idMagazzino from prodottoinmagazzino inner join prodotto on prodotto.CodiceProdotto = CodiceProdottoInMagazzino where prodotto.CodiceProdotto="+codice_prodotto);
		   int codice_magazzino = Integer.parseInt(codice.get(0)[0]);
		   return codice_magazzino;
			 }
public boolean inserisciOrdine(){
	DbConnection con = new DbConnection();
	Vector<Object[]> ordine_mag1 = Ordine.getInstance().ordine_magazzino.get("magazzino1");
	Vector<Object[]> ordine_mag2 = Ordine.getInstance().ordine_magazzino.get("magazzino2");
	
	boolean ris1 = verificaNullVector(ordine_mag1);
	boolean ris2 = verificaNullVector(ordine_mag2);
	
	switch (ris1 + "-" + ris2) {
    case "false-false":
    	float totale_spesa1 =  (float) ordine_mag1.get(0)[0];
		String nome_progetto1 = (String) ordine_mag1.get(0)[1];    
		int codice_dipendente1 = (int) ordine_mag1.get(0)[2];
		int codice_magazzino1 = (int) ordine_mag1.get(0)[3];
	    boolean inserimento1 = con.eseguiAggiornamento("insert into ordine (CodiceOrdine,TotaleSpesa,NomeProgetto,CodiceDipendente,StatoOrdine,CodiceMagazzino) values ("+null+","+totale_spesa1+",'"+nome_progetto1+"',"+codice_dipendente1+",0,"+codice_magazzino1+")");	
        
	    
	    float totale_spesa2 =  (float) ordine_mag2.get(0)[0];
		String nome_progetto2 = (String) ordine_mag2.get(0)[1];    
		int codice_dipendente2 = (int) ordine_mag2.get(0)[2];
		int codice_magazzino2 = (int) ordine_mag2.get(0)[3];
	    boolean inserimento2 = con.eseguiAggiornamento("insert into ordine (CodiceOrdine,TotaleSpesa,NomeProgetto,CodiceDipendente,StatoOrdine,CodiceMagazzino) values ("+null+","+totale_spesa2+",'"+nome_progetto2+"',"+codice_dipendente2+",0,"+codice_magazzino2+")");	
	    break;
    case "false-true":
    	float totale_spesa01 =  (float) ordine_mag1.get(0)[0];
		String nome_progetto01 = (String) ordine_mag1.get(0)[1];    
		int codice_dipendente01 = (int) ordine_mag1.get(0)[2];
		int codice_magazzino01 = (int) ordine_mag1.get(0)[3];
	    boolean inserimento01 = con.eseguiAggiornamento("insert into ordine (CodiceOrdine,TotaleSpesa,NomeProgetto,CodiceDipendente,StatoOrdine,CodiceMagazzino) values ("+null+","+totale_spesa01+",'"+nome_progetto01+"',"+codice_dipendente01+",0,"+codice_magazzino01+")");	
	    break;
    case "true-false":
        
    	float totale_spesa02 =  (float) ordine_mag2.get(0)[0];
		String nome_progetto02 = (String) ordine_mag2.get(0)[1];    
		int codice_dipendente02 = (int) ordine_mag2.get(0)[2];
		int codice_magazzino02 = (int) ordine_mag2.get(0)[3];
	    boolean inserimento02 = con.eseguiAggiornamento("insert into ordine (CodiceOrdine,TotaleSpesa,NomeProgetto,CodiceDipendente,StatoOrdine,CodiceMagazzino) values ("+null+","+totale_spesa02+",'"+nome_progetto02+"',"+codice_dipendente02+",0,"+codice_magazzino02+")");	
	    break;
    case "true-true":
    	  JOptionPane.showMessageDialog(null, "Non ci sono ordini da confermare");
    	  break;
	}
	return true;
   }
  public boolean verificaNullVector(Vector<Object[]> vec){
	  
	  for(int i=0;i<vec.size();i++){
			 for (int j=0;j<4;j++){
				 if (vec.get(i)[j] == null) 
					 {
					 continue;
					   }
					   else return false;	   
			 }
			 
		 }
	  return true;
  }
}
