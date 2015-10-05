package db.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This is the class used to connect to the Database.  It connects to Oracle,
 * as well as sends the commands and receives the results.
 * @author Group04
 *
 */
public class ConnectionHandler {

	private Connection con;
	private Statement statement;
	private ResultSet rs;

	/**
	 * Connects to Oracle.  It instantiates the driver and gets the connection up and rolling.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void connectToOracle() throws InstantiationException, IllegalAccessException
    {
        String url = "jdbc:oracle:thin:@icoracle.epfl.ch:1521/srso4.epfl.ch";
        String userName = "DB2012_G04";
        String pass = "DB2012_G04";
             
        Connection connect = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                }
            catch(ClassNotFoundException e) {
                System.err.println("Driver not found!");
                System.err.println(e.getMessage());
                System.err.println(e.getStackTrace().toString() );
        }
        try{
           connect = DriverManager.getConnection(url,userName,pass);
            System.out.println("Done!");
            
            Statement st = connect.createStatement();
            ResultSet data=st.executeQuery("select table_name from user_tables");
            
           while(data.next()){
            System.out.println( data.getString(1));
           }
        }
        catch(Exception e){
            System.out.println("bo");
            System.err.println(e.getMessage());
        }
    }

	/**
	 * This functions sends a command to the database and returns the results.
	 * @param command the SQL command you are sending
	 * @return the result to your query
	 */
	public ResultSet send2DB( String command ) {
		try {
			statement = con.createStatement();  
			rs = statement.executeQuery(command);  
		} catch ( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
		}
		return rs;	
	}

	public void disconnect() {
		// TODO Auto-generated method stub
	}
}
