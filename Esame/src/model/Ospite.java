package model;

import java.util.Vector;

import dao.OspiteDAO;

public class Ospite {
	
	public Ospite(){
		
	}
	

	public Vector<String[]> GeneraCatalogo1(){
		
		return OspiteDAO.getInstance().GeneraCatalogo1();
		
	}
	
public Vector<String[]> GeneraCatalogo2(){
		
		return OspiteDAO.getInstance().GeneraCatalogo2();
		
	}
}
