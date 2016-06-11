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

	public void RifornimentoProdotto(int quantita, int codice_prodotto) {
		  
		Magazziniere m = new Magazziniere(quantita, codice_prodotto);
		m.RifornimentoProdotto();
		
	}
	  
	 
	  
	  public Vector<String[]> PocaDisponibilita(){
		  Magazziniere m = new Magazziniere();
		  return m.PocaDisponibilita();
	  }

	  public Vector<String[]> ListaRichiestePendenti() {
		  
		  Magazziniere m = new Magazziniere();
		  return m.TableRichiestePendenti();		  
	  }
	  
	  public Vector<String[]> DettaglioRichiestePendenti(int codOrdine){
		  Magazziniere m = new Magazziniere();
		  return m.ListaRichiestePendenti(codOrdine);
	  }
	  public void EvadiOrdine(){
		  Magazziniere m = new Magazziniere();
		  m.EvadiOrdine();
	  }

}
