package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Business.UtenteRegistratoBusiness;
import dao.UtenteRegistratoDAO;
import model.Sessione;
import model.UtenteRegistrato;
import view.GuiOspite;
import view.GuiLogin;

public class Ascoltatore implements ActionListener{
	
	JFrame finestra = new JFrame();
	

	public Ascoltatore(JFrame finestra){
		this.finestra = finestra;
	}
	public Ascoltatore(GuiLogin finestra){
		super();
		this.finestra = finestra; 
	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("accediOspite")){
			
			GuiOspite app = new GuiOspite(); //FA PARTIRE LA FINESTRA DI GuiOpsite 
			finestra.setVisible(false); // CHIUDE LA FINESTRA DI LOGIN
			
		}
		else if (arg0.getActionCommand().equals("esci")){
			System.exit(0);
				
		}
		
	
		else if(arg0.getActionCommand().equals("logout")){
			Sessione.getInstance().session.remove("utente_corrente");
			GuiLogin app = new GuiLogin();
			finestra.setVisible(false);
			
		}
		
		else if(arg0.getActionCommand().equals("accedi")){
			GuiLogin win = new GuiLogin();
			finestra.setVisible(false);
		}
		
		
	}
	
	

}
