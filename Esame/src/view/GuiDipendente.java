package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreDipendente;
import ActionListener.JTableListener;
import dao.DipendenteDAO;
import model.Sessione;
import model.Sessione;
import model.Sessione;
import model.UtenteRegistrato;

public class GuiDipendente extends JFrame{
	Color BlueFacebook = new Color(59,89,152);
	Color MediumBlueFacebook = new Color(109, 132, 180);
	UtenteRegistrato utente = Sessione.getInstance().session.get("utente_corrente");
	private String nome = utente.getNome();
	private String cognome = utente.getCognome();
	
	
	
	public GuiDipendente(){
		super("Dipendente");
		Ascoltatore listener = new Ascoltatore(this);
		AscoltatoreDipendente listener_dip = new AscoltatoreDipendente(this);
		Container c = getContentPane();
		
		JPanel principale = new JPanel();
		JPanel utente = new JPanel(); 
		JPanel primo = new JPanel();
		JPanel secondo = new JPanel();   
		JPanel nord1 = new JPanel();
		JPanel sud1 = new JPanel();
		JPanel nord2 = new JPanel();
		JPanel sud2 = new JPanel();
		JTabbedPane tab = new JTabbedPane();
		JLabel accesso = new JLabel(nome+" "+cognome);
	
	    
		JButton carrello1 = new JButton("Vai al Carrello");
		JButton carrello2 = new JButton("Vai al Carrello");
		JButton aggiungi1 = new JButton("Aggiungi al carrello");
		JButton aggiungi2 = new JButton("Aggiungi al carrello");
		JButton logout1 = new JButton("Log-Out");
		JButton logout2 = new JButton("Log-Out");
		JButton esci1 = new JButton("Esci");
		JButton esci2 = new JButton("Esci");
		
		
		
		DipendenteDAO dipendente = DipendenteDAO.getInstance();
		
		//------TAB 1----------------
		primo.setLayout(new BorderLayout());
		DefaultTableModel dtm1 = new DefaultTableModel(0,0){
			@Override 
			public boolean isCellEditable(int row, int column) {
				if (column == 6)
		          {return true;}
				else return false;
		    }
		};
		JTable table1 = new JTable() {
		    public Dimension getPreferredScrollableViewportSize() {
		        return new Dimension(850, 250);
		    }
		};
		table1.setRowSelectionAllowed(true);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String columnNames1[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilita "
												,"Prezzo","Produttore"};
		dtm1.setColumnIdentifiers(columnNames1);
	  	table1.setModel(dtm1);
	  	table1.setRowHeight(30);
	  	table1.getTableHeader().setReorderingAllowed(false); 
		JTableListener lis1 = new JTableListener(table1);
		//POPOLA LA JTABLE
		
	  	for(int i=0;i<dipendente.GeneraCatalogo1().size();i++){ //Allocazione dinamica della JTable
	  	    dtm1.addRow(dipendente.GeneraCatalogo1().get(i));
	  	}
	  	
	  	
	  	//PANNELLI
	  	c.add(utente, BorderLayout.NORTH);
	  	utente.add(accesso);
	  	c.add(principale);
	  	JScrollPane scrollPane1 = new JScrollPane(table1);
		scrollPane1.getViewport().setBackground(MediumBlueFacebook);
	  	primo.add(nord1,BorderLayout.NORTH);
	  	nord1.add(scrollPane1);
	  	primo.add(sud1, BorderLayout.SOUTH);
	  	sud1.setLayout(new FlowLayout());
	  	sud1.add(carrello1);
	  	carrello1.addActionListener(listener_dip);
		carrello1.setActionCommand("carrello");
		sud1.add(aggiungi1);
		aggiungi1.addActionListener(lis1);
		aggiungi1.setActionCommand("aggiungi");
	  	sud1.add(logout1);
	  	logout1.addActionListener(listener);
		logout1.setActionCommand("logout");
		sud1.add(esci1);
		esci1.addActionListener(listener);
		esci1.setActionCommand("esci");
	  
	  	//----------TAB 2-----------------
	  	secondo.setLayout(new BorderLayout());
		DefaultTableModel dtm2 = new DefaultTableModel(0, 0){
			
				@Override 
				public boolean isCellEditable(int row, int column) {
					if (column == 6)
			          {return true;}
					else return false;
			    
			    }
			};
		JTable table2 = new JTable(){
		    public Dimension getPreferredScrollableViewportSize() {
		        return new Dimension(850, 250);
		    }
		};
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.setRowSelectionAllowed(true);
		String columnNames2[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilità" ,"Prezzo"
				                               ,"Produttore"};
		dtm2.setColumnIdentifiers(columnNames2);
	  	table2.setModel(dtm2);
	  	JTableListener lis2 = new JTableListener(table2);
	  	
	  	//POPOLA LA JTABLE
	  	
	  	for(int i=0;i<dipendente.GeneraCatalogo2().size();i++){ //Allocazione dinamica della JTable
	  		dtm2.addRow(dipendente.GeneraCatalogo2().get(i));
	  	}  
	  	
	  	
	  	table2.getTableHeader().setReorderingAllowed(false); 
	  	table2.setRowHeight(30);
	  	
	  	//PANNELLI
	  	
	  	JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.getViewport().setBackground(MediumBlueFacebook);
	  	secondo.setLayout(new BorderLayout());
	  	secondo.add(nord2,BorderLayout.NORTH);
	  	nord2.add(scrollPane2);
	  	secondo.add(sud2, BorderLayout.SOUTH);
	  	sud2.setLayout(new FlowLayout());
	  	sud2.add(carrello2);
	  	carrello2.addActionListener(listener_dip);
		carrello2.setActionCommand("carrello");
		sud2.add(aggiungi2);
		aggiungi2.addActionListener(lis2);
		aggiungi2.setActionCommand("aggiungi");

	  	sud2.add(logout2);
	  	logout2.addActionListener(listener);
		logout2.setActionCommand("logout");
		sud2.add(esci2);
		esci2.addActionListener(listener);
		esci2.setActionCommand("esci");
	    
	
	  	
	  	 tab.add("Maglie", primo);
		 tab.add("Lecce", secondo);
		 principale.add(tab);
		 
		//Colori e grafica
			c.setBackground(BlueFacebook);
			primo.setOpaque(true);
			primo.setBackground(BlueFacebook);
			secondo.setOpaque(true);
			secondo.setBackground(BlueFacebook);
			nord1.setOpaque(true);
			nord1.setBackground(BlueFacebook);
			sud1.setOpaque(true);
			sud1.setBackground(BlueFacebook);
			nord2.setOpaque(true);
			nord2.setBackground(BlueFacebook);
			sud2.setOpaque(true);
			sud2.setBackground(BlueFacebook);
			accesso.setForeground(Color.WHITE);
			principale.setBackground(BlueFacebook);
			principale.setPreferredSize(getPreferredSize());
			utente.setOpaque(true);
			utente.setBackground(BlueFacebook);
			primo.setBorder(new LineBorder(Color.black, 2, true));
			secondo.setBorder(new LineBorder(Color.black, 2, true));
			scrollPane1.getViewport().setBackground(MediumBlueFacebook);
			scrollPane1.setBorder(new LineBorder(Color.BLACK,1));
			scrollPane2.getViewport().setBackground(MediumBlueFacebook);
			scrollPane2.setBorder(new LineBorder(Color.BLACK,1));
			
	    setSize(900,450);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
	    
	}
	}