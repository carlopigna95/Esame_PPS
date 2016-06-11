package Business;

import java.util.Vector;

import model.Dipendente;

public class DipendenteBusiness {

	private static DipendenteBusiness instance;
	public static DipendenteBusiness getInstance()
	{
		if(instance == null)
			instance = new DipendenteBusiness();
		return instance;
	}
	
	 public Vector<String[]> GeneraCatalogo1(){
		 Dipendente d = new Dipendente();
		 return d.GeneraCatalogo1();
	 }
	 public Vector<String[]> GeneraCatalogo2(){
		 Dipendente d = new Dipendente();
		 return d.GeneraCatalogo2();
	 }
	
	public void aggiornaTotaleSpesa(){
		Dipendente d = new Dipendente();
		d.aggiornaTotaleSpesa();
	}

	
	}

