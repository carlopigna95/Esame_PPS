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
	private int quantitaSelezionata;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Categoria == null) ? 0 : Categoria.hashCode());
		result = prime * result + Codice_Prodotto;
		result = prime * result + ((Descrizione == null) ? 0 : Descrizione.hashCode());
		result = prime * result + Disponibilita_Magazzino;
		result = prime * result + ((Nome_Prodotto == null) ? 0 : Nome_Prodotto.hashCode());
		result = prime * result + Float.floatToIntBits(Prezzo);
		result = prime * result + ((Produttore == null) ? 0 : Produttore.hashCode());
		result = prime * result + quantitaSelezionata;
		return result;
	}
	
	public int getQuantitaSelezionata() {
		return quantitaSelezionata;
	}
	public void setQuantitaSelezionata(int quantitaSelezionata) {
		this.quantitaSelezionata = quantitaSelezionata;
	}
	
	       

}