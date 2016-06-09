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
	public Vector<String[]> getProgetto(){
		Ordine o = new Ordine();
		return o.getProgetto();
	} 
	public void confermaOrdine(){
		Ordine o = new Ordine();
		o.confermaOrdine();
	}
	public boolean inserisciOrdine(){
		Ordine o = new Ordine();
		return o.inserisciOrdine();
	}
	public int getCodiceMagazzino(int codice_prodotto){
		Ordine o = new Ordine();
		return o.getCodiceMagazzino(codice_prodotto);
	}
}
