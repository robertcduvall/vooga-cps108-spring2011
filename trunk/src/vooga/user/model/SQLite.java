package vooga.user.model;
	import java.sql.*;

	public class SQLite {
		
		public static PreparedStatement myPrep;
		public static Connection myConn;
		public static Statement myStat;
		
		public SQLite() throws ClassNotFoundException, SQLException{
			Class.forName("org.sqlite.JDBC"); //standard - need every time
		    Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		    Statement stat = conn.createStatement();
		    myStat = stat;
		    myConn = conn;
		}
		
		public void update(String tableName, String[] components, String[] inputs) throws SQLException{
			initialize(tableName,components);
			int[] columns = new int[components.length];
			//String[] col = {"0","1","2","3","4","5","6","7","8","9","10"};
			for(int x =0; x< components.length; x++){
				columns[x] = x+1;
			}
			addToBatch(columns, inputs);
			submitBatch();
			//close();
		}
		
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
		
		private String trimString(StringBuilder s){
			String p = s.substring(0, s.length()-2);
			return p;
		}
		
		
		public void addToBatch(int[] columns, String[] inputs) throws SQLException{
			  for(int i = 0; i < inputs.length; i++){
				  myPrep.setString(columns[i], inputs[i]); 
			  }
			    myPrep.addBatch();
		}
		
		public void submitBatch() throws SQLException{
			myConn.setAutoCommit(false); //this generic - copy format
		    myPrep.executeBatch();
		    myConn.setAutoCommit(true);
		}
		
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
		
		public void close() throws SQLException{
			myConn.close();
		}
	}
