package dao;

import java.util.Vector;

import Business.CapoProgettoBusiness;
import dbconnection.DbConnection;

public class CapoProgettoDAO {
	
		
	private static CapoProgettoDAO instance;
		
	 public static CapoProgettoDAO getInstance()
		   {
		   	if (instance==null)
		   	{
	    		instance = new CapoProgettoDAO();
	    	}		    	
		   	
		   	return instance;
		   }
	 
	 
	 public String[] NomiProgetti(){
		
		 Vector <String[]> NomiProgetti = DbConnection.getInstance().eseguiQuery("select NomeProgetto, TotaleSpesa from progetto");
		 String[] ProjectNames = new String[NomiProgetti.size()];
		 
			for(int i=0;i<NomiProgetti.size();i++){
				ProjectNames[i] = NomiProgetti.get(i)[0];
			}
	
		 
		 
		 return ProjectNames;
	 }
	 
		
	 public Vector<String[]> TotaleSpesaProgetto(){
		 Vector <String[]> TotaleSpesa = DbConnection.getInstance().eseguiQuery("select NomeProgetto, TotaleSpesa from progetto");

		return TotaleSpesa;
	 
		
	}  
		 
		 

}