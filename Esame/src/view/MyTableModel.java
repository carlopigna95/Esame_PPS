package view;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

 
public class MyTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	public MyTableModel(){
		super();
	}
	//Impongo la colonna "Seleziona" di tipo Boolean; le altre colonne rimangono genericamente Object
	public Class getColumnClass(int col) {
       if (col == 6){
    	   return Boolean.class;
       }
       else 
    	   return Object.class;
	}
               
                
	//Rendo solo la colonna con il checkbox editabile
	public boolean isCellEditable(int row, int col) {
        if (col == 6)      //solo la colonna con i checkbox è editabile
            return true;
        else return false;
      }
	
}
