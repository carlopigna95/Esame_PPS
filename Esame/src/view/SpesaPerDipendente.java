package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ActionListener.AscoltatoreCapoProgetto;
import ActionListener.JTableListener;
import Business.CapoProgettoBusiness;;

public class SpesaPerDipendente extends JFrame {
	
	Color BlueFacebook = new Color(59,89,152);
	Color MediumBlueFacebook = new Color(109, 132, 180);
	
	public SpesaPerDipendente(){
		super("Spesa per Dipendente");
		Container c = getContentPane();
		JPanel nord = new JPanel();
		JPanel sud = new JPanel();
		JButton opzioni = new JButton("Torna alle opzioni");
		JButton stampa = new JButton("Stampa");
		
		c.setLayout(new BorderLayout());
		c.add(nord, BorderLayout.NORTH);
		c.add(sud, BorderLayout.SOUTH);
		
		//--------CREAZIONE TABLE------------
		DefaultTableModel dtm = new DefaultTableModel(0,0){
			public boolean isCellEditable(int row,int column){
				return false;
				}
		};
		final JTable table = new JTable(){
			public Dimension getPreferredScrollableViewportSize(){
		  		return new Dimension(400,100);
			}
		};
		table.setRowSelectionAllowed(true);
		String columnNames[] = new String[] { "Nome", "Cognome", "Spesa Totale €"};
		dtm.setColumnIdentifiers(columnNames);
		table.setModel(dtm);
		JTableListener lis = new JTableListener(table);
		for(int i=0;i<CapoProgettoBusiness.getInstance().TotaleSpesaDipendente().size();i++){
			dtm.addRow(CapoProgettoBusiness.getInstance().TotaleSpesaDipendente().get(i));
			}
		table.setSize(new Dimension(400,100));
		table.getTableHeader().setReorderingAllowed(false);
	  	table.setModel(dtm);
	  	table.setRowHeight(20);
	  	
		//-------------------------------
		
		JScrollPane ScrollPaneTable = new JScrollPane(table);
		
		AscoltatoreCapoProgetto listener = new AscoltatoreCapoProgetto(this);
		AscoltatoreCapoProgetto listener2 = new AscoltatoreCapoProgetto(this,table,dtm);

		nord.add(ScrollPaneTable);
		sud.setLayout(new GridLayout(1,2,5,5));	
		sud.add(stampa);
		stampa.addActionListener(listener2);
		stampa.setActionCommand("stampa_dipendente");
		sud.add(opzioni);
		opzioni.addActionListener(listener);
		opzioni.setActionCommand("ritorna_opzioni");
		
		//Colori e grafica
		c.setBackground(BlueFacebook);
		nord.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		nord.setOpaque(true);
		nord.setBackground(BlueFacebook);
		sud.setOpaque(true);
		sud.setBackground(BlueFacebook);
		sud.setBorder(new LineBorder(Color.BLACK,1));
		ScrollPaneTable.getViewport().setBackground(MediumBlueFacebook);
		ScrollPaneTable.setBorder(new LineBorder(Color.BLACK,1));
		
		
		
		

		
		setVisible(true);
		setSize(500, 300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
		

		
}


