package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import javax.swing.JFrame;

public class AscoltatoreCapoProgetto implements ActionListener{
	JFrame finestra = new JFrame();
	
	public AscoltatoreCapoProgetto(JFrame finestra){
		this.finestra = finestra;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		 if(arg0.getActionCommand().equals("conferma")){   
			
			////ALGORITMO DI CONFERMA ORGANIZZAZIONE SPESE.....
		}
		 
			
		 else if(arg0.getActionCommand().equals("stampa")){
				PrinterJob pj = PrinterJob.getPrinterJob();
				PageFormat pf = pj.pageDialog(pj.defaultPage());
			}
		
	}
	
	

}
