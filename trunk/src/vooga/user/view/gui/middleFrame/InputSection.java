package vooga.user.view.gui.middleFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputSection implements ISectionAddable {

	@Override
	public void addSection(String sectionTitle, String request, FieldPanel panel) {	
		// The for loop creates each prompt that the operator wants to ask
			JTextField user = new JTextField(30);
			panel.add(new JLabel(request), "gap para");
			panel.getInputs().add(user);
			panel.add(user, "span, growx, wrap");	
	}

}
