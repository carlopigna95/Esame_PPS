package Business;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Ordine;

public class OrdineBusiness {

	private static OrdineBusiness instance;
	public static OrdineBusiness getInstance()
	{
		if(instance == null)
			instance = new OrdineBusiness();
		return instance;
	}
	JTable table;
	DefaultTableModel dmt;
	public Vector<String[]> getProgetto(){
		Ordine o = new Ordine();
		return o.getProgetto();
	} 
	public void conferma(){
		Ordine o = new Ordine();
		o.confermaOrdine();
	}
	public boolean inserisciOr(){
		Ordine o = new Ordine();
		return o.inserisciOrdini();
	}
}
