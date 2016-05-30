package dao;

import java.util.Vector;

import org.omg.CORBA.PRIVATE_MEMBER;

import dbconnection.DbConnection;
import model.Progetto;
import model.Sessione;
import model.UtenteRegistrato;

public class ProgettoDAO {

	private static ProgettoDAO instance;
	
	 public static ProgettoDAO getInstance()
	    {
	    	if (instance==null)
	    	{
	    		instance = new ProgettoDAO();
	    	}
	    	return instance;
	    }
	
}
