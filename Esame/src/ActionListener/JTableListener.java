
	package ActionListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Business.DipendenteBusiness;
import Business.ProgettoBusiness;
import model.Carrello;
import model.Ordine;
import model.Sessione;
import model.UtenteRegistrato;
import view.GuiCarrello;


public class JTableListener implements ActionListener {
     JTable table = new JTable();
     DefaultTableModel dtm = new DefaultTableModel();
     double SpesaTotale = 0;
     GuiCarrello win;
     
    
     
    public JTableListener() {}
   
	public JTableListener(JTable table) {
		super();
		this.table = table;
		table.getModel();
	}
	public JTableListener(DefaultTableModel dtm) {
		super();
		this.dtm = dtm;
	}
	
	public JTableListener(DefaultTableModel dtm,JTable table) {
		super();
		this.table = table;
		this.dtm = dtm;
	}
	
	public JTableListener(JTable table,DefaultTableModel dtm,GuiCarrello win){
		this.table = table;
		this.win = win;
		this.dtm = dtm;
		
	}
	
	//Per la stampa
	public JTableListener(DefaultTableModel dtm,JTable table, double SpesaTotale) {
		super();
		this.table = table;
		this.dtm = dtm;
		this.SpesaTotale = SpesaTotale;
	}


	public void actionPerformed(ActionEvent arg0) {
	 if (arg0.getActionCommand().equals("aggiungi")){
	 
	 Carrello.getInstance().aggiungiProdotto(table);
	 
	 
	 
    }
	 else  if (arg0.getActionCommand().equals("rimuovi")){
		 
	 Carrello.getInstance().rimuoviProdotto(table, dtm);
	 
	 	//per modificare la JLabel della Spesa Totale si individua il JLabel component e si opera
		Container d = win.getContentPane();
		JPanel centro = (JPanel) d.getComponent(1);
		JLabel label = (JLabel) centro.getComponent(1);
		label.setText("Totale: € "+Carrello.getInstance().getTotale_Spesa());
	 
    }
	 else if (arg0.getActionCommand().equals("modifica")){
		 
		Carrello.getInstance().modificaQuantita(table,dtm);
	
		//per modificare la JLabel della Spesa Totale si individua il JLabel component e si opera
			Container d = win.getContentPane();
			JPanel centro = (JPanel) d.getComponent(1);
			JLabel label = (JLabel) centro.getComponent(1);
			label.setText("Totale: € "+Carrello.getInstance().getTotale_Spesa());
		
		}
		    
       
	 else  if (arg0.getActionCommand().equals("conferma")){
		 boolean ris = Ordine.getInstance().confermaOrdine();
	     if (ris == true){
	      Ordine.getInstance().inserisciOrdine();
	      DipendenteBusiness.getInstance().aggiornaTotaleSpesa();
	      ProgettoBusiness.getInstance().aggionaSpesaProgetto();
	      
	    //STAMPA DISTINTA
		  
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String data = dateFormat.format(date);
			UtenteRegistrato utente = Sessione.getInstance().session.get("utente_corrente");
			MessageFormat up = new MessageFormat("Distinta Ordine - "+data);
			MessageFormat down = new MessageFormat("Ordine effettuato da: "+utente.getNome()+" "+utente.getCognome()+" - Spesa Totale = €"+Carrello.getInstance().getTotale_Spesa());
			
			
			
			try {
				table.print(JTable.PrintMode.FIT_WIDTH, up,down);
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	      Carrello.getInstance().svuotaCarrello_confermaOrdine(dtm);
	      
	   }
	 }
	 }
	}

		  

	