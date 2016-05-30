package ActionListener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

import model.CapoProgetto;
import model.Sessione;
import view.GuiCapoProgetto;
import view.SpesaPerDipendente;
import view.SpesaPerProgetto;


public class AscoltatoreCapoProgetto implements ActionListener{
	JFrame finestra = new JFrame();
	JTable table = new JTable();
	
	
	
	public AscoltatoreCapoProgetto(JFrame finestra){
		this.finestra = finestra;
	}
	
	public AscoltatoreCapoProgetto(JFrame finestra, JTable table){
		this.finestra = finestra;
		this.table = table;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		
		if(arg0.getActionCommand().equals("stampa")){

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String data = dateFormat.format(date);
			MessageFormat up = new MessageFormat("Rapporto spesa per progetto");	
			MessageFormat down = new MessageFormat(data);
			
			try {
				table.print(JTable.PrintMode.FIT_WIDTH, up,down);
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		 
		 else if(arg0.getActionCommand().equals("per_progetto")){
			 finestra.setVisible(false);
			 SpesaPerProgetto win = new SpesaPerProgetto();
				
			}
		
		 else if(arg0.getActionCommand().equals("per_dipendente")){
			 finestra.setVisible(false);
			 SpesaPerDipendente win = new SpesaPerDipendente();
				
			}
		 else if(arg0.getActionCommand().equals("ritorna_opzioni")){
			 finestra.setVisible(false);
			 GuiCapoProgetto win = new GuiCapoProgetto();
		 }
			 
		 }
		 
	}
		

