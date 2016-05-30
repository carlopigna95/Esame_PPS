package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Business.OrdineBusiness;
import dao.OrdineDAO;

public class Ordine {

	  //VARIBILI
      Object codice_ordine = null;
      float spesa_totale1 = 0;
      float spesa_totale2 = 0;
	  UtenteRegistrato u = (UtenteRegistrato) Sessione.getInstance().session.get("utente_corrente");
	  int codice_dipendente = u.getIdUtenteRegistrato();
	  boolean stato_ordine;

	  public HashMap< String,Vector<Object[]>> ordine_magazzino = new HashMap<String,Vector<Object[]>>();
	  public HashMap< String,Float> spesaTotaleDipendente = new HashMap<String,Float>();
	  
	
	  //ISTANZA
	  private static Ordine instance;
	  public static synchronized Ordine getInstance() {
			if(instance == null)
				instance = new Ordine();
			return instance;
		}
	 
	  
	  
	//METODI
	public Vector<String[]> getProgetto() {
		return OrdineDAO.getInstance().getNomeProgetto();
	}
	
    public void confermaOrdine(){
    	Vector<String[]> progetti = OrdineBusiness.getInstance().getProgetto();
    	JComboBox<String> box = new JComboBox<String>();
    	for (int i=0;i<progetti.size();i++){
       	       
                  box.addItem(progetti.get(i)[0]); 
          }
        box.setEditable(false);
        String nome_progetto = (String) box.getSelectedItem();
    	String [] options ={"OK"};
    	int pannello = JOptionPane.showOptionDialog(null,box,"Selezionare il progetto",JOptionPane.NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
	    if(pannello == 0){
	    	
	    	   Set<Prodotto> keySet = Carrello.getInstance().sessionCar.keySet();
			   Iterator<Prodotto> iterator = keySet.iterator();
			   Vector<Object[]> lista_ordine1 = new Vector<Object[]>();
			   Vector<String[]> lista_ordine2 = new Vector<String[]>();
			   Vector<Integer> prodotti = new Vector<Integer>();
		float spesa_totale1 = 0;
		Object [] ordine1 = new Object[5];
        Object [] ordine2 = new String[5];
		
			   while(iterator.hasNext()){	   
			Prodotto key = iterator.next();
			int codice_prodotto = key.codiceProdotto();
			int codice_magazzino = OrdineDAO.getInstance().getCodiceMagazzino(codice_prodotto);
			int quantità = Carrello.getInstance().sessionCar.get(key);
	        float prezzo = key.getPrezzo() * quantità;
	        
	        
		    switch (codice_magazzino){
		    case 1 : 
		    	     codice_ordine = 0;
			         ordine1[0] = codice_ordine;
			         spesa_totale1 = prezzo + spesa_totale1;
			         ordine1[1] = spesa_totale1;
			         ordine1[2] = nome_progetto; 
		             ordine1[3] = codice_dipendente;
		             ordine1[4] = codice_magazzino;
		             
		             
		    	             
		    break;
		    case 2 : 
   	                 codice_ordine = 0;
	                // ordine2[0] = Integer.toString(codice_ordine);
	                 spesa_totale2 = prezzo + spesa_totale2;
	                 ordine2[1] = Float.toString(spesa_totale2);
	                 ordine2[2] = nome_progetto; 
                     ordine2[3] = Integer.toString(codice_dipendente);
                     ordine2[4] = Integer.toString(codice_magazzino);
                     
                     
   	                 
		    break;
		  
		    }
		    
		}      
			   float spesa_ordine_misto = spesa_totale1 + spesa_totale2;
			   spesaTotaleDipendente.put("spesa_totale",spesa_ordine_misto);
			   lista_ordine1.add(ordine1);
			   //lista_ordine2.add(ordine2);
			   ordine_magazzino.put("magazzino1",lista_ordine1);  
			   //ordine_magazzino.put("magazzino2",lista_ordine2);
    }
    }
   
    }
