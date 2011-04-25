package vooga.user.view.gui.middleFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class InputSection implements ISectionAddable {

	@Override
	public void addSection(String sectionTitle, String[] sectionInformation, FieldPanel panel) {
		addSeparator(panel, sectionTitle);	
		for(String r : sectionInformation){
			panel.getPrompts().add(r);
		}		
		// The for loop creates each prompt that the operator wants to ask
		for (int i = 0; i < sectionInformation.length; i++) {
			JTextField user = new JTextField(30);
			panel.add(new JLabel(sectionInformation[i]), "gap para");
			panel.getInputs().add(user);
			panel.add(user, "span, growx, wrap");
		}
	}

	/**
	 * This method is utilized by the add Section method to create different sections to the game
	 */
	 public void addSeparator(JPanel panel, String text)
	   {
	      panel.add(new JLabel(text), "gapbottom 1, span, split 2, aligny center");
	      panel.add(new JSeparator(), "gapleft rel, growx");
	   }

}
