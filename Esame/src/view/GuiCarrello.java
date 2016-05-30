 package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;
import java.util.prefs.PreferenceChangeEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ActionListener.AscoltatoreDipendente;
import ActionListener.JTableListener;
import model.Carrello;
import model.Prodotto;

public class GuiCarrello extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public GuiCarrello(){
		super("Carrello");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		AscoltatoreDipendente lis = new AscoltatoreDipendente(this);
		JTableListener list = new JTableListener();
		
		//OBJECT
		
		JLabel avviso = new JLabel();
		JButton rimuovi = new JButton("Rimuovi dal carrello");
		JButton modifica = new JButton("Modifica quantità");
		JButton conferma = new JButton("Conferma Ordine");
		JButton catalogo = new JButton("Torna al Catalogo");
		conferma.addActionListener(list);
		conferma.setActionCommand("conferma");
		catalogo.addActionListener(lis);
		catalogo.setActionCommand("catalogo");
		
		
		
	  	//DTM E JTABLE
		
		DefaultTableModel dtm = new DefaultTableModel(0,0){
			@Override 
			public boolean isCellEditable(int row, int column) {
				 return false;
		    }
		};
		
		JTable table = new JTable() {
		    public Dimension getPreferredScrollableViewportSize() {
		        return new Dimension(850, 250);
		    }
		};
		//LISTENER
		
		JTableListener lisRimuovi = new JTableListener(dtm,table);
		JTableListener lisModifica = new JTableListener(table);
		rimuovi.addActionListener(lisRimuovi);
		rimuovi.setActionCommand("rimuovi");
		modifica.addActionListener(lisModifica);
		modifica.setActionCommand("modifica");
		
		//DATI JTABLE	
		String columnNames1[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilita "
					,"Prezzo","Produttore","Quantità selezionata"};
		dtm.setColumnIdentifiers(columnNames1);
	    
		
		Vector<String[]> lista = Carrello.getInstance().listaProdotti();
		
		for (int i=0;i<lista.size();i++){
			dtm.addRow(lista.get(i));
		}
		
       
		//IMPOSTAZIONI JTABLE
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  	table.setModel(dtm);
	  	table.setRowHeight(30);
		
	  	
		//PANEL 
	  	JScrollPane scrollPane = new JScrollPane(table);
		JPanel nord = new JPanel();
		JPanel center = new JPanel();
		JPanel sud = new JPanel();
	    
		
		nord.add(avviso);
	  	center.add(scrollPane);
	  	sud.setLayout(new FlowLayout());
	  	sud.add(catalogo);
	  	sud.add(modifica);
	  	sud.add(rimuovi);
	  	sud.add(conferma);
	  	
	  	    c.add(nord,BorderLayout.NORTH);
		    c.add(center, BorderLayout.CENTER);
		    c.add(sud,BorderLayout.SOUTH);
		    
		    setSize(900,550);
		    setDefaultCloseOperation(HIDE_ON_CLOSE);
		    setVisible(true);
	}
	
}
