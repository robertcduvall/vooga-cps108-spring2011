package vooga.user.model.database;
	import java.sql.*;
import java.util.ArrayList;
import java.util.List;

	/**
	 * @author Conrad Haynes
	 *This SQLite class, although somewhat cluttered, contains all the method calls to access, change, or utilize the 
	 *SQLite database. 
	 */
	public abstract class OldSQLite {
		
		public static PreparedStatement myPrep;
		public static Connection myConn;
		public static Statement myStat;
		
		public OldSQLite() throws SQLException, ClassNotFoundException{
				Class.forName("org.sqlite.JDBC");//standard - need every time
		    Connection conn =DriverManager.getConnection("jdbc:sqlite:voogaUser.db");
		    Statement stat = null;
				stat = conn.createStatement();
		    myStat = stat;
		    myConn = conn;
		}
		
		/**
		 * This update method initializes a database and the appropriate input
		 * @param tableName - name of desired database table
		 * @param components - name of respective columns in table
		 * @throws SQLException 
		 */
		public void createTable(String tableName, String[] components, String[] inputs) throws SQLException{
			initialize(tableName,components);
			int[] columns = new int[components.length];
			for(int x =0; x< components.length; x++){
				columns[x] = x+1;
			}
			addToBatch(columns, inputs);
			submitBatch();
			//close();
		}
		
		/**
		 * This method alters specific information in the database under a certain user or game
		 * @param key - specific username for which you desire to update information
		 * @param prompt - column of information you desire to change
		 * @param newInput - what input you want to replace the current information with
		 * @throws SQLException 
		 */
		public void updateBox(String tableName, String key, String prompt, String newInput) throws SQLException{
				myStat.execute("update '" + tableName+ "' set '" +prompt+ "'="+ newInput +" where "+"UserName=" + key);
		}
		
		/**
		 * This method removes a specific user or game from the table
		 * @param key - specific user or game you want to remove
		 * @param prompt - should alway equal UserName or GameName - pointer to key
		 * @throws SQLException 
		 */
		public void removeRow(String tableName, String key, String prompt) throws SQLException{
				myStat.execute("delete from "+ tableName + " where "+ prompt + " = '" + key + "'");
		}
		
		/**
		 * This method adds a newUser to the database
		 * @param input - a list of all the user-set preferences (including the username)
		 * @throws SQLException 
		 */
		public void addRow(String tableName, String[] input) throws SQLException{
			StringBuilder sb = new StringBuilder("Insert into " + tableName + " values('");
		
				for(String s : input){
					sb.append(s);
					sb.append("','");
				}
			String s = trimString(sb);
			s = s + ");";
			
				myStat.execute(s);
		}

		/**
		 * This method creates a new table based off of the respective tableComponents
		 * @param tableComponents - represents the columns to be created within the tables
		 * @throws SQLException 
		 * @throws SQLException 
		 */
		public abstract void initialize(String tableName, String[] tableComponents) throws SQLException;
		
		/**
		 * Removes the last two characters off the end of a StringBuilder - used multiple times with this class
		 */
		protected String trimString(StringBuilder s){
			String p = s.substring(0, s.length()-2);
			return p;
		}
		
		/**
		 * Utilized by the update method, this method actually adds user input into a batch before submitting to the database
		 * @param columns - columnTitles
		 * @param inputs - corresponding user input
		 * @throws SQLException 
		 */
		private void addToBatch(int[] columns, String[] inputs) throws SQLException{
			  for(int i = 0; i < inputs.length; i++){
					myPrep.setString(columns[i], inputs[i]);
			  }
					myPrep.addBatch();
		}
		
		/**
		 * This method submits all information from the batch to the database
		 * @throws SQLException 
		 */
		public void submitBatch() throws SQLException{
				myConn.setAutoCommit(false);//this generic - copy format
				myPrep.executeBatch();
				myConn.setAutoCommit(true);
		}
		
		/**
		 * This method retrieves all information from within a table - tableName
		 * @param tableComponents - all columns within Table, that the user wants displayed
		 * @throws SQLException 
		 */
		public void retrieveFullTable(String tableName, String[] tableComponents) throws SQLException{
			ResultSet rs = myStat.executeQuery("select * from " + tableName + ";");
		    
				while (rs.next()) {
					for(int j = 0; j < tableComponents.length; j++){
						System.out.println(tableComponents[j]+" = " + rs.getString(tableComponents[j]));
					}
				}
				rs.close();
				myConn.close();
		}
		
		/**
		 * This method retrieves all information from a specific column of a table
		 * @param tableComponents - a column within a Table, that the user wants displayed
		 * @return 
		 * @throws SQLException 
		 */
		public List<String> retrieveTableColumn(String tableName, String tableComponent) throws SQLException{
			ResultSet rs = myStat.executeQuery("select " + tableComponent + " from " + tableName + ";");
		    List<String> list = new ArrayList<String>();
			String s = "";
				while (rs.next()) {
					s = rs.getString(tableComponent);
					System.out.println(tableComponent+" = " + s);
					list.add(s);
				}
				rs.close();
				myConn.close();
				return list;
		}
				
		public String retrieveTopRow(String tableName, String tableComponent) throws SQLException{
			String result = null;
				ResultSet rs = myStat.executeQuery("select " + tableComponent +" from " + tableName + " limit 1;");
		
				while (rs.next()) {
						result = rs.getString(tableComponent);
						System.out.println(tableComponent+" = " + result);
						
			}
				rs.close();
				myConn.close();
				
				return result;
		}
		
		/**
		 * Retrieves specific information from a database table 
		 * @param username - the user or game for which the information is requested
		 * @param promptRequest - the column for wwhich the information is stored
		 * @throws SQLException 
		 */
		public String retrieveBox(String tableName, String username, String promptRequest) throws SQLException{
		
				ResultSet rs = myStat.executeQuery("SELECT "+ promptRequest + " FROM " +tableName+" WHERE " + "UserName"+" = "+"'"+username+"'");
			
			String s = "empty";
				while ( rs.next() ) {
						s = rs.getString(promptRequest);
				}
				rs.close();
				myConn.close();
			return s;
		}

		public void deleteFromTable(String tableName) throws SQLException{
			
			ResultSet rs = myStat.executeQuery("Delete from "+ tableName);
		
		String s = "empty";
			while ( rs.next() ) {
					rs.deleteRow();
			}
			rs.close();
			myConn.close();
	}
		
		
		/**
		 * A very important method, this code closes the connection to the database 
		 * - database must be closed after each submission
		 * @throws SQLException 
		 */
		public void close() throws SQLException{
				myConn.close();
		}
		
		public void clear(String tableName){
			try {
				myStat.executeUpdate("drop table if exists " + tableName + ";");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
