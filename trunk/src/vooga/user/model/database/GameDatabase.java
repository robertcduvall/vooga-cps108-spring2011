package vooga.user.model.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDatabase extends SQLite{

	public GameDatabase() throws SQLException, ClassNotFoundException {
		super();
	}

	@Override
	public void initialize(String tableName, String[] tableComponents)
			throws SQLException {
		StringBuilder options = new StringBuilder("");
		StringBuilder placeholder = new StringBuilder("");
		options.append("create table if not exists " + tableName +" (");
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

}
