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
	    
	    //inserimento prodotti
	    
	    Vector<String[]> codOrdine = con.eseguiQuery("select last_insert_id() as CodiceOrdine from ordine");
	    int codice_ordine2 = Integer.parseInt(codOrdine.get(0)[0]);
	    int codice_ordine1 = codice_ordine2 -1;
	    Vector<Integer[]> prodotti = Ordine.getInstance().prodotti.get("prodotti_ordinati");
	    for(int i=0;i<prodotti.size();i++){
	    	for(int j=0;j<2;j++){
	    		int codice_prodotto = prodotti.get(i)[j];
	    		int codice_magazzino = ProdottoDAO.getInstance().CodMagazzino(codice_prodotto);
	    		int quantita = prodotti.get(i)[j+1];
	    		switch (codice_magazzino) {
				case 1:
					boolean inserisci_prodotti1 = con.eseguiAggiornamento("insert into costituito_da ( idOrdine,idProdotto,Quantit‡Ordinata) value ("+codice_ordine1+","+codice_prodotto+","+quantita+")");
					Vector<String[]> disp = con.eseguiQuery("select DisponibilitaInMagazzino from prodottoinmagazzino where CodiceProdottoInMagazzino ="+codice_prodotto);
					int disponibilita = Integer.parseInt(disp.get(0)[0]);
					int nuova_disp = disponibilita - quantita;
					boolean aggiornaDisp = con.eseguiAggiornamento("update prodottoinmagazzino set DisponibilitaInMagazzino ="+nuova_disp+" where CodiceProdottoInMagazzino ="+codice_prodotto);
					break;

				case 2:
					boolean inserisci_prodotti2 = con.eseguiAggiornamento("insert into costituito_da ( idOrdine,idProdotto,Quantit‡Ordinata) value ("+codice_ordine2+","+codice_prodotto+","+quantita+")");
					Vector<String[]> disp1 = con.eseguiQuery("select DisponibilitaInMagazzino from prodottoinmagazzino where CodiceProdottoInMagazzino ="+codice_prodotto);
					int disponibilita1 = Integer.parseInt(disp1.get(0)[0]);
					int nuova_disp1 = disponibilita1 - quantita;
					boolean aggiornaDisp1 = con.eseguiAggiornamento("update prodottoinmagazzino set DisponibilitaInMagazzino ="+nuova_disp1+" where CodiceProdottoInMagazzino ="+codice_prodotto);
					
					break;
				}
	    		 break;
	    	}
	    }
	    
	    
	    
	    break;
    case "false-true": //magazzino 1
    	float totale_spesa01 =  (float) ordine_mag1.get(0)[0];
		String nome_progetto01 = (String) ordine_mag1.get(0)[1];    
		int codice_dipendente01 = (int) ordine_mag1.get(0)[2];
		int codice_magazzino01 = (int) ordine_mag1.get(0)[3];
	    boolean inserimento01 = con.eseguiAggiornamento("insert into ordine (CodiceOrdine,TotaleSpesa,NomeProgetto,CodiceDipendente,StatoOrdine,CodiceMagazzino) values ("+null+","+totale_spesa01+",'"+nome_progetto01+"',"+codice_dipendente01+",0,"+codice_magazzino01+")");	
	    
	    Vector<String[]> codOrdine1 = con.eseguiQuery("select last_insert_id() as CodiceOrdine from ordine");
	    int codice_ordine11 = Integer.parseInt(codOrdine1.get(0)[0]);
	    Vector<Integer[]> prodotti_magazzino1 = Ordine.getInstance().prodotti.get("prodotti_ordinati");
	    prodotti_magazzino1.size();
	    for(int i=0;i<prodotti_magazzino1.size();i++){
	    	for(int j=0;j<2;j++){
	    		int codice_prodotto = prodotti_magazzino1.get(i)[j];
	    		int quantita = prodotti_magazzino1.get(i)[j+1];
	    		boolean inserisci_prodotti1 = con.eseguiAggiornamento("insert into costituito_da ( idOrdine,idProdotto,Quantit‡Ordinata) value ("+codice_ordine11+","+codice_prodotto+","+quantita+")");
	    		Vector<String[]> disp = con.eseguiQuery("select DisponibilitaInMagazzino from prodottoinmagazzino where CodiceProdottoInMagazzino ="+codice_prodotto);
				int disponibilita = Integer.parseInt(disp.get(0)[0]);
				int nuova_disp = disponibilita - quantita;
				boolean aggiornaDisp = con.eseguiAggiornamento("update prodottoinmagazzino set DisponibilitaInMagazzino ="+nuova_disp+" where CodiceProdottoInMagazzino ="+codice_prodotto);
				
	    		break;
	    	   }
	    	}
	    break;
    case "true-false": //magazzino 2
        
    	float totale_spesa02 =  (float) ordine_mag2.get(0)[0];
		String nome_progetto02 = (String) ordine_mag2.get(0)[1];    
		int codice_dipendente02 = (int) ordine_mag2.get(0)[2];
		int codice_magazzino02 = (int) ordine_mag2.get(0)[3];
	    boolean inserimento02 = con.eseguiAggiornamento("insert into ordine (CodiceOrdine,TotaleSpesa,NomeProgetto,CodiceDipendente,StatoOrdine,CodiceMagazzino) values ("+null+","+totale_spesa02+",'"+nome_progetto02+"',"+codice_dipendente02+",0,"+codice_magazzino02+")");	
	    
	    
	    Vector<String[]> codOrdine2 = con.eseguiQuery("select last_insert_id() as CodiceOrdine from ordine");
	    int codice_ordine12 = Integer.parseInt(codOrdine2.get(0)[0]);
	    Vector<Integer[]> prodotti_magazzino2 = Ordine.getInstance().prodotti.get("prodotti_ordinati");
	    for(int i=0;i<prodotti_magazzino2.size();i++){
	    	for(int j=0;j<2;j++){
	    		int codice_prodotto = prodotti_magazzino2.get(i)[j];
	    		int quantita = prodotti_magazzino2.get(i)[j+1];
	    		boolean inserisci_prodotti1 = con.eseguiAggiornamento("insert into costituito_da ( idOrdine,idProdotto,Quantit‡Ordinata) value ("+codice_ordine12+","+codice_prodotto+","+quantita+")");
	    		Vector<String[]> disp = con.eseguiQuery("select DisponibilitaInMagazzino from prodottoinmagazzino where CodiceProdottoInMagazzino ="+codice_prodotto);
				int disponibilita = Integer.parseInt(disp.get(0)[0]);
				int nuova_disp = disponibilita - quantita;
				boolean aggiornaDisp = con.eseguiAggiornamento("update prodottoinmagazzino set DisponibilitaInMagazzino ="+nuova_disp+" where CodiceProdottoInMagazzino ="+codice_prodotto);
	    		break;
	    	   }
	    	}
	    
	    
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
