 package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ActionListener.AscoltatoreDipendente;
import ActionListener.JTableListener;
import model.Carrello;

public class GuiCarrello extends JFrame {

	private static final long serialVersionUID = 1L;
	Color BlueFacebook = new Color(59,89,152);
	Color MediumBlueFacebook = new Color(109, 132, 180);

	
	public GuiCarrello(){
		super("Carrello");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		AscoltatoreDipendente lis = new AscoltatoreDipendente(this);

		
		
		
		//OBJECT
		
		JLabel avviso = new JLabel();
		JButton rimuovi = new JButton("Rimuovi dal carrello");
		JButton modifica = new JButton("Modifica quantità");
		JButton conferma = new JButton("Conferma Ordine");
		JButton catalogo = new JButton("Torna al Catalogo");
		
		
		
		
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
		
		
		
		//DATI JTABLE	
		String columnNames1[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilita "
					,"Prezzo","Produttore","Quantità selezionata"};
		dtm.setColumnIdentifiers(columnNames1);
	    
		
		Vector<String[]> lista = Carrello.getInstance().listaProdotti();
		double prezzoReale;
		double prezzo;
		int disponibilità;
		double SpesaTotale = 0;
		
		for (int i=0;i<lista.size();i++){
			dtm.addRow(lista.get(i));
			//Aggiornamento del prezzo 
			prezzo = Double.parseDouble(lista.get(i)[4]);
			disponibilità = Integer.parseInt(lista.get(i)[6]);
			prezzoReale = prezzo*disponibilità;
			//per troncare il double alla seconda cifra decimale
			prezzoReale = Math.floor(prezzoReale*100);
			prezzoReale = prezzoReale/100;
			SpesaTotale = SpesaTotale + prezzoReale; 
			SpesaTotale = Math.floor(SpesaTotale*100);
			SpesaTotale = SpesaTotale/100;
			String PrezzoString = Double.toString(prezzoReale);
			dtm.setValueAt(PrezzoString,i,4);
			
		}
		JLabel SpesaTot = new JLabel("Totale: € "+SpesaTotale);
		
		JTableListener listStampa = new JTableListener(dtm,table,SpesaTotale);

		//LISTENER
		conferma.addActionListener(listStampa);
		conferma.setActionCommand("conferma");
		catalogo.addActionListener(lis);
		catalogo.setActionCommand("catalogo");
		JTableListener lisRimuovi = new JTableListener(dtm,table);
		JTableListener lisModifica = new JTableListener(table);
		rimuovi.addActionListener(lisRimuovi);
		rimuovi.setActionCommand("rimuovi");
		modifica.addActionListener(lisModifica);
		modifica.setActionCommand("modifica");
		
		
       
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
	  	center.add(SpesaTot);
	  	sud.setLayout(new FlowLayout());
	  	sud.add(catalogo);
	  	sud.add(modifica);
	  	sud.add(rimuovi);
	  	sud.add(conferma);
	  	
	  	c.add(nord,BorderLayout.NORTH);
	  	c.add(center, BorderLayout.CENTER);
	  	c.add(sud,BorderLayout.SOUTH);
	  	
	  	//Colori e grafica
	  	c.setBackground(BlueFacebook);
		  nord.setOpaque(true);
		  nord.setBackground(BlueFacebook);
		  sud.setOpaque(true);
		  sud.setBackground(BlueFacebook);
		  center.setOpaque(true);
		  center.setBackground(BlueFacebook);
		  sud.setBackground(BlueFacebook);
		  scrollPane.getViewport().setBackground(MediumBlueFacebook);
		  scrollPane.setBorder(new LineBorder(Color.BLACK,1));
		  SpesaTot.setForeground(Color.WHITE);

		    
		    
	  	setSize(900,550);
	  	setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	setVisible(true);
	}
	
}
