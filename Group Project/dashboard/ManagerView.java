package project.finals.dashboard;

import javax.swing.*;
import project.finals.dashboard.DashboardController;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ManagerView{
    private DashboardController  dashboardController;
    private JPanel userinfoPanel = new JPanel();
    private ManagerController managerController;

    public ManagerView(DashboardController dashboardController, ManagerController managerController){
        this.dashboardController = dashboardController;
        this.managerController = managerController;
    }

	public void ManagerGui(JFrame GuiFrame, String getName) {

        managerController = new ManagerController(this);

		JPanel managerPanel = new JPanel();
		managerPanel.setBounds(500, 0, 500, 700);
		managerPanel.setBackground(new Color(30, 30, 35));
		managerPanel.setLayout(null);

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


        JLabel managerImage = new JLabel();
        managerImage.setIcon(regImage);
        managerImage.setOpaque(true);
        managerImage.setBounds(0, 0, 500, 700);
       
		
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

                // Check if the user.dat file exists
                if (userDatFile.exists() && userDatFile.isFile()) {
                    try (BufferedReader userReader = new BufferedReader(new FileReader(userDatFile))) {

                        // Read and display usernames from user.dat
                        userinfoPanel.add(new JLabel("Users:"));
                        displayUsernames(userReader);

                    } catch (IOException ex) {
                        ex.printStackTrace(); // Handle the exception appropriately in your application
                    }
                } else {
                    JLabel noFileLabel = new JLabel("User.dat file not found");
                    noFileLabel.setForeground(Color.white);
                    userinfoPanel.add(noFileLabel);
                }

                imagePanel.add(userInfoScrollPane);
                userinfoPanel.revalidate();
                userinfoPanel.repaint();
            }

            private void displayUsernames(BufferedReader reader) throws IOException {
                String line;
                int lineNumber = 1;

                // Read each line from the user.dat file
                while ((line = reader.readLine()) != null) {
                    // Split the line using a tab as the delimiter
                    String[] userInfoParts = line.split("\t");

                   
                    if (userInfoParts.length >= 0) {
                        // Create a formatted string with the username
                        String formattedUsername = String.format("%d. Name: %s", lineNumber, userInfoParts[0]);

                        // Create a JLabel with the formatted username
                        JLabel usernameLabel = new JLabel(formattedUsername);
                        usernameLabel.setForeground(Color.white);
                        usernameLabel.setFont(new Font("Italic", Font.PLAIN, 14));
                        userinfoPanel.add(usernameLabel);

                        lineNumber++;
                    }
                }
            }
        });
        
        imagePanel.add(managerImage);

        
        JLabel managerTitle = new JLabel("Welcome to");
        managerTitle.setBounds(100, 45, 200, 40);
        managerTitle.setFont(new Font("Italic", Font.PLAIN, 20));
        managerTitle.setForeground((Color.white));
        managerPanel.add(managerTitle);


        JLabel myNotepadtitle = new JLabel("My Notepad");
        myNotepadtitle.setBounds(222, 40, 200, 50);
        myNotepadtitle.setFont(new Font("MV Boli", Font.PLAIN, 28));
        myNotepadtitle.setForeground(new Color(242, 172, 97));
        managerPanel.add(myNotepadtitle);


        JLabel userName = new JLabel("Hello " + getName);
        userName.setBounds(40, 130, 250, 50);
        userName.setFont(new Font("Italic", Font.PLAIN, 24));
        userName.setForeground((Color.white));
        managerPanel.add(userName);


        JLabel header3 = new JLabel("Manage Users: ");
        header3.setBounds(40, 200, 350, 50);
        header3.setForeground((Color.white));
        header3.setFont(new Font("Italic", Font.PLAIN, 20));
        managerPanel.add(header3);


        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle window closing event if needed
            }
        };

    

        JButton addPremiumBtn = new JButton("Add Premium");
       	addPremiumBtn.setBounds(150, 320, 200, 40);
       	addPremiumBtn.setFont(new Font("Italic", Font.BOLD, 20));
        addPremiumBtn.setForeground((Color.white));
        addPremiumBtn.setBackground(new Color(242, 172, 97));
        addPremiumBtn.setBorderPainted(false);
        addPremiumBtn.setFocusPainted(false);
        addPremiumBtn.addActionListener(managerController.addPremiumBtn(GuiFrame));
        managerPanel.add(addPremiumBtn);


        JButton removePremiumBtn = new JButton("Remove Premium");
        removePremiumBtn.setBounds(150, 390, 200, 40);
        removePremiumBtn.setBackground(new Color(242, 172, 97));
        removePremiumBtn.setFont(new Font("Italic", Font.BOLD, 20));
        removePremiumBtn.setForeground((Color.white));
        removePremiumBtn.setBorderPainted(false);
        removePremiumBtn.setFocusPainted(false);
        removePremiumBtn.addActionListener(managerController.removePremiumBtn(GuiFrame));
        managerPanel.add(removePremiumBtn);


        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.setBounds(300, 600, 150, 30);
        logoutBtn.setFont(new Font("SansSerif", Font.PLAIN, 20));
        logoutBtn.setBackground(new Color(30, 30, 35));
        logoutBtn.setForeground((Color.white));
        logoutBtn.setBorderPainted(false);
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(dashboardController.getLogoutBtn(GuiFrame));
        managerPanel.add(logoutBtn);


        GuiFrame.repaint();
        GuiFrame.getContentPane().add(imagePanel);
		GuiFrame.add(managerPanel);
		
	}
}