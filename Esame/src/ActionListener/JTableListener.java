package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Business.OrdineBusiness;
import dao.DipendenteDAO;
import dao.OrdineDAO;
import model.Carrello;
import model.Ordine;
import model.Prodotto;
import view.GuiCarrello;


public class JTableListener implements ActionListener {
     JTable table;
    DefaultTableModel dtm;
    
   
	public JTableListener(JTable table) {
		super();
		this.table = table;
		table.getModel();
	}
	
	public JTableListener(DefaultTableModel dtm,JTable table) {
		super();
		this.table = table;
		dtm = (DefaultTableModel) table.getModel();
	}
	public JTableListener(JTable table,DefaultTableModel dtm) {
		super();
		this.table = table;
		dtm = (DefaultTableModel) table.getModel();
	}
	
	


	public JTableListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	 if (arg0.getActionCommand().equals("aggiungi")){
	 
	 Carrello.getInstance().aggungiProdotto(table);
	 Vector<String[]> lista = Carrello.getInstance().listaProdotti();
	 /*for(int i=0;i<lista.size();i++){
		 for (int j=0;j<7;j++){
			 System.out.println(lista.get(i)[j]); 
		 }
		 System.out.println("----------------------------------");
	 }*/
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
		  DipendenteDAO.getInstance().aggiornaSpesa();
	
	
	 }
	 }
	}