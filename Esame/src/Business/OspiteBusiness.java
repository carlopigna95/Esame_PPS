package Business;

import java.util.Vector;

import model.Ospite;

public class OspiteBusiness {
	
	public OspiteBusiness(){
		
	}
	
	private static OspiteBusiness instance;
	
	public static OspiteBusiness getInstance()
	{
		if(instance == null)
			instance = new OspiteBusiness();
		return instance;
	}
	
	public Vector<String[]> GeneraCatalogo1(){
		Ospite ospite = new Ospite();
		return ospite.GeneraCatalogo1();
		
	}
	
	public Vector<String[]> GeneraCatalogo2(){
		Ospite ospite = new Ospite();
		return ospite.GeneraCatalogo2();
		
	}
	
	
	
	
	

}
