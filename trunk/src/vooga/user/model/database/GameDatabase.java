package vooga.user.model.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * @author CP
 * This class, one that extends the SQLite Database class, was created to handle the initialization of the game Database.
 * Specifically that of how the database is created and what exactly it is used for. In this case, it handles the details
 * relevant to an individual game such as: title, highscore, ranking, etc.
 */
public class GameDatabase extends SQLiteConnection{

	public GameDatabase(){
		super();
	}

	//This initialize method creates a gameDatabase table for the users to add and retrieve information from
	@Override
	public void initialize(String tableName, String[] tableComponents)
			throws SQLException {
		StringBuilder options = new StringBuilder("");
		StringBuilder placeholder = new StringBuilder("");
		options.append("create table if not exists " + tableName + " (");
		for (String component : tableComponents) {
			options.append(component + ", ");
			placeholder.append("?, ");
		}
		String temp = trimString(options);
		myStat.executeUpdate(temp + ");");
		String prePrep = trimString(placeholder);
		PreparedStatement prep = myConn.prepareStatement("insert into "
				+ tableName + " values (" + prePrep + ");");
		myPrep = prep;
	}

}
