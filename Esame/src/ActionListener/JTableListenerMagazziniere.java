package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Ordine;
import view.GuiMagazziniere;
import view.RichiestePendenti;

public class JTableListenerMagazziniere implements ActionListener {
	static JTable table;
    DefaultTableModel dtm;
    GuiMagazziniere magFinestra;
    
    public JTableListenerMagazziniere(DefaultTableModel dtm,JTable table, GuiMagazziniere magFinestra) {
    	super();
		JTableListenerMagazziniere.table = table;
		dtm = (DefaultTableModel) table.getModel();
		this.magFinestra = magFinestra;
	}
    
    public JTableListenerMagazziniere(JTable table) {
		super();
		this.table = table;
		table.getModel();
	}

	public void actionPerformed(ActionEvent arg0) {
		
		  if(arg0.getActionCommand().equals("OrdiniPendenti")){
			 
			 try{int row = table.getSelectedRow();
			 table.getSelectedRow();
				 int codOrdine = Integer.parseInt((String)table.getValueAt(row, 2));
				 //Viene creato un Hashmap che registra il codice dell'ordine
				 Ordine.getInstance().sessionOrdine.put("Ordine_corrente", codOrdine);

				 magFinestra.setVisible(false);
				 RichiestePendenti win = new RichiestePendenti(codOrdine);
				 
			 }
			 catch(ArrayIndexOutOfBoundsException e) {
				 JOptionPane.showMessageDialog(table, "Selezionare una riga");
			 }
			 
			 
		 }
	
		
	}

}
