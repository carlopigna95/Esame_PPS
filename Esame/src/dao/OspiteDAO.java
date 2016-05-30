package dao;

import java.util.Vector;

import dbconnection.DbConnection;

public class OspiteDAO {
	
	private static OspiteDAO instance;

	
	 public static OspiteDAO getInstance()
	    {
	    	if (instance==null)
	    	{
	    		instance = new OspiteDAO();
	    	}
	    	return instance;
}
	 public OspiteDAO(){
	 }
	 
	 public Vector<String[]> GeneraCatalogo1(){  
		 
		 DbConnection con = DbConnection.getInstance();
		
		 //Seleziona i prodotti dal magazzino 1
		 Vector<String[]> prodotto1 = con.eseguiQuery("select Prodotto.Nome, Prodotto.Categoria, "
		 		+ "Prodotto.Descrizione, ProdottoInMagazzino.DisponibilitaInMagazzino,"
		 		+ " Prodotto.Prezzo, Prodotto.Produttore from Prodotto "
		 		+ "inner join ProdottoInMagazzino on Prodotto.CodiceProdotto = "
		 		+ "ProdottoInMagazzino.CodiceProdottoInMagazzino where idMagazzino = \""+1+"\"");
		 
		 return prodotto1;
		 
			 
		 }	 
	 
	 public Vector<String[]> GeneraCatalogo2(){  
		 
		 DbConnection con = DbConnection.getInstance();
		
		 //Seleziona i prodotti dal magazzino 2
		 Vector<String[]> prodotto2 = con.eseguiQuery("select Prodotto.Nome, Prodotto.Categoria, "
		 		+ "Prodotto.Descrizione, ProdottoInMagazzino.DisponibilitaInMagazzino,"
		 		+ " Prodotto.Prezzo, Prodotto.Produttore from Prodotto "
		 		+ "inner join ProdottoInMagazzino on Prodotto.CodiceProdotto = "
		 		+ "ProdottoInMagazzino.CodiceProdottoInMagazzino where idMagazzino = \""+2+"\"");
		 
		 return prodotto2;
			 
		 }	 
	 }
	
