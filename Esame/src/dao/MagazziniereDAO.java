package dao;

import java.util.Vector;

import dbconnection.DbConnection;

public class MagazziniereDAO {
    
private static MagazziniereDAO instance;
    
    public static MagazziniereDAO getInstance()
    {
    	if (instance==null)
    	{
    		instance = new MagazziniereDAO();
    	}
    	return instance;
    }
	public MagazziniereDAO() {
		
	}
	
	public Vector<String[]> PocaDisponibilita1(){   //poca disponibilità nel magazzino 1 
		
		DbConnection con = DbConnection.getInstance();
		
		Vector<String[]> carenze = con.eseguiQuery("select Prodotto.Nome, Prodotto.Categoria,"
				+ "Prodotto.Descrizione, ProdottoInMagazzino.DisponibilitaInMagazzino, "
				+ "Prodotto.Prezzo, Prodotto.Produttore from Prodotto inner join ProdottoInMagazzino "
				+ "on Prodotto.CodiceProdotto = ProdottoInMagazzino.CodiceProdottoInMagazzino "
				+ "where idMagazzino = 1 and DisponibilitaInMagazzino <= 10");
		
		
		return carenze;
	}
	
	public Vector<String[]> PocaDisponibilita2(){   //poca disponibilità nel magazzino 2
		
		DbConnection con = DbConnection.getInstance();
		
		Vector<String[]> carenze = con.eseguiQuery("select Prodotto.Nome, Prodotto.Categoria,"
				+ "Prodotto.Descrizione, ProdottoInMagazzino.DisponibilitaInMagazzino, "
				+ "Prodotto.Prezzo, Prodotto.Produttore from Prodotto inner join ProdottoInMagazzino "
				+ "on Prodotto.CodiceProdotto = ProdottoInMagazzino.CodiceProdottoInMagazzino "
				+ "where idMagazzino = 2 and DisponibilitaInMagazzino <= 10");
		
		
		return carenze;
	}  

	public Vector<String[]> Username(){
	DbConnection con = DbConnection.getInstance();
	
	Vector<String[]> username = con.eseguiQuery("select Utente_Registrato.Username, Magazziniere.idMagazziniere "
								+ "	from Utente_Registrato inner join Magazziniere on idUtenteRegistrato"
								+ "= idMagazziniere where CodMagazzino = 1");

	return username;
	}
	
	
	
	public void AggiungiProdotti(int aggiunta,int codice_prodotto){
		DbConnection con = DbConnection.getInstance();
		Boolean aggiornamento = con.eseguiAggiornamento("update ProdottoInMagazzino set DisponibilitaInMagazzino"
								+ "= DisponibilitaInMagazzino + "+aggiunta+" "
								+ "where CodiceProdottoInMagazzino ="+codice_prodotto); 
	}
	
}
