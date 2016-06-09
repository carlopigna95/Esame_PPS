package model;

import java.util.Vector;

import dao.MagazziniereDAO;

public class Magazziniere extends UtenteRegistrato {

	 private int Codice_Magazzino;
	 private int codice_prodotto_rifornito;
	 private int quantita_rifornita;
	 
	 //Costruttore utilizzato nel metodo "AggiuntaProdotto"
	 public Magazziniere(int quantita,int codice){
		 super();
		 codice_prodotto_rifornito = codice;
		 quantita_rifornita = quantita;
		 
	 } 
	 
	 public Magazziniere(){
		 super();
	 }
	 
	

	public int getCodice_Magazzino() {
		return Codice_Magazzino;
	}

	public void setCodice_Magazzino(int codice_Magazzino) {
		Codice_Magazzino = codice_Magazzino;
	}
	
	public int CodMagazzino(){
		MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
		int codMagazzino = Integer.parseInt(magazziniere.codMagazzino().get(0)[0]); 
		return codMagazzino;
		
	}
	
	public void AggiuntaProdotto(){
		
		MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
		magazziniere.AggiungiProdotti(quantita_rifornita,codice_prodotto_rifornito);
		
	}
	
	//per la generazione della Table dei prodotti con poca disponibilità
	public Vector<String[]> PocaDisponibilita() {
		
		MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
		return magazziniere.PocaDisponibilita();
		
	}
	
//metodo per la table nella tab "Richieste Pendenti" di GUIMagazziniere
	public Vector<String[]> TableRichiestePendenti(){
	
	MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
	return magazziniere.TableRichiestePendenti();
}
	
//table per la finestra a parte delle dettaglio ordine pendente
	public Vector<String[]> DettaglioRichiestePendenti(int codOrdine){
	MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
	return magazziniere.DettaglioRichiestePendenti(codOrdine);
	}
	
	public void EvadiOrdine(){
		MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
		magazziniere.EvadiOrdine();
	}

}
