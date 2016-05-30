package Business;

import java.util.Vector;

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
	public void conferma(){
		Ordine o = new Ordine();
		o.confermaOrdine();
	}
}