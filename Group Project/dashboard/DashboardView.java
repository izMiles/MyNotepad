package project.finals.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;


public class DashboardView{

	private DashboardController dashboardController;
    private JPanel imagePanel = new JPanel();
    private JPanel fileNamesPanel = new JPanel();
    private JPanel dashboardPanel = new JPanel();

    
	public void dashboardGui(JFrame GuiFrame, String getName) {

        dashboardController = new DashboardController(this);

        dashboardPanel.setBounds(500, 0, 500, 700);
        dashboardPanel.setBackground(new Color(30, 30, 35));
        dashboardPanel.setLayout(null);

        ImageIcon regImage = new ImageIcon(getClass().getResource("/image/image.png"));

        JButton viewnotesBtn = new JButton("VIEW NOTES");
        viewnotesBtn.setFont(new Font("Italic", Font.BOLD, 14));
        viewnotesBtn.setForeground(Color.white);
        viewnotesBtn.setBackground(new Color(242, 172, 97));
        viewnotesBtn.setBorderPainted(false);
        viewnotesBtn.setFocusPainted(false);
        viewnotesBtn.setBounds(30, 30, 120, 40);

        fileNamesPanel.setBounds(50, 100, 400, 500);
        fileNamesPanel.setBackground(new Color(242, 172, 97));
        fileNamesPanel.setLayout(new BoxLayout(fileNamesPanel, BoxLayout.Y_AXIS));

        JScrollPane fileNamesScrollPane = new JScrollPane(fileNamesPanel);
        fileNamesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        fileNamesScrollPane.setBounds(50, 100, 400, 500);

        JLabel dashboardImage = new JLabel();
        dashboardImage.setIcon(regImage);
        dashboardImage.setOpaque(true);
        dashboardImage.setBounds(0, 0, 500, 700);

        imagePanel.setBounds(0, 0, 500, 700);
        imagePanel.setLayout(null);
        imagePanel.add(viewnotesBtn);

        viewnotesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            fileNamesPanel.removeAll(); // Clear the existing components
            
                String userName = getName;
                String userFolderPath = "database/" + userName;
                File userFolder = new File(userFolderPath);

                // Check if the user folder exists
                if (userFolder.exists() && userFolder.isDirectory()) {
                    // List all files in the user's folder
                    String[] fileNames = userFolder.list();

                    // Add JLabels for each file name with numbering
                    int fileNumber = 1;
                    for (String fileName : fileNames) {
                        JLabel fileNameLabel = new JLabel(fileNumber + ". " + fileName);
                        fileNameLabel.setForeground(Color.white);
                        fileNameLabel.setFont(new Font("Italic", Font.PLAIN, 14));
                        fileNamesPanel.add(fileNameLabel);
                        fileNumber++;
                    }
                } else {
                    JLabel noFilesLabel = new JLabel("No files found");
                    noFilesLabel.setForeground(Color.white);
                    fileNamesPanel.add(noFilesLabel);
                }

                imagePanel.add(fileNamesScrollPane);
                fileNamesPanel.revalidate();
                fileNamesPanel.repaint();
            }
        });

        
       
        imagePanel.add(dashboardImage); 

        JLabel dashboardTitle = new JLabel("Welcome to");
        dashboardTitle.setBounds(100, 45, 200, 40);
        dashboardTitle.setFont(new Font("Italic", Font.PLAIN, 20));
        dashboardTitle.setForeground((Color.white));
        dashboardPanel.add(dashboardTitle);


        JLabel myNotepadtitle = new JLabel("My Notepad");
        myNotepadtitle.setBounds(222, 40, 200, 50);
        myNotepadtitle.setFont(new Font("MV Boli", Font.PLAIN, 28));
        myNotepadtitle.setForeground(new Color(242, 172, 97));
        dashboardPanel.add(myNotepadtitle);

        JLabel userName = new JLabel("Hi " + getName);
        userName.setBounds(40, 130, 250, 50);
        userName.setFont(new Font("Italic", Font.PLAIN, 24));
        userName.setForeground((Color.white));
        dashboardPanel.add(userName);

        JLabel header3 = new JLabel("What do you wish to do today?");
        header3.setBounds(40, 200, 350, 50);
        header3.setForeground((Color.white));
        header3.setFont(new Font("Italic", Font.PLAIN, 20));
        dashboardPanel.add(header3);


        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle window closing event if needed
            }
        };

        
        JButton createFileBtn = new JButton("Create new file");
        createFileBtn.setBounds(150, 320, 200, 40);
        createFileBtn.setBackground(new Color(242, 172, 97));
        createFileBtn.setFont(new Font("Italic", Font.BOLD, 20));
        createFileBtn.setForeground((Color.white));
        createFileBtn.setBorderPainted(false);
        createFileBtn.setFocusPainted(false);
        createFileBtn.addActionListener(dashboardController.getCreateFile(windowListener, getName));
        dashboardPanel.add(createFileBtn);

       
        JButton loadFileBtn = new JButton("Load file");
       	loadFileBtn.setBounds(150, 390, 200, 40);
       	loadFileBtn.setFont(new Font("Italic", Font.BOLD, 20));
        loadFileBtn.setForeground((Color.white));
        loadFileBtn.setBackground(new Color(242, 172, 97));
        loadFileBtn.setBorderPainted(false);
        loadFileBtn.setFocusPainted(false);
        loadFileBtn.addActionListener(dashboardController.getLoadFile(windowListener, getName));
        dashboardPanel.add(loadFileBtn);


        JButton deleteFileBtn = new JButton("Delete file");
        deleteFileBtn.setBounds(150, 460, 200, 40);
        deleteFileBtn.setFont(new Font("Italic", Font.BOLD, 20));
        deleteFileBtn.setForeground((Color.white));
        deleteFileBtn.setBackground(new Color(242, 172, 97));
        deleteFileBtn.setBorderPainted(false);
        deleteFileBtn.setFocusPainted(false);
        deleteFileBtn.addActionListener(dashboardController.getDeleteFile(getName));
        dashboardPanel.add(deleteFileBtn);

        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.setBounds(300, 600, 150, 30);
        logoutBtn.setFont(new Font("SansSerif", Font.PLAIN, 20));
        logoutBtn.setBackground(new Color(30, 30, 35));
        logoutBtn.setForeground((Color.white));
        logoutBtn.setBorderPainted(false);
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(dashboardController.getLogoutBtn(GuiFrame));
        dashboardPanel.add(logoutBtn);

        

        GuiFrame.repaint();
        GuiFrame.getContentPane().add(imagePanel);
		GuiFrame.add(dashboardPanel);
		
	}

  
}