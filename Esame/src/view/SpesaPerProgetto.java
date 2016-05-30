package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

import ActionListener.AscoltatoreCapoProgetto;
import ActionListener.JTableListener;
import Business.CapoProgettoBusiness;

public class SpesaPerProgetto extends JFrame {
	
	AscoltatoreCapoProgetto listener = new AscoltatoreCapoProgetto(this);
	AscoltatoreCapoProgetto listener2 = new AscoltatoreCapoProgetto(this,TableSpesaProgetto());
	
	public SpesaPerProgetto(){
		Container c = getContentPane(); 
		JPanel nord = new JPanel();
		JPanel sud = new JPanel();
		JButton opzioni = new JButton("Torna alle opzioni");
		JButton stampa = new JButton("Stampa");
		
		
		c.setLayout(new BorderLayout());
		c.add(nord, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(TableSpesaProgetto());
		nord.add(scrollPane);
		c.add(sud, BorderLayout.SOUTH);
		sud.setLayout(new GridLayout(1,2,5,5));
		sud.add(stampa);
		stampa.addActionListener(listener2);
		stampa.setActionCommand("stampa");
		sud.add(opzioni);
		opzioni.addActionListener(listener);
		opzioni.setActionCommand("ritorna_opzioni");
		
	
		
		setVisible(true);
		setSize(500,300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		
		
	}
	
	public JTable TableSpesaProgetto(){
		MyTableModel mtm = new MyTableModel();
		final JTable table = new JTable(){
			public Dimension getPreferredScrollableViewportSize(){
		  		return new Dimension(400,100);
			}
		};
		table.setRowSelectionAllowed(true);
		String columnNames[] = new String[] { "Nome Progetto","Spesa Totale €"};
		mtm.setColumnIdentifiers(columnNames);
		table.setModel(mtm);
		JTableListener lis = new JTableListener(table);
		for(int i=0;i<CapoProgettoBusiness.getInstance().TotaleSpesaProgetto().size();i++){
			mtm.addRow(CapoProgettoBusiness.getInstance().TotaleSpesaProgetto().get(i));
			}
		table.setSize(new Dimension(400,100));
	  	return table;
	}

}
