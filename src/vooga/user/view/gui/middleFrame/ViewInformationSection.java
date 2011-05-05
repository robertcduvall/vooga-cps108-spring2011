package vooga.user.view.gui.middleFrame;
import javax.swing.JLabel;
import vooga.user.model.database.SQLiteConnection;
import vooga.user.model.database.UserDatabase;

public class ViewInformationSection implements ISectionAddable {
	SQLiteConnection database;
	private final static String USER_TABLE = "user";
	
	
	@Override
	public void addSection(String sectionTitle, String request,
			FieldPanel panel) {
			panel.add(new JLabel(request), "gap para");
			panel.add(new JLabel(getBoxInput(request,"Guest")),"gap para, span, growx, wrap");
		//}
	}
	
	public String getBoxInput(String request, String userName){
			database = new UserDatabase();
			return database.retrieveBox(USER_TABLE, userName, request);
	}
}
