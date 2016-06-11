package Business;

import java.util.Vector;

import model.CapoProgetto;

public class CapoProgettoBusiness {

	private static CapoProgettoBusiness instance;
	
	public static CapoProgettoBusiness getInstance()
	   {
	   	if (instance==null)
	   	{
 		instance = new CapoProgettoBusiness();
 	}		    	
	   	
	   	return instance;
	   }
	
	 public Vector<String[]> TotaleSpesaProgetto(){
		 
		 CapoProgetto cp = new CapoProgetto();
		 return cp.TotaleSpesaProgetto();	 
	 } 
	 
	 public Vector<String[]> TotaleSpesaDipendente(){
		 
		 CapoProgetto cp = new CapoProgetto();
		 return cp.TotaleSpesaDipendente();	 
	 } 
}
