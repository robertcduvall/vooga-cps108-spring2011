package vooga.user.model.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDatabase extends SQLite{

	public UserDatabase(){
		super();
	}

	@Override
	public void initialize(String tableName, String[] tableComponents) {
		try {
			myStat.executeUpdate("drop table if exists " + tableName + ";");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		StringBuilder options = new StringBuilder("");
		StringBuilder placeholder = new StringBuilder("");
		options.append("create table " + tableName +" (");
		//CREATE [TEMPORARY] TABLE [IF NOT EXISTS] tbl_name [(create_definition,...)]
		for(String component : tableComponents){
			options.append(component + ", ");
			placeholder.append("?, ");
		}
		String temp = trimString(options);
			try {
				myStat.executeUpdate(temp + ");");
			} catch (SQLException e1) {
				e1.printStackTrace();
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

}
