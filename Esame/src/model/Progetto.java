package model;

import java.util.Vector;

import dao.OrdineDAO;
import dao.ProgettoDAO;

public class Progetto {
	private String Nome_Progetto;
	private float Spesa_attuale;
	
	private static Progetto instance;
	
	 public static Progetto getInstance()
	    {
	    	if (instance==null)
	    	{
	    		instance = new Progetto();
	    	}
	    	return instance;
	    }
	public Progetto() {
		// TODO Auto-generated constructor stub
	}
	public Vector<String[]> getNomeProgetto(){
		return ProgettoDAO.getInstance().getNomeProgetto();
	}
	public void aggionaSpesaProgetto(){
		ProgettoDAO.getInstance().aggionaSpesaProgetto();
	}
	public Progetto(String nomeProgetto){
		super();
	}

	public String getNome_Progetto() {
		return Nome_Progetto;
	}

	public void setNome_Progetto(String nome_Progetto) {
		Nome_Progetto = nome_Progetto;
	}

	public float getSpesa_attuale() {
		return Spesa_attuale;
	}

	public void setSpesa_attuale(float spesa_attuale) {
		Spesa_attuale = spesa_attuale;
	}
}
