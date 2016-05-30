package Business;

import dao.ProdottoDAO;

public class ProdottoBusiness {
	
	private static ProdottoBusiness instance;
	public static ProdottoBusiness getInstance()
	{
		if(instance == null)
			instance = new ProdottoBusiness();
		return instance;
	}
	
	
	public int CodiceProdotto(String nome_prodotto){
		ProdottoDAO prodotto = ProdottoDAO.getInstance();
		int codice = prodotto.CodiceProdotto(nome_prodotto);
		
		return codice; 
				
	}

}
