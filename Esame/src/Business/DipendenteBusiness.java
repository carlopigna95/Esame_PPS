package Business;


public class DipendenteBusiness {

	private static DipendenteBusiness instance;
	public static DipendenteBusiness getInstance()
	{
		if(instance == null)
			instance = new DipendenteBusiness();
		return instance;
	}
	
	
	}

