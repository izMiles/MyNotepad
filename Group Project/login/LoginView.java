package project.finals.login;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginView{

	private LoginController loginController;


	public void loginGui(JFrame GuiFrame){

		loginController = new LoginController(this);


		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(500, 0, 500, 700);
		loginPanel.setBackground(new Color(30, 30, 35));
		loginPanel.setLayout(null);

		
		ImageIcon regImage = new ImageIcon(getClass().getResource("/image/image.png"));
		
		
		JLabel logImage = new JLabel();
        logImage.setIcon(regImage);
        logImage.setBounds(0, 0, 500, 700);
       
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(0, 0, 500, 700);
		imagePanel.setLayout(new OverlayLayout(imagePanel));

		
		imagePanel.add(logImage);

		
		JLabel title = new JLabel("My Notepad");
		title.setFont(new Font("MV Boli", Font.PLAIN,30));
		title.setForeground((Color.white));
		title.setForeground(new Color(242, 172, 97));
		title.setBounds(170, 80, 200, 30);
		loginPanel.add(title);


		JLabel signLabel = new JLabel("USERNAME");
		signLabel.setBounds(100, 190, 150, 30);
		signLabel.setForeground((Color.white));
		loginPanel.add(signLabel);

		
		JTextField signInput = new JTextField();
		signInput.setBounds(100, 225, 300, 40);
		signInput.setBackground((Color.white));
		signInput.setForeground((Color.black));
		signInput.setFont(new Font("SansSerif", Font.PLAIN,16));
		loginPanel.add(signInput);

		
		JLabel passLabel = new JLabel("PASSWORD");
		passLabel.setBounds(100, 285, 150, 30);
		passLabel.setForeground((Color.white));
		loginPanel.add(passLabel);

		
		JPasswordField passInput = new JPasswordField();
		passInput.setBounds(100, 320, 300, 40);
		passInput.setBackground((Color.white));
		passInput.setForeground((Color.black));
		passInput.setFont(new Font("SansSerif", Font.PLAIN,16));
		loginPanel.add(passInput);

		
		JCheckBox showPass = new JCheckBox("Show Password");
		showPass.setBounds(100, 370, 200, 40);
		showPass.setFont(new Font("SansSerif", Font.PLAIN, 12));
		showPass.setForeground((Color.white));
		showPass.setBorderPainted(false);
		showPass.setFocusPainted(false);
		showPass.setBackground(new Color(30, 30, 35));
		showPass.addItemListener(loginController.showPasswordBtn(passInput));
		loginPanel.add(showPass);


		JButton loginBtn = new JButton("LOGIN");
		loginBtn.setBounds(150, 450, 200, 40);
		loginBtn.setBackground(new Color(242, 172, 97));
		loginBtn.setForeground((Color.white));
		loginBtn.setBorderPainted(false);
		loginBtn.setFocusPainted(false);
		loginBtn.addActionListener(loginController.signInBtn(GuiFrame, signInput, passInput));
		loginPanel.add(loginBtn);


		JButton signInBtn = new JButton ("SIGN IN");
		signInBtn.setBounds(130, 550, 100, 40);
		signInBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
		signInBtn.setForeground((Color.white));
		signInBtn.setBorderPainted(false);
		signInBtn.setFocusPainted(false);
		signInBtn.setBackground(new Color(30, 30, 35));
		loginPanel.add(signInBtn);

		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(250, 550, 5, 50);
		loginPanel.add(separator);

		
		JButton createAccBtn = new JButton ("REGISTER");
		createAccBtn.setBounds(270, 550, 120, 40);
		createAccBtn.setBorderPainted(false);
		createAccBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
		createAccBtn.setFocusPainted(false);
		createAccBtn.setForeground((Color.white));
		createAccBtn.setBackground(new Color(30, 30, 35));
		createAccBtn.addActionListener(loginController.getRegisterButton(GuiFrame));
		loginPanel.add(createAccBtn);

		
		GuiFrame.getContentPane().add(imagePanel);
		loginPanel.setVisible(true);
		GuiFrame.add(loginPanel);
	}
}