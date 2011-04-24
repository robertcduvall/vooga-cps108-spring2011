package vooga.user.model;
	import java.sql.*;

	/**
	 * @author Conrad Haynes
	 *This SQLite class, although somewhat cluttered, contains all the method calls to access, change, or utilize the 
	 *SQLite database. 
	 */
	public class SQLite {
		
		public static PreparedStatement myPrep;
		public static Connection myConn;
		public static Statement myStat;
		
		public SQLite() throws SQLException, ClassNotFoundException{
				Class.forName("org.sqlite.JDBC");//standard - need every time
		    Connection conn =DriverManager.getConnection("jdbc:sqlite:test1.db");
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
		public void update(String tableName, String[] components, String[] inputs) throws SQLException{
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
		public void updateInfoInDatabase(String tableName, String key, String prompt, String newInput) throws SQLException{
				myStat.execute("update '" + tableName+ "' set '" +prompt+ "'="+ newInput +" where "+"UserName=" + key);
		}
		
		/**
		 * This method removes a specific user or game from the table
		 * @param key - specific user or game you want to remove
		 * @param prompt - should alway equal UserName or GameName - pointer to key
		 * @throws SQLException 
		 */
		public void removeRowFromDatabase(String tableName, String key, String prompt) throws SQLException{
				myStat.execute("delete from "+ tableName + " where "+ prompt + " = '" + key + "'");
		}
		
		/**
		 * This method adds a newUser to the database
		 * @param input - a list of all the user-set preferences (including the username)
		 * @throws SQLException 
		 */
		public void addNewUser(String tableName, String[] input) throws SQLException{
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
		 */
		public void initialize(String tableName, String[] tableComponents) throws SQLException{
				myStat.executeUpdate("drop table if exists " + tableName + ";");
			StringBuilder options = new StringBuilder("");
			StringBuilder placeholder = new StringBuilder("");
			options.append("create table " + tableName +" (");
			for(String component : tableComponents){
				options.append(component + ", ");
				placeholder.append("?, ");
			}
			String temp = trimString(options);
				myStat.executeUpdate(temp + ");");
		    String prePrep = trimString(placeholder);
		    PreparedStatement prep = myConn.prepareStatement("insert into " + tableName + " values ("+ prePrep +");");
		    myPrep = prep;
		}
		
		/**
		 * Removes the last two characters off the end of a StringBuilder - used multiple times with this class
		 */
		private String trimString(StringBuilder s){
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
		public void retrieveTableStats(String tableName, String[] tableComponents) throws SQLException{
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
		 * Retrieves specific information from a database table 
		 * @param username - the user or game for which the information is requested
		 * @param promptRequest - the column for wwhich the information is stored
		 * @throws SQLException 
		 */
		public String retrieveExactEntry(String tableName, String username, String promptRequest) throws SQLException{
		
				ResultSet rs = myStat.executeQuery("SELECT "+ promptRequest + " FROM " +tableName+" WHERE " + "UserName"+" = "+"'"+username+"'");
			
			String s = "empty";
				while ( rs.next() ) {
						s = rs.getString(promptRequest);
				}
				rs.close();
				myConn.close();
			return s;
		}

		/**
		 * A very important method, this code closes the connection to the database 
		 * - database must be closed after each submission
		 * @throws SQLException 
		 */
		public void close() throws SQLException{
				myConn.close();
		}
	}
	
