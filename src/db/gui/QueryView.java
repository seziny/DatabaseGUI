package db.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This class is used as a simple GUI that will execute the query commands
 * listed in the PDF for Deliverable 2. 
 * 
 * @author Group04
 *
 */
@SuppressWarnings("serial")
public class QueryView extends JFrame {

	/**
	 * Boolean flag to determine if this window is open.
	 */
	public boolean isOpen;
	
	/**
	 * This is the reference to the query executor class that will be used
	 * by this view.
	 */
	private QueryExecutor exec;
	
	/**
	 * This is the table for the output of the data.
	 */
	private QueryTableModel qtm;
	
	/**
	 * Buttons to execute the simple queries that we have created.
	 */
	private JButton simpleCommands[] = {  new JButton( "s1" ),
										  new JButton( "s2" ),
										  new JButton( "s3" ),
										  new JButton( "s4" ),
										  new JButton( "s5" ),
										  new JButton( "s6" )  };
	
	/**
	 * Buttons to click to execute the queries defined in the pdf.
	 * Each is hardcoded, a-f, corresponds to indeces 0-5
	 */
	private JButton pdfCommands[] = { new JButton( "a" ),
									  new JButton( "b" ),
									  new JButton( "c" ),
									  new JButton( "d" ),
									  new JButton( "e" ),
									  new JButton( "f" )  };
	
	/**
	 * The constructor for the Query View Window.
	 * @param exec the QueryExecutor to communicate with.
	 */
	public QueryView( QueryExecutor exec )
	{
		super("Execute Queries");
		this.exec = exec;
		isOpen = true;
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		/////////////////////////////////////////////////////////////////////////
		//                             BUTTONS                                 //
		/////////////////////////////////////////////////////////////////////////
		
		/*----------------------------------------------------------------
		 *   Add action listener to each button. Also add to the frame  
		 *----------------------------------------------------------------*/
		ClickHandler clickHandler = new ClickHandler();
		
		/*  Loop through and add the listener for the simple commands  */
		for ( int i = 0 ; i < simpleCommands.length ; i++ )
		{
			simpleCommands[i].addActionListener( clickHandler );
			add( simpleCommands[i] ); //adds button to the frame
		}
		
		/*  Loop through and add the listener for the pdf commands  */
		for ( int i = 0 ; i < pdfCommands.length ; i++ )
		{
			pdfCommands[i].addActionListener( clickHandler );
			add( pdfCommands[i] ); //adds button to the frame
		}
		
		/////////////////////////////////////////////////////////////////////////
		//                       Table for output                              //
		/////////////////////////////////////////////////////////////////////////
		
		qtm = new QueryTableModel();
		JTable table = new JTable(qtm);
		JScrollPane scrollpane = new JScrollPane(table);
		JPanel p1 = new JPanel();
		getContentPane().add(p1, BorderLayout.NORTH);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
	}
	
	/**
	 * The action listener for the clickable buttons.
	 */
	private class ClickHandler implements ActionListener
	{
		public void actionPerformed ( ActionEvent e )
		{
			String whichQuery = ((JButton) e.getSource()).getText();
			ResultSet rs = exec.queryEntry( whichQuery ); //send query to executor
			qtm.setQuery( rs );
		}
	}
}
