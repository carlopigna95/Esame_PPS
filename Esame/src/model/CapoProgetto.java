package model;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JRadioButton;

import dao.CapoProgettoDAO;

public class CapoProgetto extends UtenteRegistrato {
	
	
	public String[] NomiProgetti(){
		return CapoProgettoDAO.getInstance().NomiProgetti();
	}
	
	 public Vector<String[]> TotaleSpesaProgetto(){
		 Vector<String[]> spesa = CapoProgettoDAO.getInstance().TotaleSpesaProgetto();
		 return spesa;
	 } 
	 


}