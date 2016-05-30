package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.MagazziniereDAO;
import view.GuiMagazziniere;



public class AscoltatoreMagazziniere implements ActionListener{
	JFrame finestra = new JFrame();
	
	public AscoltatoreMagazziniere(JFrame finestra){
		this.finestra = finestra;
	}
			
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("rifornimento")){
		/*	Vector<String[]> vettore = MagazziniereDAO.getInstance().PocaDisponibilita1();
			for(int i=0;i<vettore.size();i++){
		
			String valore = JOptionPane.showInputDialog("Prodotto selezionato: "+vettore.get(i)[0]+".\n Inserire la quantità"
														+ " da rifornire");
			
			}  */
		
		}
		
	}

}


