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
		
		public SQLite(){
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} //standard - need every time
		    Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    Statement stat = null;
			try {
				stat = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    myStat = stat;
		    myConn = conn;
		}
		
		/**
		 * This update method initializes a database and the appropriate input
		 * @param tableName - name of desired database table
		 * @param components - name of respective columns in table
		 */
		public void update(String tableName, String[] components, String[] inputs){
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
		 */
		public void updateInfoInDatabase(String tableName, String key, String prompt, String newInput){
			try {
				myStat.execute("update '" + tableName+ "' set '" +prompt+ "'="+ newInput +" where "+"UserName=" + key);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * This method removes a specific user or game from the table
		 * @param key - specific user or game you want to remove
		 * @param prompt - should alway equal UserName or GameName - pointer to key
		 */
		public void removeRowFromDatabase(String tableName, String key, String prompt){
			try {
				myStat.execute("delete from "+ tableName + " where "+ prompt + " = '" + key + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * This method adds a newUser to the database
		 * @param input - a list of all the user-set preferences (including the username)
		 */
		public void addNewUser(String tableName, String[] input){
			StringBuilder sb = new StringBuilder("Insert into " + tableName + " values('");
			try {
				for(String s : input){
					sb.append(s);
					sb.append("','");
				}
			String s = trimString(sb);
			s = s + ");";
			
				myStat.execute(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method creates a new table based off of the respective tableComponents
		 * @param tableComponents - represents the columns to be created within the tables
		 */
		public void initialize(String tableName, String[] tableComponents){
			try {
				myStat.executeUpdate("drop table if exists " + tableName + ";");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			StringBuilder options = new StringBuilder("");
			StringBuilder placeholder = new StringBuilder("");
			options.append("create table " + tableName +" (");
			for(String component : tableComponents){
				options.append(component + ", ");
				placeholder.append("?, ");
			}
			String temp = trimString(options);
		    try {
				myStat.executeUpdate(temp + ");");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    String prePrep = trimString(placeholder);
		    PreparedStatement prep = null;
			try {
				prep = myConn.prepareStatement("insert into " + tableName + " values ("+ prePrep +");");
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		 */
		private void addToBatch(int[] columns, String[] inputs){
			  for(int i = 0; i < inputs.length; i++){
				  try {
					myPrep.setString(columns[i], inputs[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			  }
			    try {
					myPrep.addBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		/**
		 * This method submits all information from the batch to the database
		 */
		public void submitBatch(){
			try {
				myConn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			} //this generic - copy format
		    try {
				myPrep.executeBatch();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    try {
				myConn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * This method retrieves all information from within a table - tableName
		 * @param tableComponents - all columns within Table, that the user wants displayed
		 */
		public void retrieveTableStats(String tableName, String[] tableComponents){
			ResultSet rs = null;
			try {
				rs = myStat.executeQuery("select * from " + tableName + ";");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    try {
				while (rs.next()) {
					for(int j = 0; j < tableComponents.length; j++){
						System.out.println(tableComponents[j]+" = " + rs.getString(tableComponents[j]));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Retrieves specific information from a database table 
		 * @param username - the user or game for which the information is requested
		 * @param promptRequest - the column for wwhich the information is stored
		 */
		public String retrieveExactEntry(String tableName, String username, String promptRequest){
			ResultSet rs = null;
			try {
				rs = myStat.executeQuery("SELECT "+ promptRequest + " FROM " +tableName+" WHERE " + "UserName"+" = "+"'"+username+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String s = "empty";
			try {
				while ( rs.next() ) {
				    try {
						s = rs.getString(promptRequest);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
			return s;
		}

		/**
		 * A very important method, this code closes the connection to the database 
		 * - database must be closed after each submission
		 */
		public void close(){
			try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
