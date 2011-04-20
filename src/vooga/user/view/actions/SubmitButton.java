package vooga.user.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import vooga.user.controller.ILoginController;
import vooga.user.view.gui.middleFrame.FieldPanel;
import vooga.user.view.gui.middleFrame.FieldPanel1;


public class SubmitButton extends AbstractSlogoAction {

	FieldPanel panel;
	ILoginController pc;
	public SubmitButton(ILoginController p, FieldPanel fieldPanel) {
		super(p);
		pc = p;
		panel = fieldPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		pc.processInformation(panel.getPromptText(),panel.getInputFields());
		try {
			pc.logOut();
		} catch (Exception e) {
			System.out.println("no");
			e.printStackTrace();
		}
	}

}
