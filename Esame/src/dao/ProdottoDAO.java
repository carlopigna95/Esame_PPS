package dao;

import java.util.Vector;

import dbconnection.DbConnection;

public class ProdottoDAO {
	
	public static ProdottoDAO instance = new ProdottoDAO();
	
	public static ProdottoDAO getInstance()
    {
    	if (instance==null)
    	{
    		instance = new ProdottoDAO();
    	}
    	return instance;
    }
	
	public int CodiceProdotto(String nome_prodotto){
		DbConnection con = DbConnection.getInstance();
		Vector<String[]> vettore = con.eseguiQuery("Select CodiceProdotto from Prodotto where Nome =\""+nome_prodotto+"\"");
		int codice = Integer.parseInt(vettore.get(0)[0]);
	
		return codice;
		
	}

}
