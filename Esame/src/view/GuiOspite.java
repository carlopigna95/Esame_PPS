package view;


import java.sql.*;

import java.util.Vector;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ActionListener.Ascoltatore;
import dao.OspiteDAO;


public class GuiOspite extends JFrame {
	
  
	private static final long serialVersionUID = 1L;
	Ascoltatore listener = new Ascoltatore(this);

	public GuiOspite(){
		super("Benvenuto");
		Container c = getContentPane();
		JPanel nord1 = new JPanel();
		JPanel sud1 = new JPanel();
		JPanel nord2 = new JPanel();
		JPanel sud2 = new JPanel();
		JPanel primo = new JPanel();  //prima tab
		JPanel secondo = new JPanel(); //seconda tab
	  	JButton esci1 = new JButton("Esci");
	  	JButton esci2 = new JButton("Esci");

	  	JTabbedPane tab = new JTabbedPane();
	  	final OspiteDAO ospite = OspiteDAO.getInstance();
	  	
	  	//--------------TAB MAGAZZINO 1--------------
	  	primo.setLayout(new BorderLayout());
	  	DefaultTableModel dtm1 = new DefaultTableModel(0, 0){
			@Override 
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};  //serve per allocare dinamicamente la JTable
		
	  	final JTable table1 = new JTable();
	  	table1.setRowSelectionAllowed(false);
	  	String columnNames1[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilita" ,"Prezzo"};
	  	dtm1.setColumnIdentifiers(columnNames1);
	  	table1.setModel(dtm1);
	  	
	  	for(int i=0;i<ospite.GeneraCatalogo1().size();i++){ //Allocazione dinamica della JTable
	  		dtm1.addRow(ospite.GeneraCatalogo1().get(i));
	  		
	  		
	  	}  
	  	table1.getTableHeader().setReorderingAllowed(false); 
	  	table1.setRowHeight(30);
	  	table1.setGridColor(Color.MAGENTA);  // POI I COLORI LI SCEGLIAMO CIVILI.
	  	JScrollPane scrollPane1 = new JScrollPane(table1);
	    add(primo);
	    primo.add(nord1,BorderLayout.NORTH);
	    nord1.setLayout(new FlowLayout());
	    nord1.add(scrollPane1);
	    primo.add(sud1, BorderLayout.SOUTH);
	    sud1.add(esci1);
	    esci1.addActionListener(listener);
	    esci1.setActionCommand("esci");
	    
	    //-------------TAB MAGAZZINO 2----------------
	    secondo.setLayout(new BorderLayout());
		DefaultTableModel dtm2 = new DefaultTableModel(0, 0)  //serve per allocare dinamicamente la JTable
		{
			@Override 
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		JTable table2 = new JTable();
	  	String columnNames2[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilita" ,"Prezzo €"};
	  	dtm2.setColumnIdentifiers(columnNames2);
	  	table2.setModel(dtm2);
	  	
	  	for(int i=0;i<ospite.GeneraCatalogo2().size();i++){ //Allocazione dinamica della JTable
	  		dtm2.addRow(ospite.GeneraCatalogo2().get(i));
	  	}  
	  	table2.getTableHeader().setReorderingAllowed(false); 
	  	table2.setRowSelectionAllowed(false);
	  	table2.setRowHeight(30);
	  	table2.setGridColor(Color.MAGENTA);  // POI I COLORI LI SCEGLIAMO CIVILI.
	  	JScrollPane scrollPane2 = new JScrollPane(table2);
	  	
	    c.add(secondo);
	    secondo.add(nord2,BorderLayout.NORTH);
	    nord2.setLayout(new FlowLayout());
	    nord2.add(scrollPane2);
	    secondo.add(sud2, BorderLayout.SOUTH);
	    sud2.add(esci2);
	    esci2.addActionListener(listener);
	    esci2.setActionCommand("esci");
	    
	    
	    tab.add("Maglie", primo);
	    tab.add("Lecce", secondo);
	    c.add(tab);
	    
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(700,600);
	    setVisible(true);
	    
	    
	  }
	
	}
