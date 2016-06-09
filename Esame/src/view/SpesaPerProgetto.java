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
import Business.CapoProgettoBusiness;

public class SpesaPerProgetto extends JFrame {
	
	AscoltatoreCapoProgetto listener = new AscoltatoreCapoProgetto(this);
	AscoltatoreCapoProgetto listener2 = new AscoltatoreCapoProgetto(this,TableSpesaProgetto());
	Color BlueFacebook = new Color(59,89,152);
	Color MediumBlueFacebook = new Color(109, 132, 180);
	
	
	public SpesaPerProgetto(){
		super("Spesa per Progetto");
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
		
		//Colori e grafica
		c.setBackground(BlueFacebook);
		nord.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		nord.setOpaque(true);
		nord.setBackground(BlueFacebook);
		sud.setOpaque(true);
		sud.setBackground(BlueFacebook);
		sud.setBorder(new LineBorder(Color.BLACK,1));
		scrollPane.getViewport().setBackground(MediumBlueFacebook);
		scrollPane.setBorder(new LineBorder(Color.BLACK,1));
		

		
	
		
		setVisible(true);
		setSize(500,300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		
		
	}
	
	public JTable TableSpesaProgetto(){
		DefaultTableModel dtm = new DefaultTableModel(0,0){private static final long serialVersionUID = 1L;

			@Override 
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
			JTable table = new JTable(){
			public Dimension getPreferredScrollableViewportSize(){
		  		return new Dimension(400,100);
			}
			};
	
		String columnNames[] = new String[] { "Nome Progetto","Spesa Totale €"};
		dtm.setColumnIdentifiers(columnNames);
		table.setModel(dtm);
		JTableListener lis = new JTableListener(table);
		for(int i=0;i<CapoProgettoBusiness.getInstance().TotaleSpesaProgetto().size();i++){
			dtm.addRow(CapoProgettoBusiness.getInstance().TotaleSpesaProgetto().get(i));
			}
		table.setSize(new Dimension(400,100));
		table.getTableHeader().setReorderingAllowed(false);
	  	table.setModel(dtm);
	  	table.setRowHeight(30);
	  	return table;
	}

}
	
