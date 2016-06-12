package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Sessione;
import model.UtenteRegistrato;
import view.GuiCapoProgetto;
import view.SpesaPerDipendente;
import view.SpesaPerProgetto;


public class AscoltatoreCapoProgetto implements ActionListener{
	JFrame finestra = new JFrame();
	JTable table = new JTable();
	DefaultTableModel dtm = new DefaultTableModel();
	
	
	
	public AscoltatoreCapoProgetto(JFrame finestra){
		this.finestra = finestra;
	}
	
	public AscoltatoreCapoProgetto(JFrame finestra, JTable table){
		this.finestra = finestra;
		this.table = table;
		
	}
	public AscoltatoreCapoProgetto(JFrame finestra, JTable table, DefaultTableModel dtm){
		this.finestra = finestra;
		this.table = table;
		this.dtm = dtm;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		
		if(arg0.getActionCommand().equals("stampa_progetto")){

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String data = dateFormat.format(date);
			UtenteRegistrato utente = Sessione.getInstance().session.get("utente_corrente");
			MessageFormat up = new MessageFormat("Rapporto spesa per progetto");	
			MessageFormat down = new MessageFormat(utente.getNome()+" "+utente.getCognome()+" - "+data);
			
			try {
				table.print(JTable.PrintMode.FIT_WIDTH, up,down);
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		if(arg0.getActionCommand().equals("stampa_dipendente")){

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String data = dateFormat.format(date);
			UtenteRegistrato utente = Sessione.getInstance().session.get("utente_corrente");
			MessageFormat up = new MessageFormat("Rapporto spesa per dipendente");	
			MessageFormat down = new MessageFormat(utente.getNome()+" "+utente.getCognome()+" - "+data);
			
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
		

