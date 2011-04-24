package vooga.user.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vooga.user.controller.ILoginController;
import vooga.user.view.gui.middleFrame.FieldPanel;


public class PasswordLogin extends AbstractLoginAction{

	FieldPanel panel;
	public PasswordLogin(ILoginController p, FieldPanel fieldPanel) {
		super(p);
		controller = p;
		panel = fieldPanel;
	}

	//@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String[] input = panel.getInputFields();
		System.out.println("size " + input.length);
		String username = input[0];
		String password = input[1];
		if(!controller.approveLogin(username, password)){
			controller.displayError("Incorrect Username & Password");
		}else{
			try {
			controller.exitLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("WELCOME!!");
		}

	}

}
