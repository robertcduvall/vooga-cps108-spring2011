package vooga.user.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import vooga.user.controller.ILoginController;
import vooga.user.view.gui.middleFrame.FieldPanel;


public class SubmitButton extends AbstractLoginAction {

	FieldPanel panel;
	ILoginController pc;
	public SubmitButton(ILoginController p, FieldPanel fieldPanel) {
		super(p);
		pc = p;
		panel = fieldPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(pc.processInformation(panel.getInputFields())){
		try {
			pc.logIn();
		} catch (Exception e) {
			System.out.println("no");
			e.printStackTrace();
		}
		}
	}

}
