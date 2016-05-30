package dao;

import java.util.Vector;

import Business.MagazziniereBusiness;
import dbconnection.DbConnection;
import model.Ordine;
import model.Sessione;
import model.UtenteRegistrato;

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
	
	//Metodo per ottenere il codice del magazzino 
		public Vector<String[]> codMagazzino(){
			DbConnection con = DbConnection.getInstance();
			UtenteRegistrato utente = (UtenteRegistrato) Sessione.getInstance().session.get("utente_corrente");
			String username = utente.getUsername();
		
			Vector<String[]> codMagazzino = con.eseguiQuery("select Magazziniere.CodMagazzino from Magazziniere "
				+ "inner join Utente_Registrato on Magazziniere.idMagazziniere "
				+ "= utente_registrato.idUtenteRegistrato and utente_registrato.Username = \""+username+"\"");

		return codMagazzino;
		}
	
	public Vector<String[]> PocaDisponibilita(){   //poca disponibilit‡ nel magazzino 1 
		
		DbConnection con = DbConnection.getInstance();
		int codMagazzino = MagazziniereBusiness.getInstance().CodMagazzino();
		
		Vector<String[]> carenze = con.eseguiQuery("select Prodotto.Nome, Prodotto.Categoria, Prodotto.Descrizione, "
				+ "ProdottoInMagazzino.DisponibilitaInMagazzino, Prodotto.Prezzo, Prodotto.Produttore from Prodotto inner join"
				+ " ProdottoInMagazzino on Prodotto.CodiceProdotto = ProdottoInMagazzino.CodiceProdottoInMagazzino where "
				+ "idMagazzino = '"+codMagazzino+"'and DisponibilitaInMagazzino < 6");
		
		
		return carenze;
	}

	public void AggiungiProdotti(int aggiunta,int codice_prodotto){
		DbConnection con = DbConnection.getInstance();
		Boolean aggiornamento = con.eseguiAggiornamento("update ProdottoInMagazzino set DisponibilitaInMagazzino"
								+ "= DisponibilitaInMagazzino + "+aggiunta+" "
								+ "where CodiceProdottoInMagazzino ="+codice_prodotto); 
	}
	
	public Vector<String[]> TableRichiestePendenti(){
		DbConnection con = DbConnection.getInstance();
		Vector<String[]> table = con.eseguiQuery("select Utente_Registrato.Nome, utente_registrato.Cognome,"
								+ " Ordine.CodiceOrdine from Utente_Registrato inner join Ordine"
								+ " where CodiceDipendente = idUtenteRegistrato and StatoOrdine ="+false);
		return table;
	}
	
	public Vector<String[]> RichiestePendenti(int codOrdine){
		DbConnection con = DbConnection.getInstance();
		int codMagazzino = MagazziniereBusiness.getInstance().CodMagazzino();
		
		
		Vector<String[]> table = con.eseguiQuery("Select prodotto.nome, prodotto.categoria, prodotto.produttore, "
				+ "Costituito_da.Quantit‡Ordinata, prodotto.prezzo from Prodotto inner join costituito_da on "
				+ "Prodotto.CodiceProdotto = costituito_da.idProdotto and costituito_da.idOrdine ='"+codOrdine+"' "
				+ "inner join prodottoinmagazzino on prodottoinmagazzino.CodiceProdottoInMagazzino"
				+ " = costituito_da.idProdotto and prodottoinmagazzino.idMagazzino ='"+codMagazzino+"'");
		return table;
	}
	
	public void EvadiOrdine(){
		DbConnection con = DbConnection.getInstance();
		int codOrdine =(Integer)Ordine.getInstance().sessionOrdine.get("Ordine_corrente");
		
		Boolean cambioSato = con.eseguiAggiornamento("update Ordine set StatoOrdine = true where CodiceOrdine = "+codOrdine); 
		
	}
	
}
