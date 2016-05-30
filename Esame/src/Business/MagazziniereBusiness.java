package Business;

import java.util.Vector;

import dao.MagazziniereDAO;
import model.Magazziniere;
import model.Sessione;
import model.UtenteRegistrato;

public class MagazziniereBusiness {

	private static MagazziniereBusiness instance;
	public static MagazziniereBusiness getInstance()
	{
		if(instance == null)
			instance = new MagazziniereBusiness();
		return instance;
	}
	 public boolean ControlloMagazziniere() {
		 
		 Magazziniere m = new Magazziniere();
		 return m.ControlloMag();
		 
	  }

	  public void AggiungiProdotti(int quantita, int codice_prodotto) {
		  
		Magazziniere m = new Magazziniere(quantita, codice_prodotto);
		m.AggiuntaProdotto();
		
	  }
	  
	  public void VisualizzaRichieste() {
		  
		  
	  }
	  
	  public Vector<String[]> PocaDisponibilita1(){
		  Magazziniere m = new Magazziniere();
		  return m.PocaDisponibilita1();
	  }
	  
	  public Vector<String[]> PocaDisponibilita2(){
		  Magazziniere m = new Magazziniere();
		  return m.PocaDisponibilita2();
	  }

}
