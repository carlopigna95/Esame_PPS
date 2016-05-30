package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

import dao.DipendenteDAO;
import model.Dipendente;
import view.GuiDipendente;
import view.GuiCarrello;


public class AscoltatoreDipendente implements ActionListener{
	JFrame finestra = new JFrame();
	GuiDipendente dipFinestra;

	public AscoltatoreDipendente(JFrame finestra){
		super();
		this.finestra = finestra;
	}
	public AscoltatoreDipendente(GuiDipendente dipFinestra){
		super();
		this.dipFinestra = dipFinestra;
	}
	public AscoltatoreDipendente() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
			if(arg0.getActionCommand().equals("carrello")){
			dipFinestra.setVisible(false);
			GuiCarrello app = new GuiCarrello();
			
		}
			else if(arg0.getActionCommand().equals("catalogo")){
				finestra.setVisible(false);
                GuiDipendente app = new GuiDipendente();
			}
		
		
	}

}
