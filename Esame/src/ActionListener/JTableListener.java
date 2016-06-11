
	package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DipendenteDAO;
import dao.OrdineDAO;
import dao.ProgettoDAO;
import model.Carrello;
import model.Ordine;
import model.Sessione;
import model.UtenteRegistrato;
import view.GuiMagazziniere;


public class JTableListener implements ActionListener {
     JTable table = new JTable();
     DefaultTableModel dtm = new DefaultTableModel();
     GuiMagazziniere magFinestra;
     double SpesaTotale = 0;
    
     
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
	 Vector<String[]> lista = Carrello.getInstance().listaProdotti();
    }
	 else  if (arg0.getActionCommand().equals("rimuovi")){
		 
	 Carrello.getInstance().rimuoviProdotto(table, dtm);
	 
    }
	 else if (arg0.getActionCommand().equals("modifica")){
		 
		Carrello.getInstance().modificaQuantita(table,dtm);
        Vector<String[]> lista = Carrello.getInstance().listaProdotti();
		
		 
	 }
	 else  if (arg0.getActionCommand().equals("conferma")){
		  Ordine.getInstance().confermaOrdine();
		  OrdineDAO.getInstance().inserisciOrdine();
		  DipendenteDAO.getInstance().aggiornaTotaleSpesa();
		  ProgettoDAO.getInstance().aggionaSpesaProgetto();
		  
		  //STAMPA DISTINTA
		  
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String data = dateFormat.format(date);
			UtenteRegistrato utente = Sessione.getInstance().session.get("utente_corrente");
			MessageFormat up = new MessageFormat("Distinta Ordine - "+data);
			MessageFormat down = new MessageFormat("Ordine effettuato da: "+utente.getNome()+" "+utente.getCognome()+" - Spesa Totale = €"+SpesaTotale);
			
			
			
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
	