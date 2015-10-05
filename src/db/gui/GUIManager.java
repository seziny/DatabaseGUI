package db.gui;

import java.util.HashMap;

import javax.swing.JFrame;

/**
 * This is the class that has the main method.
 * @author Group04
 *
 */
public class GUIManager {

	/**
	 * This is the main function! This will call the GUI.
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {	
		
		/*  Open the connection with the database  */
		ConnectionHandler.connectToOracle();
		ConnectionHandler connection = new ConnectionHandler();
		
		/*  Populate the library in the QueryExecutor class  */
		QueryExecutor exec = new QueryExecutor( connection );
		populateLibrary( QueryExecutor.queryLibrary );
		
		/*  Fire up the GUI!  */
		QueryView qview = new QueryView( exec );
		openWindow( qview, 500, 500 );
		while ( qview.isOpen ) {} //loop in netherspace until window is closed
		
		/*  Disconnect from the database  */
		connection.disconnect();
	}
	
	/**
	 * This opens the specified GUI View window
	 * @param window the window you want to open.
	 * @param width the width of this window
	 * @param height the height of this window.
	 */
	private static void openWindow( JFrame window, int width, int height )
	{
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize( width, height );
		window.setVisible( true );
	}
	
	/**
	 * This function will populate the library with the pre-loaded function.
	 * Basically this function is where we hardcode the SQL queries that we
	 * want to execute.
	 * @param library
	 */
	private static void populateLibrary( HashMap<String, String> library )
	{
		/*----------------------------------------------
		                  SQL COMMANDS
		 -----------------------------------------------*/
		
		/*  Queries that we created (simple)  */
		String sql_command_s1 = ("");
		String sql_command_s2 = ("");
		String sql_command_s3 = ("");
		String sql_command_s4 = ("");
		String sql_command_s5 = ("");
		String sql_command_s6 = ("");
		
		/*  Queries provided by the prompt PDF  */
		String sql_command_a = ("");
		String sql_command_b = ("");
		String sql_command_c = ("");
		String sql_command_d = ("");
		String sql_command_e = ("");
		String sql_command_f = ("");
		
		/*----------------------------------------------
        		ADD SQL COMMANDS TO LIBRARY
		-----------------------------------------------*/

		/*  SIMPLE Commands  */
		library.put( "s1" , sql_command_s1 );
		library.put( "s2" , sql_command_s2 );
		library.put( "s3" , sql_command_s3 );
		library.put( "s4" , sql_command_s4 );
		library.put( "s5" , sql_command_s5 );
		library.put( "s6" , sql_command_s6 );
		
		/*  PDF Commands  */
		library.put( "a" , sql_command_a );
		library.put( "b" , sql_command_b );
		library.put( "c" , sql_command_c );
		library.put( "d" , sql_command_d );
		library.put( "e" , sql_command_e );
		library.put( "f" , sql_command_f );
	}
}
