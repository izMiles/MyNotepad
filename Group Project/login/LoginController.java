package project.finals.login;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import project.finals.register.RegisterView;
import project.finals.dashboard.DashboardView;
import project.finals.dashboard.AdminView;
import project.finals.dashboard.DashboardController;
import project.finals.dashboard.AdminController;
import project.finals.dashboard.ManagerView;
import project.finals.dashboard.ManagerController;
public class LoginController{

	private LoginView loginView;
	private RegisterView registerView;
	private Login login;
	private DashboardView dashboardView;
	private AdminView adminView;
	private DashboardController dashboardController;
	private AdminController adminController;
	private ManagerView managerView;
	private ManagerController managerController;
	public LoginController(LoginView loginView){

		this.loginView = loginView;
		this.registerView = new RegisterView();
		this.login = new Login();
		this.dashboardView = new DashboardView();
		this.dashboardController = new DashboardController(this.dashboardView); // You may need to adjust this based on your actual code.
        this.adminView = new AdminView(this.dashboardController, this.adminController);
		this.adminController = new AdminController(this.adminView);
		this.managerController = new ManagerController(this.managerView);
		this.managerView = new ManagerView(this.dashboardController, this.managerController);
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


  	public ActionListener signInBtn(JFrame GuiFrame, JTextField userName, JPasswordField password) {

		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String getUserName = userName.getText();
                String getPassword = new String(password.getPassword());
				String adminUser = "admin101";
				String adminPass = "admin101";


        		if (getUserName.isEmpty() || getPassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Fill the Required Field!");
				} else if (getUserName.equals(adminUser) && getPassword.equals(adminPass)) {
					GuiFrame.getContentPane().removeAll();
					GuiFrame.repaint();
					adminView.AdminGui(GuiFrame, null);
				} else {
					String displayManager = login.loginManagerHandling(getUserName, getPassword);
					String displayName = login.loginFileHandling(getUserName, getPassword);
				
					if (displayName != null) {
						GuiFrame.getContentPane().removeAll();
						GuiFrame.repaint();
						dashboardView.dashboardGui(GuiFrame, displayName);
					} else if (displayManager != null) {
						GuiFrame.getContentPane().removeAll();
						GuiFrame.repaint();
						managerView.ManagerGui(GuiFrame, displayManager);
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect Password or Username!");
					}
				}
					 

            }


		};
	}


	public ActionListener getRegisterButton(JFrame GuiFrame) { 

		return new ActionListener() {

			public void actionPerformed(ActionEvent e){

			GuiFrame.getContentPane().removeAll();
			GuiFrame.repaint();

			registerView.registerGui(GuiFrame);

    
            }
		};
	}

    
}