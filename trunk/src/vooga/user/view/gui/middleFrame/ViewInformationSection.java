package vooga.user.view.gui.middleFrame;

import java.sql.SQLException;

import javax.swing.JLabel;

import vooga.user.model.SQLite;

public class ViewInformationSection implements ISectionAddable {
	SQLite database;
	private final static String USER_TABLE = "user";
	
	@Override
	public void addSection(String sectionTitle, String request,
			FieldPanel panel) {
			panel.add(new JLabel(request), "gap para");
			panel.add(new JLabel(getDatabaseMapping(request)),"gap para, span, growx, wrap");
			
	}
	
	public String getDatabaseMapping(String request){
		String s = "";
		try {
			database = new SQLite();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			s = database.retrieveTopRow(USER_TABLE, request);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
}
