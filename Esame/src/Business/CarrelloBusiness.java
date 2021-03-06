package Business;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Carrello;
import view.GuiCarrello;

public class CarrelloBusiness {

	private static CarrelloBusiness instance;
	public static CarrelloBusiness getInstance()
	{
		if(instance == null)
			instance = new CarrelloBusiness();
		return instance;
	}

	public void aggiungiProdotto(JTable table){
		
		Carrello c = new Carrello();
		c.aggiungiProdotto(table);
	}
	public void listaProdotti(){
		Carrello c = new Carrello();
		c.listaProdotti();
	}
	public void rimuoviProdotto(JTable table,DefaultTableModel dtm){
		Carrello c = new Carrello();
		c.rimuoviProdotto(table, dtm);
		
	}
	public void modificaQuantita(JTable table,DefaultTableModel dtm){
		Carrello c = new Carrello();
		c.modificaQuantita(table,dtm);
		
	}
	public void svuotaCarrello_confermaOrdine(DefaultTableModel dtm){
		Carrello c = new Carrello();
		c.svuotaCarrello_confermaOrdine(dtm);
		
	}
	
	
	
}
