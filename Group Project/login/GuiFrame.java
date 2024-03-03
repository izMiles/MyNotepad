package project.finals.login;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import project.finals.login.LoginView;

public class GuiFrame extends JFrame{

	public LoginView loginView;

	public GuiFrame(){

		setTitle("My Notepad");
		setSize(1000, 700);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logoIcon = new ImageIcon("image/logo.png");
		setIconImage(logoIcon.getImage());
		loginView = new LoginView();

		loginView.loginGui(this);
		setVisible(true);
	}
}