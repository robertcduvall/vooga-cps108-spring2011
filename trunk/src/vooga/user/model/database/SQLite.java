package vooga.user.model.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

	/**
	 * @author Conrad Haynes
	 *This SQLite class, although somewhat cluttered, contains all the method calls to access, change, or utilize the 
	 *SQLite database. 
	 */
	public abstract class SQLite {
		
		public static PreparedStatement myPrep;
		public static Connection myConn;
		public static Statement myStat;
		
		public SQLite(){
				try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e) {e.printStackTrace();}//standard - need every time
		    Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:voogaUser.db");
			} catch (SQLException e) {e.printStackTrace();}
		    Statement stat = null;
				try {
					stat = conn.createStatement();
				} catch (SQLException e) {e.printStackTrace();}
		    myStat = stat;
		    myConn = conn;
		}
		
		/**
		 * This method creates a new table based off of the respective tableComponents
		 * @param tableComponents - represents the columns to be created within the tables
		 * @throws SQLException 
		 * @throws SQLException 
		 */
		public abstract void initialize(String tableName, String[] tableComponents) throws SQLException;	
	
		
	//These following 2 methods act on a specific box within a table	
		/**
		 * This method alters specific information in the database under a certain user or game
		 * @param key - specific username for which you desire to update information
		 * @param prompt - column of information you desire to change
		 * @param newInput - what input you want to replace the current information with
		 * @throws SQLException 
		 */
		public void updateBox(String tableName, String key, String prompt, String newInput){
				try {
					myStat.execute("update '" + tableName+ "' set '" +prompt+ "'="+ newInput +" where "+"UserName=" + key);
				} catch (SQLException e) {e.printStackTrace();}
		}
	
		/**
		 * Retrieves specific information from a database table //ALWAYS CALL CLOSE AFTERWARDS!!!
		 * @param username - the user or game for which the information is requested
		 * @param promptRequest - the column for wwhich the information is stored
		 * @throws SQLException 
		 */
		public String retrieveBox(String tableName, String username, String promptRequest){
		
				ResultSet rs = null;
				try {
					rs = myStat.executeQuery("SELECT "+ promptRequest + " FROM " +tableName+" WHERE " + "UserName"+" = "+"'"+username+"'");
				} catch (SQLException e) {e.printStackTrace();}
			
			String s = "empty";
				try {
					while ( rs.next() ) {
							try {
								s = rs.getString(promptRequest);
								System.out.println("in loop " + s);
							} catch (SQLException e) {e.printStackTrace();}
					}
				} catch (SQLException e) {e.printStackTrace();}
				
			return s;
		}
		
		
	//These following 3 methods act on a specific row within a table	
		/**
		 * This method adds a newUser to the database
		 * @param input - a list of all the user-set preferences (including the username)
		 * @throws SQLException 
		 */
		public void addRow(String tableName, String[] input){
			StringBuilder sb = new StringBuilder("Insert into " + tableName + " values('");
		
				for(String s : input){
					sb.append(s);
					sb.append("','");
				}
			String s = trimString(sb);
			s = s + ");";
			
				try {
					myStat.execute(s);
				} catch (SQLException e) {e.printStackTrace();}
		}

		/**
		 * This method retrieves an array that contains all the preferences for a specific key 
		 * to the table
		 * @param tableName - name of table
		 * @param key - the username or game title you are searching for
		 * @param prompts - prompts array contains the name of all the search-able preferences
		 * @return
		 */
		public String[] retrieveRow(String tableName, String key, String[] prompts){
			String[] preferences = new String[prompts.length];
			for(int i = 0; i < prompts.length; i++){
				System.out.println("for loop " + i);
				preferences[i] = retrieveBox(tableName,key,prompts[i]);
			}
			return preferences;
		}
		
		/**
		 * This method removes a specific user or game from the table
		 * @param key - specific user or game you want to remove
		 * @param prompt - should alway equal UserName or GameName - pointer to key
		 * @throws SQLException 
		 */
		public void removeRow(String tableName, String key, String prompt){
				try {
					myStat.execute("delete from "+ tableName + " where "+ prompt + " = '" + key + "'");
				} catch (SQLException e) {
					e.printStackTrace();}
		}
			
		
	//These following 3 methods retrieve general components from a table
		/**
		 * This method retrieves all information from within a table - tableName
		 * @param tableComponents - all columns within Table, that the user wants displayed
		 * @throws SQLException 
		 */
		public void retrieveFullTable(String tableName, String[] tableComponents){
			ResultSet rs = null;
			try {
				rs = myStat.executeQuery("select * from " + tableName + ";");
			} catch (SQLException e) {e.printStackTrace();}
		    
				try {
					while (rs.next()) {
						for(int j = 0; j < tableComponents.length; j++){
							System.out.println(tableComponents[j]+" = " + rs.getString(tableComponents[j]));
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					rs.close();
				} catch (SQLException e) {e.printStackTrace();}
				try {
					myConn.close();
				} catch (SQLException e) {e.printStackTrace();}
		}
		
		/**
		 * This method retrieves all information from a specific column of a table
		 * @param tableComponents - a column within a Table, that the user wants displayed
		 * @return 
		 * @throws SQLException 
		 */
		public List<String> retrieveTableColumn(String tableName, String tableComponent){
			ResultSet rs = null;
			try {
				rs = myStat.executeQuery("select " + tableComponent + " from " + tableName + ";");
			} catch (SQLException e) {e.printStackTrace();}
		    List<String> list = new ArrayList<String>();
			String s = "";
				try {
					while (rs.next()) {
						s = rs.getString(tableComponent);
						System.out.println(tableComponent+" = " + s);
						list.add(s);
					}
				} catch (SQLException e) {e.printStackTrace();}
				try {
					rs.close();
				} catch (SQLException e) {e.printStackTrace();}
				try {
					myConn.close();
				} catch (SQLException e) {e.printStackTrace();}
				return list;
		}
				
			

		
	//Clean-up operations and helper methods
		/**
		 * Removes the last two characters off the end of a StringBuilder - used multiple times with this class
		 */
		protected String trimString(StringBuilder s){
			String p = s.substring(0, s.length()-2);
			return p;
		}
		
		/**
		 * A very important method, this code closes the connection to the database 
		 * - database must be closed after each submission
		 * @throws SQLException 
		 */
		public void close(){
				try {
					myConn.close();
				} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		public void clear(String tableName){
			try {
				myStat.executeUpdate("drop table if exists " + tableName + ";");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
