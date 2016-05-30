package ActionListener;
import model.Prodotto;
import model.Sessione;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;

import Business.UtenteRegistratoBusiness;
import dao.OrdineDAO;
import dao.ProgettoDAO;
import dao.UtenteRegistratoDAO;
import model.UtenteRegistrato;
import view.GuiCapoProgetto;
import view.GuiDipendente;
import view.GuiMagazziniere;
import view.GuiLogin;

public class AscoltatoreLogin implements ActionListener{
 private GuiLogin loginWindow;
	
	public AscoltatoreLogin(GuiLogin loginWindow) {
		super();
		this.loginWindow = loginWindow;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
			
			String username = loginWindow.username.getText();
			String password = loginWindow.password.getText();
			boolean utenteEsiste = UtenteRegistratoBusiness.getInstance().verificaLogin(username,password);
			
			if(utenteEsiste == true){
				UtenteRegistrato u = Sessione.getInstance().session.get("utente_corrente");
				char ruolo = u.getRuolo();
				JOptionPane.showMessageDialog(null, "Benvenuto, "+username+"!");
				switch(ruolo){
					case 'C' :	/*Sessione.getInstance().session.put("utente_corrente",UtenteRegistratoBusiness.
								getInstance().sessione(username, password,idUtente));*/
					GuiCapoProgetto winCP = new GuiCapoProgetto();
					loginWindow.setVisible(false);
					
					break;
					case 'D' : 	/*Sessione.getInstance().session.put("utente_corrente",UtenteRegistratoBusiness.
								getInstance().sessione(username, password,idUtente));*/
					GuiDipendente winD = new GuiDipendente();
					loginWindow.setVisible(false);
					break;
					case 'M' :	/*Sessione.getInstance().session.put("utente_corrente",UtenteRegistratoBusiness.
								getInstance().sessione(username, password,idUtente));*/
					GuiMagazziniere winM = new GuiMagazziniere();
					loginWindow.setVisible(false);
					break;
				}
				
			}
			    
			else
				JOptionPane.showMessageDialog(null, "Utente non riconosciuto");
			
			
			
	
			 
		
	}  

}
