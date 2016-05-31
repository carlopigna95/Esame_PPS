package view;
import model.Magazziniere;
import model.Sessione;
import model.UtenteRegistrato;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreMagazziniere;
import ActionListener.JTableListener;
import ActionListener.JTableListenerMagazziniere;
import ActionListener.MyTableListener;
import Business.MagazziniereBusiness;
import dao.MagazziniereDAO;


public class GuiMagazziniere extends JFrame {
	 
	
	MagazziniereBusiness magazz_business = MagazziniereBusiness.getInstance();
	private static final long serialVersionUID = 1L;
	DefaultTableModel dtm = new DefaultTableModel();
	Ascoltatore listener = new Ascoltatore(this);
	MyTableListener TableListener = new MyTableListener(this);
	JTableListenerMagazziniere listenerDettagli = new JTableListenerMagazziniere(dtm,creatore_TableRichiestePendenti(),this);
	
	
	AscoltatoreMagazziniere listener_magazz = new AscoltatoreMagazziniere(this);
	Color BlueFacebook = new Color(59,89,152);
	Color MediumBlueFacebook = new Color(109, 132, 180);
	
	public GuiMagazziniere(){
		  super("Magazziniere");
		  Container c = getContentPane();
		  c.setBackground(BlueFacebook);
		  JTabbedPane tab = new JTabbedPane();
		  JPanel primo = new JPanel();
		  JPanel secondo= new JPanel();
		  JPanel nord1 = new JPanel();
		  JPanel sud1 = new JPanel();
		  JPanel nord2 = new JPanel();
		  JPanel sud2 = new JPanel();
		  primo.setOpaque(true);
		  primo.setBackground(BlueFacebook);
		  nord1.setOpaque(true);
		  nord1.setBackground(BlueFacebook);
		  sud1.setOpaque(true);
		  sud1.setBackground(BlueFacebook);
		  JButton logout1 = new JButton("Log-out");
		  JButton esci1 = new JButton("Esci");
		  JButton dettagli = new JButton("Dettagli Ordine");
		  JButton logout2 = new JButton("Log-out");
		  JButton esci2 = new JButton("Esci");
		  
		  
		  //---------PRIMO TAB--------------------------
		  
		  c.add(primo);
		  primo.setLayout(new BorderLayout());
		  primo.add(nord1,BorderLayout.NORTH);
		  primo.add(sud1,BorderLayout.SOUTH);
		  JScrollPane ScrollPaneRichieste = new JScrollPane(creatore_TableRichiestePendenti());
		  ScrollPaneRichieste.getViewport().setBackground(BlueFacebook);
		 
		  nord1.add(ScrollPaneRichieste);
		  sud1.setLayout(new GridLayout(1,3,5,5));
		  sud1.add(dettagli); 
		  dettagli.addActionListener(listenerDettagli);
		  dettagli.setActionCommand("OrdiniPendenti");
		  sud1.add(logout1);
		  logout1.addActionListener(listener);
		  logout1.setActionCommand("logout");
		  sud1.add(esci1);
		  esci1.addActionListener(listener);
		  esci1.setActionCommand("esci");
		  
		  //--------SECONDO TAB---------------------
		  c.add(secondo);
		  secondo.setLayout(new BorderLayout());
		  secondo.add(nord2,BorderLayout.NORTH);
		  secondo.add(sud2,BorderLayout.SOUTH);
		  nord2.add(creatore_JTable()); 
		 
		  sud2.add(logout2);
		  sud2.setLayout(new GridLayout(1,2,5,5));
		  logout2.addActionListener(listener);
		  logout2.setActionCommand("logout");
		  sud2.add(esci2);
		  esci2.addActionListener(listener);
		  esci2.setActionCommand("esci");
		
	        
	      c.add(tab);
	      tab.add("Richieste pendenti", primo);
	      tab.add("Rifornimento",secondo);
	      setDefaultCloseOperation(EXIT_ON_CLOSE);
		  setSize(650,550);
		  setVisible(true);
		  
		   }
	
	//----------METODI PER LA GENERAZIONE DELLE JTABLE-------------
	
		
	//-----------JTable prodotti con poca disponibilità-------------
		public JScrollPane creatore_JTable(){
			
			MyTableModel mtm = new MyTableModel();
			final JTable table = new JTable() {
				public Dimension getPreferredScrollableViewportSize(){
					return new Dimension(600,400);
				}
			};
			table.setRowSelectionAllowed(true);
			String columnNames[] = new String[] { "Nome", "Categoria", "Descrizione","Disponibilità"
													,"Prezzo","Produttore"};
			mtm.setColumnIdentifiers(columnNames);
		  	table.setModel(mtm);
			//I dati vengono presi dal Database: è necessario aggiungere la colonna "Seleziona" a parte 
			Boolean[] checkbox = new Boolean[magazz_business.PocaDisponibilita().size()];
		  	for(int i=0;i<magazz_business.PocaDisponibilita().size();i++){ //Allocazione dinamica della JTable
		  	    mtm.addRow(magazz_business.PocaDisponibilita().get(i));
		  	   checkbox[i] = false;				
		  	}
		  	mtm.addColumn("Seleziona",checkbox);
		  	mtm.addTableModelListener(TableListener);
		  	JScrollPane scrollPane = new JScrollPane(table);
		  	return scrollPane;
		  	
		    
		}
		
		
		//-----------TABLE RICHIESTE PENDENTI------------
		public JTable creatore_TableRichiestePendenti(){
			MyTableModel mtm = new MyTableModel();
			final JTable table = new JTable(){
				private static final long serialVersionUID = 1L;
				public Dimension getPreferredScrollableViewportSize(){
					return new Dimension(600,400);
				}
			};
			table.setRowSelectionAllowed(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			String columnNames[] = new String[] { "Nome Dipendente", "Cognome Dipendente","Codice Ordine"};
			mtm.setColumnIdentifiers(columnNames);
			table.setModel(mtm);
			JTableListenerMagazziniere lis = new JTableListenerMagazziniere(table);
			for(int i=0;i<magazz_business.TableRichiestePendenti().size();i++){
				mtm.addRow(magazz_business.TableRichiestePendenti().get(i));
			}
			table.setOpaque(true);
			return table;
	     
		}
		
}


