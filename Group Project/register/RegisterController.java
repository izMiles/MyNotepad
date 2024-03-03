package project.finals.register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import project.finals.login.LoginView;


public class RegisterController{

	private RegisterView registerView;
	private LoginView loginView;
	private Register register;


	public RegisterController(RegisterView registerView){

		this.registerView = registerView;
		this.loginView = new LoginView();
		this.register = new Register();
	}

	public ItemListener showPasswordBtn(JPasswordField passInput) {

        return new ItemListener() {

            public void itemStateChanged(ItemEvent e) {

            	if(e.getStateChange() == ItemEvent.SELECTED) {

            	    passInput.setEchoChar((char) 0);

            	} else {

                	passInput.setEchoChar('*');

            	}


            }
        
		};

    }


	public ActionListener cancelBtn(JFrame GuiFrame){
		
		return new ActionListener(){

			public void actionPerformed(ActionEvent e){

				GuiFrame.getContentPane().removeAll();
				GuiFrame.repaint();

				loginView.loginGui(GuiFrame);
			}
		};
	}

 	public ActionListener registrationBtn(JFrame GuiFrame, JTextField name, JTextField userName, JPasswordField password) {

		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String getName = name.getText();
				String getUsername = userName.getText();
				String getPassword = new String(password.getPassword());

				if (getName.isEmpty() || getUsername.isEmpty() || getPassword.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Please Fill the Required Field");

				} else {


					register.registerFileHandling(getName, getUsername, getPassword);
					register.createFolder(getName);
					GuiFrame.getContentPane().removeAll();
					GuiFrame.repaint();
					loginView.loginGui(GuiFrame);

				}

			}

		};
	}

}