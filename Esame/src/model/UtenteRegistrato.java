package model;

import Business.UtenteRegistratoBusiness;
import dao.UtenteRegistratoDAO;
import view.GuiLogin;

public class UtenteRegistrato {
   
  public String nome;
  public String cognome;
  private String username;
  private String password;
  private int idUtenteRegistrato;
  public char ruolo;
  public UtenteRegistrato()
  {
	  super();
  }
  public UtenteRegistrato(String username,String password)
  {
	  super();
	  this.username = username;
	  this.password = password;
  }
  public UtenteRegistrato (String nome,String cognome,String username,String password,int id,char ruolo)
  {
	  super();
	  this.setIdUtenteRegistrato(id);
	  
  }
  public UtenteRegistrato(String username,String password, int idUtenteRegistrato)
  {
   super();
   this.username = username;
   this.password = password;
   this.setIdUtenteRegistrato(idUtenteRegistrato);
   }
  public UtenteRegistrato(String username){
	   super();
	   this.username = username;
	  }
    public String getUsername() {
	return username;
                                }

    public void setUsername(String username) {
	
                           }

    public String getNome() {
    	return nome;
    }
    public void setNome(String nome) {
    	this.nome = nome;
    }
    public String getCognome() {
    	return cognome;
    }
    public void setCognome(String cognome) {
    	this.cognome = cognome;
    }
    public char getRuolo() {
    	return ruolo;
    }
    public void setRuolo(char ruolo) {
    	this.ruolo = ruolo;
    }
public String getPassword() {
	
	return password;
                            } 
public void setPassword(String password) {
	
                                   }
public boolean login(){   

	
	 return UtenteRegistratoDAO.getInstance().utenteEsiste(this);
          }

public int getIdUtenteRegistrato() {
	return idUtenteRegistrato;
}
public void setIdUtenteRegistrato(int idUtenteRegistrato) {
	this.idUtenteRegistrato = idUtenteRegistrato;
}



}