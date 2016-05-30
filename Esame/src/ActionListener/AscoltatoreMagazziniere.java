package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Business.MagazziniereBusiness;
import dao.MagazziniereDAO;
import model.Ordine;
import view.GuiMagazziniere;
import view.RichiestePendenti;



public class AscoltatoreMagazziniere implements ActionListener{
	
	JFrame finestra = new JFrame();
	
	public AscoltatoreMagazziniere(JFrame finestra){
		this.finestra = finestra;
	}
	
			
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("evadi")){
			MagazziniereBusiness magazziniere = new MagazziniereBusiness();
			magazziniere.EvadiOrdine();
			JOptionPane.showMessageDialog(finestra, "L'ordine è stato evaso correttamente!");
			finestra.setVisible(false);
			GuiMagazziniere win = new GuiMagazziniere();
			
		
		}
		
		else if (arg0.getActionCommand().equals("chiudi")){
			finestra.setVisible(false);
			Ordine.getInstance().sessionOrdine.clear();
			GuiMagazziniere win = new GuiMagazziniere();
			
		}

		
	}

}


