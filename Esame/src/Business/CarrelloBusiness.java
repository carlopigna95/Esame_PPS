package Business;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Carrello;

public class CarrelloBusiness {

	private static CarrelloBusiness instance;
	public static CarrelloBusiness getInstance()
	{
		if(instance == null)
			instance = new CarrelloBusiness();
		return instance;
	}

	public void aggiungiProdotto(JTable table,JTable table1){
		
		Carrello c = new Carrello();
		c.aggungiProdotto(table);
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
	
}
