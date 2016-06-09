package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Business.OrdineBusiness;
import dao.OrdineDAO;
import dao.ProgettoDAO;

public class Ordine {

	  //VARIBILI
      float spesa_totale1 = 0;
      float spesa_totale2 = 0;
	  UtenteRegistrato u = (UtenteRegistrato) Sessione.getInstance().session.get("utente_corrente");
	  int codice_dipendente = u.getIdUtenteRegistrato();
	  boolean stato_ordine;
	  private int codOrdine;

	  public HashMap< String,Vector<Object[]>> ordine_magazzino = new HashMap<String,Vector<Object[]>>();
	  public HashMap< String,Float> spesaTotaleDipendente = new HashMap<String,Float>();
	  public HashMap< String,Object[]> spesaTotaleProgetto = new HashMap<String,Object[]>();
	  public HashMap<String,Object> sessionOrdine = new HashMap<String, Object>();
	  public HashMap<String,Vector<Integer[]>> prodotti = new HashMap<String,Vector<Integer[]>>();
	  
	  
	  
	  //ISTANZA
	  private static Ordine instance;
	  public static synchronized Ordine getInstance() {
			if(instance == null)
				instance = new Ordine();
			return instance;
		}
	 
	  
	  
	//METODI
	public Vector<String[]> getProgetto() {
		return ProgettoDAO.getInstance().getNomeProgetto();
	}
	
    public void confermaOrdine(){
    	if (Carrello.getInstance().sessionCar.isEmpty()){
    		JOptionPane.showMessageDialog(null, "Non ci sono prodotti nel carrello");
    	}else{
            Object [] spesa_progetto = new Object[2];
     		Vector<String[]> progetti = OrdineBusiness.getInstance().getProgetto();
        	JComboBox<String> box = new JComboBox<String>();
        	for (int i=0;i<progetti.size();i++){
           	       
                      box.addItem(progetti.get(i)[0]); 
              }
            box.setEditable(false);
            String nome_progetto = (String) box.getSelectedItem();
            spesa_progetto[0] = nome_progetto;
        	String [] options ={"OK"};
        	int pannello = JOptionPane.showOptionDialog(null,box,"Selezionare il progetto",JOptionPane.NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
    	    if(pannello == 0){
    	    	
    	    	   Set<Prodotto> keySet = Carrello.getInstance().sessionCar.keySet();
    			   Iterator<Prodotto> iterator = keySet.iterator();
    			   Vector<Object[]> lista_ordine1 = new Vector<Object[]>();
    			   Vector<Object[]> lista_ordine2 = new Vector<Object[]>();
    			   Vector<Prodotto> lista_prodotti = new Vector<Prodotto>(); 
    			  
    		       float spesa_totale1 = 0;
    		       Object [] ordine1 = new Object[4];
                   Object [] ordine2 = new Object[4];
    		
    			   while(iterator.hasNext()){		   
    			Prodotto key = iterator.next();
    			lista_prodotti.addElement(key);
    			int codice_prodotto = key.codiceProdotto();
    			int codice_magazzino = OrdineDAO.getInstance().getCodiceMagazzino(codice_prodotto);
    			int quantità = Carrello.getInstance().sessionCar.get(key);
    	        float prezzo = key.getPrezzo() * quantità;
    	        
    	        
    		    switch (codice_magazzino){
    		    case 1 : 
    		    	 
    			         spesa_totale1 = prezzo + spesa_totale1;
    			         ordine1[0] = spesa_totale1;
    			         ordine1[1] = nome_progetto; 
    		             ordine1[2] = codice_dipendente;
    		             ordine1[3] = codice_magazzino;
    		                     
    		    break;
    		    case 2 : 
    	                 spesa_totale2 = prezzo + spesa_totale2;
    	                 ordine2[0] = spesa_totale2;
    	                 ordine2[1] = nome_progetto; 
                         ordine2[2] = codice_dipendente;
                         ordine2[3] = codice_magazzino;
                        
    		    break;
    		  
    		    }
    		    
    		}      
    			   float spesa_ordine_misto = spesa_totale1 + spesa_totale2;
    			   spesa_progetto[1] = spesa_ordine_misto;
    			   spesaTotaleProgetto.put("spesa_totale_progetto", spesa_progetto);
    			   spesaTotaleDipendente.put("spesa_totale_dipendente",spesa_ordine_misto);
    			   lista_ordine1.add(ordine1);
    			   lista_ordine2.add(ordine2);
    			   ordine_magazzino.put("magazzino1",lista_ordine1);  
    			   ordine_magazzino.put("magazzino2",lista_ordine2);
    	    }
    	}
    	
    }
    public int getCodiceMagazzino(int codice_prodotto){
    	return OrdineDAO.getInstance().getCodiceMagazzino(codice_prodotto);
    }
    public boolean inserisciOrdine(){
    	return OrdineDAO.getInstance().inserisciOrdine();
    }
    public void setStato_Ordine(boolean stato_ordine) {
		this.stato_ordine = stato_ordine;
	}

	public int getCodOrdine() {
		return codOrdine;
	}

	public void setCodOrdine(int codOrdine) {
		this.codOrdine = codOrdine;
	}
    
   
    
}
			   
			   
			   
	   
    

   
