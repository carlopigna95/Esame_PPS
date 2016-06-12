package model;


import java.util.HashMap;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Carrello {

	private int Codice_Ordine;
	private Prodotto Prodotti_Acquistati;
	private float Totale_Spesa;
	private int Codice_Progetto;
	
	
	private static Carrello instance;
	public HashMap<Prodotto , Integer> sessionCar = new HashMap<Prodotto, Integer>();
	private JTable table;
	
	
	public static synchronized Carrello getInstance() {
		if(instance == null)
			instance = new Carrello();
		return instance;
	}
	
	//GETTER & SETTER
	public int getCodice_Ordine() {
		return Codice_Ordine;
	}
	public void setCodice_Ordine(int codice_Ordine) {
		Codice_Ordine = codice_Ordine;
	}
	public float getTotale_Spesa() {
		return Totale_Spesa;
	}
	public void setTotale_Spesa(float totale_Spesa) {
		Totale_Spesa = totale_Spesa;
	}

	public int getCodice_Progetto() {
		return Codice_Progetto;
	}

	public void setCodice_Progetto(int codice_Progetto) {
		Codice_Progetto = codice_Progetto;
	}

	public Prodotto getProdotti_Acquistati() {
		return Prodotti_Acquistati;
	}

	public void setProdotti_Acquistati(Prodotto prodotti_Acquistati) {
		Prodotti_Acquistati = prodotti_Acquistati;
	}
	public void aggiungiProdotto(JTable table){
		
		 //LEGGE IL PRODOTTO
		
		
	    try{
	         
	    	 int row = table.getSelectedRow();
		     Object[] cella =  new Object[table.getColumnCount()];
			 for (int col=0;col<table.getColumnCount();col++){
				 cella [col] = table.getValueAt(row, col);
			 } 
		      
			 //INTERAZIONE CON LA CLASSE PRODOTTO
			 Prodotto p = new Prodotto();
			 
			     p.setNome_Prodotto((String) cella [0]);
				 p.setCategoria((String) cella [1]);
				 p.setDescrizione((String) cella [2]);
				 p.setDisponibilita_Magazzino(Integer.parseInt((String) cella [3]));
				 p.setPrezzo(Float.parseFloat((String) cella [4]));
				 p.setProduttore((String) cella [5]);
				
			 //LEGGE LA QUANTITA AGGIUNTA AL CARRELLO
			  Integer disponibilita = Integer.parseInt( table.getValueAt(row, 3).toString());  //prende il valore della colonna 4 ossia Disponibilita
			  int value = disponibilita.intValue();
			 //COMBOBOX
			  JComboBox<Integer> box = new JComboBox<Integer>();
				             for (int i=1;i<=value;i++){
				            	       
					                   box.addItem(i);
					                        
				               }
			        box.setEditable(false); 
			 //DA LA POSSBILITA DI SCEGLIERE LA QUANTITA 
			       if(disponibilita == 0){JOptionPane.showMessageDialog(null, "Il prodotto è esaurito");}
			       else{
			        String [] options ={"OK"};
			        int pannello = JOptionPane.showOptionDialog(null,box,"Selezionare la quantita",JOptionPane.NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				    if (pannello == 0){
				    		int scelta = Integer.parseInt(box.getSelectedItem().toString());
				    	
			 //SALVATAGGO DATI E CONTROLLO DOPPIONI IN BASE AL HASHCODE DEL PRODOTTO
				    if(sessionCar.isEmpty()){
				    	sessionCar.put(p, scelta);
				                            }
				    else
				    {
				       /*Set<Prodotto> keySet = sessionCar.keySet();
					   Iterator<Prodotto> iterator = keySet.iterator();
					   while(iterator.hasNext()){
						   
						   Prodotto key = iterator.next();
						   if (p.getNome_Prodotto() == key.getNome_Prodotto()){
							   sessionCar.remove(key);
							   break;
						   }
						   }*/
				    	for(Prodotto key : sessionCar.keySet()){	
				    	if (p.getNome_Prodotto().equals(key.getNome_Prodotto())){
							   sessionCar.remove(key);
							   break;
							  
						   }
				    	
				    	}
				    	sessionCar.put(p, scelta);
					  };
				    
				 }
				    	
				    	}
			    }
	    catch(ArrayIndexOutOfBoundsException e){
	    	JOptionPane.showMessageDialog(null, "Selezionare un prodotto");
	    }
	 }
	
   public Vector<String[]> listaProdotti(){
	   Vector<String[]> lista = new Vector<String[]>();
	   Set<Prodotto> keySet = sessionCar.keySet();
	   Iterator<Prodotto> iterator = keySet.iterator();
	   
	   while(iterator.hasNext()){
		   Prodotto key = iterator.next();   
		   String[] elemento = new String[7];
		   elemento[0] = key.getNome_Prodotto();
		   elemento[1] = key.getCategoria();
		   elemento[2] = key.getDescrizione();
		   elemento[3] = Integer.toString(key.getDisponibilita_Magazzino());
		   elemento[4] = Float.toString(key.getPrezzo());
		   elemento[5] = key.getProduttore();
		   elemento[6] = Integer.toString(sessionCar.get(key));
		   lista.addElement(elemento);
	   }
	        
	        return lista;
	  
   }
   public void rimuoviProdotto(JTable table,DefaultTableModel dtm){
	   //GRAFICA
	   try{
		   
		   int row = table.getSelectedRow();
		   dtm = (DefaultTableModel) table.getModel();
		   Object[] cella =  new Object[table.getColumnCount()];
		   for (int col=0;col<table.getColumnCount()-1;col++){
				 cella [col] = table.getValueAt(row, col);
			 } 
		   Prodotto p = new Prodotto();
		   p.setNome_Prodotto((String) cella [0]);
			 p.setCategoria((String) cella [1]);
			 p.setDescrizione((String) cella [2]);
			 p.setDisponibilita_Magazzino(Integer.parseInt((String) cella [3]));
			 p.setPrezzo(Float.parseFloat((String) cella [4]));
			 p.setProduttore((String) cella [5]);
	       
	       /*Set<Prodotto> keySet = sessionCar.keySet();
		   Iterator<Prodotto> iterator = keySet.iterator();
		   while(iterator.hasNext()){
			   Prodotto key = iterator.next(); 
			  
			   if (p.getNome_Prodotto() == key.getNome_Prodotto()){
				   sessionCar.remove(key);
			   }
		   }*/
			 for(Prodotto key : sessionCar.keySet()){
				 if (p.getNome_Prodotto().equals(key.getNome_Prodotto())){
					   sessionCar.remove(key);
					   break;
				   }
			 }
		   
		   dtm.removeRow(row);
	      
	   }
	   catch(ArrayIndexOutOfBoundsException e){
	    	JOptionPane.showMessageDialog(null, "Selezionare un prodotto");
	    }
	   
   }
  public void modificaQuantita(JTable table,DefaultTableModel dtm){
	  try{ 
	  Object[] opzioni = {"Aggiungi","Rimuovi","Annulla"};
	  int risposta = JOptionPane.showOptionDialog(null,"Opzioni di modifica:", "Modifica quantità",
              JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
              null, opzioni, null);
      
	  //AGGIUNGE UN PRODOTTO
	  if (risposta == JOptionPane.YES_OPTION){
		  int row = table.getSelectedRow(); //valore della riga
		  int disp = Integer.parseInt( table.getValueAt(row, 3).toString() ); // valore della disponibilità
		  int vecchia_scelta = Integer.parseInt(table.getValueAt(row, 6).toString()); //valore della scelta effetttuata nel catalogo
		
		//COMBOBOX creato in base alla disponibilità e la scelta effettuata nel catalogo
		  if (disp > vecchia_scelta){
			  JComboBox<Integer> box = new JComboBox<Integer>();
                  for (int i=1;i<=disp-vecchia_scelta;i++){
   	                      box.addItem(i);
                  }
                  box.setEditable(false);  
                  
         		 //QUANTITA DA AGGIUNGERE       
                    String [] options ={"OK"};
			        int pannello = JOptionPane.showOptionDialog(null,box,"Seleziona la quantita",JOptionPane.NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				        if (pannello == 0){
         			    int nuova_quantita = Integer.parseInt(box.getSelectedItem().toString());
         			    int somma = nuova_quantita + vecchia_scelta;
         			    table.setValueAt(somma, row, 6);
        //AGGIORNAMENTO HASHMAP
         			   Object[] cella =  new Object[table.getColumnCount()];
         			   for (int col=0;col<table.getColumnCount()-1;col++){
         					 cella [col] = table.getValueAt(row, col);
         				 } 
         			   Prodotto p = new Prodotto();
         			  p.setNome_Prodotto((String) cella [0]);
     				  p.setCategoria((String) cella [1]);
     				  p.setDescrizione((String) cella [2]);
     				  p.setDisponibilita_Magazzino(Integer.parseInt((String) cella [3]));
     				  p.setPrezzo(Float.parseFloat((String) cella [4]));
     				  p.setProduttore((String) cella [5]);
     			 
     				  for(Prodotto key : sessionCar.keySet()){
     					 if (p.getNome_Prodotto().equals(key.getNome_Prodotto())){
     						sessionCar.remove(key);
      					   sessionCar.put(p, somma);
      					   break;
     					    }
     					 }
     				  
         		      /* Set<Prodotto> keySet = sessionCar.keySet();
         			   Iterator<Prodotto> iterator = keySet.iterator();
         			   while(iterator.hasNext()){
         				   
         				   Prodotto key = iterator.next(); 
         				 
         				   if (p.getNome_Prodotto() == key.getNome_Prodotto()){
         					   
         				   }
         			   }
         			    */
         			     }
		  }
                  else JOptionPane.showMessageDialog(null, "Hai esaurito il prodotto");

		  
		}
	  
	  //RIMUOVI UN PRODOTTO
	  
	  if (risposta == JOptionPane.NO_OPTION){
		  int row = table.getSelectedRow();
		  int vecchia_scelta = Integer.parseInt(table.getValueAt(row, 6).toString());
		  if( vecchia_scelta == 1){JOptionPane.showMessageDialog(null, "Se vuoi rimuovere il prodotto clicca su 'Rimuovi'");}
		  else{
			//COMBOBOX creato in base alla disponibilità e la scelta effettuata nel catalogo
			  
			  JComboBox<Integer> box = new JComboBox<Integer>();
				             for (int i=1;i<vecchia_scelta;i++){
				            	       
					                   box.addItem(i);
					                   
					                        
				               }
			        box.setEditable(false);  
			        
			        
			 //QUANTITA DA RIMUOVERE
			        String [] options ={"OK"};
			        int pannello = JOptionPane.showOptionDialog(null,box,"Seleziona la quantita",JOptionPane.NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				        if (pannello == 0){
				    int nuova_quantita = Integer.parseInt(box.getSelectedItem().toString());
				    int somma = vecchia_scelta-nuova_quantita;
				   try{if (somma == 0){
					      rimuoviProdotto(table, dtm);
					    }
					    table.setValueAt(somma, row, 6);
					  //AGGIORNAMENTO HASHMAP
	         			   Object[] cella =  new Object[table.getColumnCount()];
	         			   for (int col=0;col<table.getColumnCount();col++){
	         					 cella [col] = table.getValueAt(row, col);
	         				 } 
	         			   Prodotto p = new Prodotto();
	         			  p.setNome_Prodotto((String) cella [0]);
	     				 p.setCategoria((String) cella [1]);
	     				 p.setDescrizione((String) cella [2]);
	     				 p.setDisponibilita_Magazzino(Integer.parseInt((String) cella [3]));
	     				 p.setPrezzo(Float.parseFloat((String) cella [4]));
	     				 p.setProduttore((String) cella [5]);
	     			 
	     			    for(Prodotto key : sessionCar.keySet()){
	     			    	if (p.getNome_Prodotto().equals(key.getNome_Prodotto())){
	         					   sessionCar.remove(key);
	         					   sessionCar.put(p, somma);
	         					  break;
	         				   }
	     			    }
	         		      /* Set<Prodotto> keySet = sessionCar.keySet();
	         			   Iterator<Prodotto> iterator = keySet.iterator();
	         			   while(iterator.hasNext()){
	         				   
	         				   Prodotto key = iterator.next(); 
	         				  
	         				   if (p.getNome_Prodotto() == key.getNome_Prodotto()){
	         					   sessionCar.remove(key);
	         					   sessionCar.put(p, somma);
	         				   }
	         			   }*/
				   }
				   
				   catch(ArrayIndexOutOfBoundsException e){
					   JOptionPane.showMessageDialog(null, "Hai eliminato il prodotto dal carrello");
				   }
	  }
	  }
	  }
	  }catch(ArrayIndexOutOfBoundsException e){
	    	JOptionPane.showMessageDialog(null, "Selezionare un prodotto");
	    }	
	 
	 
	  
  }
  public void svuotaCarrello_confermaOrdine(DefaultTableModel dtm){
	  sessionCar.clear();
	  Ordine.getInstance().ordine_magazzino.clear();
	  dtm.setRowCount(0);
  }
}



