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
	
	public boolean ControlloMag(){
		MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
		 UtenteRegistrato utente = (UtenteRegistrato) Sessione.getInstance().session.get("utente_corrente");
		 String username = utente.getUsername();
		 //Verifica se il magazziniere che ha effettuato il login è quello del magazzino 1
		 boolean magazziniere_attuale = username.equals(magazziniere.Username().get(0)[0]); 
		 return magazziniere_attuale;
		
	}
	
	public void AggiuntaProdotto(){
		
		MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
		magazziniere.AggiungiProdotti(quantita_rifornita,codice_prodotto_rifornito);
		
	}
	
	public Vector<String[]> PocaDisponibilita1() {
		
		MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
		return magazziniere.PocaDisponibilita1();
		
	}
	
public Vector<String[]> PocaDisponibilita2() {
		
		MagazziniereDAO magazziniere = MagazziniereDAO.getInstance();
		return magazziniere.PocaDisponibilita2();
		
	}

	}
