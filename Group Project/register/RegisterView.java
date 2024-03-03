package project.finals.register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterView{

	private RegisterController registerController;


	public void registerGui(JFrame GuiFrame){

		registerController = new RegisterController(this);

		
        JPanel registerPanel = new JPanel();
		registerPanel.setBounds(500, 0, 500, 700);
		registerPanel.setBackground(new Color(30, 30, 35));
		registerPanel.setLayout(null);

		
		ImageIcon regImage = new ImageIcon(getClass().getResource("/image/image.png"));
		
		
		JLabel registerImage = new JLabel();
        registerImage.setIcon(regImage);
        registerImage.setBounds(0, 0, 500, 700);
       
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(0, 0, 500, 700);
		imagePanel.setLayout(new OverlayLayout(imagePanel));

		
		imagePanel.add(registerImage);

		
        JLabel title = new JLabel("My Notepad");
		title.setFont(new Font("MV Boli", Font.PLAIN,30));
		title.setForeground(new Color(242, 172, 97));
		title.setBounds(170, 80, 200, 30);
		registerPanel.add(title);

		
        JLabel nameLabel = new JLabel("NICKNAME");
		nameLabel.setBounds(100, 140, 200, 30);
		nameLabel.setForeground((Color.white));
		registerPanel.add(nameLabel);

		
        JTextField nameInput = new JTextField();
		nameInput.setBounds(100, 185, 300, 30);
		nameInput.setBackground((Color.white));
		nameInput.setFont(new Font("SansSerif", Font.PLAIN,16));
		registerPanel.add(nameInput);

		
        JLabel usernLabel = new JLabel("USERNAME");
		usernLabel.setBounds(100, 230, 150, 30);
		usernLabel.setForeground((Color.white));
		registerPanel.add(usernLabel);

		
        JTextField usernInput = new JTextField();
		usernInput.setBounds(100, 275, 300, 30);
		usernInput.setBackground((Color.white));
		usernInput.setFont(new Font("SanesSerif", Font.PLAIN, 16));
		registerPanel.add(usernInput);

		
        JLabel passLabel = new JLabel("PASSWORD");
		passLabel.setBounds(100, 320, 150, 30);
		passLabel.setForeground((Color.white));
		registerPanel.add(passLabel);

		
        JPasswordField passInput = new JPasswordField();
		passInput.setBounds(100, 365, 300, 30);
		passInput.setBackground((Color.white));
		registerPanel.add(passInput);


        JCheckBox showPass = new JCheckBox("Show Password");
		showPass.setBounds(100, 400, 200, 40);
		showPass.setFont(new Font("SansSerif", Font.PLAIN, 12));
		showPass.setForeground((Color.white));
		showPass.setBorderPainted(false);
		showPass.setFocusPainted(false);
		showPass.setBackground(new Color(30, 30, 35));
		showPass.addItemListener(registerController.showPasswordBtn(passInput));
		registerPanel.add(showPass);

		
        JButton registerBtn = new JButton("CREATE");
		registerBtn.setBounds(150, 470, 200, 40);
		registerBtn.setBackground(new Color(242, 172, 97));
		registerBtn.setForeground((Color.white));
		registerBtn.setFocusPainted(false);
		registerBtn.setBorderPainted(false);
		registerBtn.addActionListener(registerController.registrationBtn(GuiFrame, nameInput, usernInput, passInput));
		registerPanel.add(registerBtn);

		
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(250, 550, 5, 50);
		registerPanel.add(separator);

		
        JButton signInBtn = new JButton ("SIGN IN");
		signInBtn.setBounds(130, 550, 100, 40);
		signInBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
		signInBtn.setForeground((Color.white));
		signInBtn.setBorderPainted(false);
		signInBtn.setFocusPainted(false);
		signInBtn.setBackground(new Color(30, 30, 35));
		signInBtn.addActionListener(registerController.cancelBtn(GuiFrame));
		registerPanel.add(signInBtn);


		JButton createAccBtn = new JButton ("REGISTER");
		createAccBtn.setBounds(270, 550, 120, 40);
		createAccBtn.setBorderPainted(false);
		createAccBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
		createAccBtn.setFocusPainted(false);
		createAccBtn.setForeground((Color.white));
		createAccBtn.setBackground(new Color(30, 30, 35));
		registerPanel.add(createAccBtn);


		GuiFrame.getContentPane().add(imagePanel);
		GuiFrame.add(registerPanel);

	}
}