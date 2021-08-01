package common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Jdbc_connect 
{
	public static Connection getConnection() 
	{
		 FileInputStream fis = null;
		try {
			fis = new FileInputStream("connection.properties");
		} catch (FileNotFoundException e) {
			System.out.println("No file found");
			e.printStackTrace();
		} 
	        Properties p=new Properties (); 
	        try {
				p.load (fis);
			} catch (IOException e) {
				System.out.println("Unable to load file");
				e.printStackTrace();
			} 
	        String dname= (String) p.get ("driver"); 
	        String url= (String) p.get ("url"); 
	        String username= (String) p.get ("username"); 
	        String password= (String) p.get ("password"); 
	        try {
				Class.forName(dname);
			} catch (ClassNotFoundException e) {
				System.out.println("Driver error");
				e.printStackTrace();
			} 
	        Connection con = null;
			try {
				con = DriverManager.getConnection( 
				        url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	        return con;
	}
}
