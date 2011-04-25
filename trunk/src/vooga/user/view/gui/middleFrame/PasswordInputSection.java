package vooga.user.view.gui.middleFrame;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PasswordInputSection implements ISectionAddable {

	@Override
	public void addSection(String sectionTitle, String request, FieldPanel panel) {		
		// The for loop creates each prompt that the operator wants to ask
			JTextField password = new JPasswordField(30);
			panel.add(new JLabel(request), "gap para");
			panel.getInputs().add(password);
			panel.add(password, "span, growx, wrap");
	}
}
