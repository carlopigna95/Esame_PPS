package model;
import java.util.HashMap;

public class Sessione {

	private static Sessione instance;
	public HashMap<String, UtenteRegistrato> session = new HashMap<String,UtenteRegistrato>();
	
	public static synchronized Sessione getInstance() {
		if(instance == null)
			instance = new Sessione();
		return instance;
	}
}
