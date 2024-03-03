package project.finals.dashboard;

import javax.swing.*;

import project.finals.dashboard.DashboardController;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class AdminView{
    private DashboardController dashboardController;
    private AdminController adminController;
    private JButton deleteUsersBtn; 

    public AdminView(DashboardController dashboardController, AdminController adminController) {
        this.dashboardController = dashboardController;
        this.adminController = adminController;
    }

    // Getter for deleteUsersBtn
    public JButton getDeleteUsersBtn() {
        return deleteUsersBtn;
    }


    private JPanel userinfoPanel = new JPanel();


	public void AdminGui(JFrame GuiFrame, String getName) {

		adminController = new AdminController(this);

		JPanel adminPanel = new JPanel();
		adminPanel.setBounds(500, 0, 500, 700);
		adminPanel.setBackground(new Color(30, 30, 35));
		adminPanel.setLayout(null);

		ImageIcon regImage = new ImageIcon(getClass().getResource("/image/image.png"));


        JButton viewusersBtn = new JButton("VIEW USERS");
        viewusersBtn.setFont(new Font("Italic", Font.BOLD, 14));
        viewusersBtn.setForeground(Color.white);
        viewusersBtn.setBackground(new Color(242, 172, 97));
        viewusersBtn.setBorderPainted(false);
        viewusersBtn.setFocusPainted(false);
        viewusersBtn.setBounds(30, 30, 120, 40);


        userinfoPanel.setBounds(50, 100, 400, 500);
        userinfoPanel.setBackground(new Color(242, 172, 97));
        userinfoPanel.setLayout(new BoxLayout(userinfoPanel, BoxLayout.Y_AXIS));


        JScrollPane userInfoScrollPane = new JScrollPane(userinfoPanel);
        userInfoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        userInfoScrollPane.setBounds(50, 100, 400, 500);


        JLabel adminImage = new JLabel();
        adminImage.setIcon(regImage);
        adminImage.setOpaque(true);
        adminImage.setBounds(0, 0, 500, 700);
       
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(0, 0, 500, 700);
		imagePanel.setLayout(null);
        imagePanel.add(viewusersBtn);


        viewusersBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userinfoPanel.removeAll(); // Clear the existing components
        
                String databaseFolderPath = "database";
                File databaseFolder = new File(databaseFolderPath);
                File userDatFile = new File(databaseFolder, "user.dat");
                File managerDatFile = new File(databaseFolder, "manager.dat");
        
                // Check if both user.dat and manager.dat files exist
                if (userDatFile.exists() && userDatFile.isFile() && managerDatFile.exists() && managerDatFile.isFile()) {
                    try (BufferedReader userReader = new BufferedReader(new FileReader(userDatFile));
                         BufferedReader managerReader = new BufferedReader(new FileReader(managerDatFile))) {
        
                        // Read and display contents of manager.dat
                        userinfoPanel.add(new JLabel("Managers:"));
                        displayFileContents(managerReader);
        
                        // Read and display contents of user.dat
                        userinfoPanel.add(new JLabel("Users:"));
                        displayFileContents(userReader);
        
                    } catch (IOException ex) {
                        ex.printStackTrace(); // Handle the exception appropriately in your application
                    }
                } else {
                    JLabel noFilesLabel = new JLabel("One or both files not found");
                    noFilesLabel.setForeground(Color.white);
                    userinfoPanel.add(noFilesLabel);
                }
        
                imagePanel.add(userInfoScrollPane);
                userinfoPanel.revalidate();
                userinfoPanel.repaint();
            }
        
            private void displayFileContents(BufferedReader reader) throws IOException {
                String line;
                int lineNumber = 1;
        
                // Read each line from the file
                while ((line = reader.readLine()) != null) {
                    // Split the line using a tab as the delimiter
                    String[] userInfoParts = line.split("\t");
        
                    // Check if the line has at least three parts (name, username, password)
                    if (userInfoParts.length >= 3) {
                        // Create a formatted string with the user information
                        String formattedUserInfo = String.format("%d. Name: %s, Username: %s, Password: %s",
                                lineNumber, userInfoParts[0], userInfoParts[1], userInfoParts[2]);
        
                        // Create a JLabel with the formatted user information
                        JLabel userInfoLabel = new JLabel(formattedUserInfo);
                        userInfoLabel.setForeground(Color.white);
                        userInfoLabel.setFont(new Font("Italic", Font.PLAIN, 14));
                        userinfoPanel.add(userInfoLabel);
        
                        lineNumber++;
                    }
                }
            }
        });
        
        imagePanel.add(adminImage);

        
        JLabel adminTitle = new JLabel("Welcome to");
        adminTitle.setBounds(100, 45, 200, 40);
        adminTitle.setFont(new Font("Italic", Font.PLAIN, 20));
        adminTitle.setForeground((Color.white));
        adminPanel.add(adminTitle);


        JLabel myNotepadtitle = new JLabel("My Notepad");
        myNotepadtitle.setBounds(222, 40, 200, 50);
        myNotepadtitle.setFont(new Font("MV Boli", Font.PLAIN, 28));
        myNotepadtitle.setForeground(new Color(242, 172, 97));
        adminPanel.add(myNotepadtitle);


        JLabel userName = new JLabel("Hello Admin ");
        userName.setBounds(40, 130, 250, 50);
        userName.setFont(new Font("Italic", Font.PLAIN, 24));
        userName.setForeground((Color.white));
        adminPanel.add(userName);


        JLabel header3 = new JLabel("Manage Users: ");
        header3.setBounds(40, 200, 350, 50);
        header3.setForeground((Color.white));
        header3.setFont(new Font("Italic", Font.PLAIN, 20));
        adminPanel.add(header3);


        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle window closing event if needed
            }
        };

        
        JButton addManagerBtn = new JButton("Add Manager");
        addManagerBtn.setBounds(20, 320, 200, 40);
        addManagerBtn.setBackground(new Color(242, 172, 97));
        addManagerBtn.setFont(new Font("Italic", Font.BOLD, 20));
        addManagerBtn.setForeground((Color.white));
        addManagerBtn.setBorderPainted(false);
        addManagerBtn.setFocusPainted(false);
        addManagerBtn.addActionListener(adminController.addManagerBtn(GuiFrame));
        adminPanel.add(addManagerBtn);


        JButton addPremiumBtn = new JButton("Add Premium");
       	addPremiumBtn.setBounds(250, 320, 200, 40);
       	addPremiumBtn.setFont(new Font("Italic", Font.BOLD, 20));
        addPremiumBtn.setForeground((Color.white));
        addPremiumBtn.setBackground(new Color(242, 172, 97));
        addPremiumBtn.setBorderPainted(false);
        addPremiumBtn.setFocusPainted(false);
        addPremiumBtn.addActionListener(adminController.addPremiumBtn(GuiFrame));
        adminPanel.add(addPremiumBtn);
        

        JButton removePremiumBtn = new JButton("Remove Premium");
        removePremiumBtn.setBounds(250, 390, 200, 40);
        removePremiumBtn.setBackground(new Color(242, 172, 97));
        removePremiumBtn.setFont(new Font("Italic", Font.BOLD, 20));
        removePremiumBtn.setForeground((Color.white));
        removePremiumBtn.setBorderPainted(false);
        removePremiumBtn.setFocusPainted(false);
        removePremiumBtn.addActionListener(adminController.removePremiumBtn(GuiFrame));
        adminPanel.add(removePremiumBtn);


        JButton removeManagerBtn = new JButton("Remove Manager");
        removeManagerBtn.setBounds(20, 390, 200, 40);
        removeManagerBtn.setBackground(new Color(242, 172, 97));
        removeManagerBtn.setFont(new Font("Italic", Font.BOLD, 20));
        removeManagerBtn.setForeground((Color.white));
        removeManagerBtn.setBorderPainted(false);
        removeManagerBtn.setFocusPainted(false);
        removeManagerBtn.addActionListener(adminController.removeManagerBtn(GuiFrame));
        adminPanel.add(removeManagerBtn);


        JButton deleteUsersBtn = new JButton("Remove User");
        deleteUsersBtn.setBounds(150, 460, 200, 40);
        deleteUsersBtn.setFont(new Font("Italic", Font.BOLD, 20));
        deleteUsersBtn.setForeground((Color.white));
        deleteUsersBtn.setBackground(new Color(242, 172, 97));
        deleteUsersBtn.setBorderPainted(false);
        deleteUsersBtn.setFocusPainted(false);
        deleteUsersBtn.addActionListener(adminController.promptForUserNameAndDelete(GuiFrame));
        adminPanel.add(deleteUsersBtn);


        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.setBounds(300, 600, 150, 30);
        logoutBtn.setFont(new Font("SansSerif", Font.PLAIN, 20));
        logoutBtn.setBackground(new Color(30, 30, 35));
        logoutBtn.setForeground((Color.white));
        logoutBtn.setBorderPainted(false);
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(dashboardController.getLogoutBtn(GuiFrame));
        adminPanel.add(logoutBtn);


        GuiFrame.repaint();
        GuiFrame.getContentPane().add(imagePanel);
		GuiFrame.add(adminPanel);
		
	}
}