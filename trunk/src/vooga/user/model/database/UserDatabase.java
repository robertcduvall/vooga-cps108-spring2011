package vooga.user.model.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * @author CP
 * This class, one that extends the SQLite Database class, was created to handle the initialization of the user Database.
 * Specifically that of how the database is created and what exactly it is used for. In this case, it handles the details
 * relevant to all users of the game engine such as: age, e-mail, passwords, telephone numbers, etc.
 */
public class UserDatabase extends SQLiteConnection{

	public UserDatabase(){
		super();
	}

	//This initialize method creates a userDatabase table for the users to add and retrieve information from
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
