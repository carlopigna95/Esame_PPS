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

	public int CodMagazzino() {
		 
		 Magazziniere m = new Magazziniere();
		 return m.CodMagazzino();
		 
	}

	public void AggiungiProdotti(int quantita, int codice_prodotto) {
		  
		Magazziniere m = new Magazziniere(quantita, codice_prodotto);
		m.AggiuntaProdotto();
		
	}
	  
	 
	  
	  public Vector<String[]> PocaDisponibilita(){
		  Magazziniere m = new Magazziniere();
		  return m.PocaDisponibilita();
	  }

	  public Vector<String[]> TableRichiestePendenti() {
		  
		  Magazziniere m = new Magazziniere();
		  return m.TableRichiestePendenti();		  
	  }
	  
	  public Vector<String[]> RichiestePendenti(int codOrdine){
		  Magazziniere m = new Magazziniere();
		  return m.RichiestePendenti(codOrdine);
	  }
	  public void EvadiOrdine(){
		  Magazziniere m = new Magazziniere();
		  m.EvadiOrdine();
	  }

}
