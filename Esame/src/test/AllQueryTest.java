package test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import Business.CapoProgettoBusiness;
import Business.DipendenteBusiness;
import Business.ProdottoBusiness;
import dbconnection.DbConnection;

public class AllQueryTest {

	@Test
	public void test() {
		
		//QUERY DI CAPO PROGETTO
		//Test totale spesa per progetto
		double TotaleSpesaProgetto = Double.parseDouble(CapoProgettoBusiness.getInstance().TotaleSpesaProgetto().get(0)[1]);
		assertTrue(TotaleSpesaProgetto > 0);
		
		//Test totale spesa per dipendente
		double TotaleSpesaDipendente = Double.parseDouble(CapoProgettoBusiness.getInstance().TotaleSpesaDipendente().get(0)[2]);
		assertTrue(TotaleSpesaDipendente > 0);
		
		//QUERY DIPENDENTE 
		
		//Test di generazione del catalogo1
		Vector<String[]> catalogo1 = DipendenteBusiness.getInstance().GeneraCatalogo1();
		assertFalse(catalogo1.isEmpty());
		
		//Test di generazione del catalogo2
		Vector<String[]> catalogo2 = DipendenteBusiness.getInstance().GeneraCatalogo2();
		assertFalse(catalogo2.isEmpty());
		
		//QUERY MAGAZZINIERE
		// Test di controllo magazzino
		String username = "AndreaD"; // lavora presso il magazzino 2
		DbConnection con = DbConnection.getInstance();
		Vector<String[]> codMagazzino = con.eseguiQuery("select Magazziniere.CodMagazzino from Magazziniere "
			+ "inner join Utente_Registrato on Magazziniere.idMagazziniere "
			+ "= utente_registrato.idUtenteRegistrato and utente_registrato.Username = \""+username+"\"");
		int magazzino = Integer.parseInt(codMagazzino.get(0)[0]);
		assertEquals(magazzino,2);
		
		//Test di controllo richieste pendenti
		
		//Il test d‡ esisto positivo solo se ci sono effettivamente richieste pendenti
		Vector<String[]> richiestePendenti = con.eseguiQuery("select Utente_Registrato.Nome, utente_registrato.Cognome,"
				+ " Ordine.CodiceOrdine from Utente_Registrato inner join Ordine"
				+ " where CodiceDipendente = idUtenteRegistrato and StatoOrdine ="+false+" and Ordine.CodiceMagazzino ="+magazzino);
		
		assertFalse(richiestePendenti.isEmpty());
		
		//Test di controllo poca disponibilit‡
		//Il test d‡ esito positivo solo se ci sono prodotti con poca disponibilit‡
		Vector<String[]> carenze = con.eseguiQuery("select Prodotto.Nome, Prodotto.Categoria, Prodotto.Descrizione, "
				+ "ProdottoInMagazzino.DisponibilitaInMagazzino, Prodotto.Prezzo, Prodotto.Produttore from Prodotto inner join"
				+ " ProdottoInMagazzino on Prodotto.CodiceProdotto = ProdottoInMagazzino.CodiceProdottoInMagazzino where "
				+ "idMagazzino = '"+magazzino+"'and DisponibilitaInMagazzino < 5");
		
		assertFalse(carenze.isEmpty());
		
		//Test sul dettaglio richieste pendenti
		
		int codOrdine = 9;  //L'ordine 9 Ë relativo al secondo magazzino
		Vector<String[]> dettaglio = con.eseguiQuery("Select prodotto.nome, prodotto.categoria, prodotto.produttore, "
				+ "Costituito_da.Quantit‡Ordinata, prodotto.prezzo from Prodotto inner join costituito_da on "
				+ "Prodotto.CodiceProdotto = costituito_da.idProdotto and costituito_da.idOrdine ='"+codOrdine+"' "
				+ "inner join prodottoinmagazzino on prodottoinmagazzino.CodiceProdottoInMagazzino"
				+ " = costituito_da.idProdotto and prodottoinmagazzino.idMagazzino ='"+magazzino+"'");
		
		assertTrue(dettaglio.isEmpty());
		
		//QUERY PRODOTTO
		
		//Test getCodiceProdotto
		int codiceProdotto = ProdottoBusiness.getInstance().CodiceProdotto("Penna Stilo");
		assertEquals(7,codiceProdotto);
		
		//QUERY PROGETTO
		int IdUtente = 5;
		String nome = "HASS_Project";
		
				Vector<String[]> progetto = con.eseguiQuery("select NomeProgetto from progetto where idDipendente="+IdUtente);
			assertEquals(nome,progetto.get(0)[0]);
	}
	
	

		
}
