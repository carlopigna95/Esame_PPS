package model;

import java.util.Vector;

import dao.DipendenteDAO;

public class Dipendente extends UtenteRegistrato {

  private Integer Codice_Progetto;
  private Integer Codice_Dipendente;
public Integer getCodice_Progetto() {
	return Codice_Progetto;
}
public void setCodice_Progetto(Integer codice_Progetto) {
	Codice_Progetto = codice_Progetto;
}
public Integer getCodice_Dipendente() {
	return Codice_Dipendente;
}
public void setCodice_Dipendente(Integer codice_Dipendente) {
	Codice_Dipendente = codice_Dipendente;
}
 public Vector<String[]> GeneraCatalogo1(){
	 return DipendenteDAO.getInstance().GeneraCatalogo1();
 }
 public Vector<String[]> GeneraCatalogo2(){
	 return DipendenteDAO.getInstance().GeneraCatalogo2();
 }
 public Vector<String[]> Disponibilità(){
	 return DipendenteDAO.getInstance().Disponibilita();
 }
public void aggiornaSpesa(){
	DipendenteDAO.getInstance().aggiornaSpesa();
}

}