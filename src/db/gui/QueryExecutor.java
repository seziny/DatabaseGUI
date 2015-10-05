package db.gui;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * This is the class that will actually build and execute
 * the SQL queries.
 * @author Group04
 *
 */
public class QueryExecutor {
		
	/**
	 * A HashMap to associate the name of the query with the String construction
	 * of the query.
	 */
	public static HashMap<String, String> queryLibrary = new HashMap<String, String>();
	
	/**
	 * This is the database connection handler.
	 */
	private ConnectionHandler connection;
	
	/**
	 * The constructor for the query executor.
	 * @param connection the connection to the database
	 */
	public QueryExecutor( ConnectionHandler connection ) 
	{
		this.connection = connection;
	}
	
	/**
	 * This is the function that will take in the name of the query, match it to the
	 * existing library, grab the query and send it across to the database.
	 * @param name of the query
	 * @return The result of the query. Null if the query does not exist.
	 */
	public ResultSet queryEntry( String name )
	{
		if ( !queryExists(name) ) return null;
		else
		{
			ResultSet result = connection.send2DB( queryLibrary.get( name ) );
			return result;
		}
	}
	
	/**
	 * Take a quick check to see if the query exists in our pre-loaded library
	 * @param name the name of the query
	 * @return true if the query exists, false if it doesn't
	 */
	private boolean queryExists( String name )
	{
		if ( queryLibrary.containsKey( name ) )
			return true;
		else
			return false;
	}
}