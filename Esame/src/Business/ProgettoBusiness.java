package Business;

import java.util.Vector;

import model.Progetto;

public class ProgettoBusiness {
	
	private static ProgettoBusiness instance;
	
	public static ProgettoBusiness getInstance()
	{
		if(instance == null)
			instance = new ProgettoBusiness();
		return instance;
	}
	
	public Vector<String[]> getNomeProgetto(){
		Progetto p = new Progetto();
		return p.getNomeProgetto();
	}
	public void aggionaSpesaProgetto(){
		Progetto p = new Progetto();
		p.aggionaSpesaProgetto();
	}
}
