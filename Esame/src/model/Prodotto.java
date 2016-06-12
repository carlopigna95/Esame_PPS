package model;

import dao.ProdottoDAO;

public class Prodotto {
    
	private int Codice_Prodotto;
	private String Nome_Prodotto;
	private String Categoria;
	private int Disponibilita_Magazzino;
	private float Prezzo;
	private String Descrizione;
	private String Produttore;
	private float PrezzoTotale;
	public Prodotto(String Nome_Prodotto){
		  this.Nome_Prodotto = Nome_Prodotto;
		  
		 }
	public Prodotto(){
		
	}
	public String getNome_Prodotto() {
		return Nome_Prodotto;
	}
	public void setNome_Prodotto(String nome_Prodotto) {
		Nome_Prodotto = nome_Prodotto;
	}
	public float getPrezzo() {
		return Prezzo;
	}
	public void setPrezzo(float prezzo) {
		Prezzo = prezzo;
	}
	
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	public int getDisponibilita_Magazzino() {
		return Disponibilita_Magazzino;
	}
	public void setDisponibilita_Magazzino(int disponibilita_Magazzino) {
		Disponibilita_Magazzino = disponibilita_Magazzino;
	}
	public String getDescrizione() {
		return Descrizione;
	}
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
	public String getProduttore() {
		return Produttore;
	}
	public void setProduttore(String produttore) {
		Produttore = produttore;
	}
	public int getCodice_Prodotto() {
		return Codice_Prodotto;
	}
	public void setCodice_Prodotto(int codice_Prodotto) {
		Codice_Prodotto = codice_Prodotto;
	}
	public int codiceProdotto(){
		  ProdottoDAO prodotto = ProdottoDAO.getInstance();
		  int codice = prodotto.CodiceProdotto(Nome_Prodotto);
		  return codice;
	
	}
	
	public float getPrezzoTotale() {
		return PrezzoTotale;
	}
	public void setPrezzoTotale(float prezzoTotale) {
		PrezzoTotale = prezzoTotale;
	}
	
	       

}