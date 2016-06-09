package view;


import java.sql.*;

import java.util.Vector;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
	final OspiteDAO ospite = OspiteDAO.getInstance();
	Ascoltatore listener = new Ascoltatore(this);
	Color BlueFacebook = new Color(59,89,152);
	Color MediumBlueFacebook = new Color(109, 132, 180);
    
	public GuiOspite(){
		super("Benvenuto");
		Container c = getContentPane();
		c.setBackground(BlueFacebook);
		JPanel nord1 = new JPanel();
		JPanel sud1 = new JPanel();
		JPanel nord2 = new JPanel();
		JPanel sud2 = new JPanel();
		JPanel primo = new JPanel();  //prima tab
		JPanel secondo = new JPanel(); //seconda tab
		nord1.setOpaque(true);
		sud1.setOpaque(true);
		primo.setOpaque(true);
	  	JButton esci1 = new JButton("Esci");
	  	JButton esci2 = new JButton("Esci");
	  	JButton accedi1 = new JButton("Accedi");
	  	JButton accedi2 = new JButton("Accedi");

	  	JTabbedPane tab = new JTabbedPane();
	  	
	  	
	  	//--------------TAB MAGAZZINO 1--------------
	  	primo.setLayout(new BorderLayout());
	  	
	  	JScrollPane scrollPane1 = new JScrollPane(creatore_table1());
	  	scrollPane1.setBorder(new LineBorder(Color.white));
	  	scrollPane1.setBorder(new LineBorder(Color.BLACK,1));
	  	scrollPane1.getViewport().setBackground(MediumBlueFacebook);
	    add(primo);
	    primo.add(nord1,BorderLayout.NORTH);
	    nord1.setLayout(new FlowLayout());
	    nord1.add(scrollPane1);
	    primo.add(sud1, BorderLayout.SOUTH);
	    sud1.setLayout(new GridLayout(1,2,5,5));
	    sud1.add(esci1);
	    
	    esci1.addActionListener(listener);
	    esci1.setActionCommand("esci");
	    sud1.add(accedi1);
	    accedi1.addActionListener(listener);
	    accedi1.setActionCommand("accedi");
	    primo.setBackground(BlueFacebook);
	    nord1.setBackground(BlueFacebook);
	    sud1.setBackground(BlueFacebook);
	    
	    //-------------TAB MAGAZZINO 2----------------
	    secondo.setLayout(new BorderLayout());
	  	JScrollPane scrollPane2 = new JScrollPane(creatore_table2());
	  	scrollPane2.setBorder(new LineBorder(Color.white));
	  	scrollPane2.setBorder(new LineBorder(Color.BLACK,1));
	  	scrollPane2.getViewport().setBackground(MediumBlueFacebook);
	  	
	    c.add(secondo);
	    secondo.add(nord2,BorderLayout.NORTH);
	    nord2.setLayout(new FlowLayout());
	    nord2.add(scrollPane2);
	    secondo.add(sud2, BorderLayout.SOUTH);
	    sud2.setLayout(new GridLayout(1,2,5,5));
	    sud2.add(esci2);
	    esci2.addActionListener(listener);
	    esci2.setActionCommand("esci");
	    sud2.add(accedi2);
	    accedi2.addActionListener(listener);
	    accedi2.setActionCommand("accedi");
	    secondo.setBackground(BlueFacebook);
	    nord2.setBackground(BlueFacebook);
	    sud2.setBackground(BlueFacebook);
	    
	    
	    tab.add("Maglie", primo);
	    tab.add("Lecce", secondo);
	   
	    c.add(tab);
	    
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(700,600);
	    setVisible(true);
	    
	    
	  }
	/////-------METODI JTABLE------
	
	//JTABLE TAB1
	JTable creatore_table1(){
		DefaultTableModel dtm = new DefaultTableModel(0, 0){
			
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		}; 
		final JTable table = new JTable() {
			public Dimension getPreferredScrollableViewportSize(){
				return new Dimension(600,200);
			}
		};
	  	String columnNames[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilita" ,"Prezzo €"};
	  	dtm.setColumnIdentifiers(columnNames);
	  	table.setModel(dtm);
	  	
	  	for(int i=0;i<ospite.GeneraCatalogo1().size();i++){ //Allocazione dinamica della JTable
	  		dtm.addRow(ospite.GeneraCatalogo1().get(i));
	  	}  
	  	table.getTableHeader().setReorderingAllowed(false); 
	  	table.setRowSelectionAllowed(false);
	  	table.setRowHeight(30);
	  	return table;
		
	}
	
	//JTABLE TAB 2
	JTable creatore_table2(){
		DefaultTableModel dtm = new DefaultTableModel(0, 0){
			
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		}; 
		final JTable table = new JTable() {
			public Dimension getPreferredScrollableViewportSize(){
				return new Dimension(600,200);
			}
		};
	  	String columnNames[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilita" ,"Prezzo €"};
	  	dtm.setColumnIdentifiers(columnNames);
	  	table.setModel(dtm);
	  
	  	
	  	for(int i=0;i<ospite.GeneraCatalogo2().size();i++){ //Allocazione dinamica della JTable
	  		dtm.addRow(ospite.GeneraCatalogo2().get(i));
	  	}  
	  	table.getTableHeader().setReorderingAllowed(false); 
	  	table.setRowSelectionAllowed(false);
	  	table.setRowHeight(30);
	  	return table;
		
	}
	
	}
