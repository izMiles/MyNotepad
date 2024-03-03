package project.finals.fileTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import project.finals.dashboard.DashboardView;


public class CreateFileController {

    private CreateFileView createFileView;
    private DashboardView dashboardView;
    private CreateFile createFile;
    private LoadFileView loadFileView;

    public CreateFileController(CreateFileView createFileView, LoadFileView loadFileView) {
        this.createFileView = createFileView;
        this.dashboardView = new DashboardView();
        this.createFile = new CreateFile();
        this.loadFileView = loadFileView; 
    }
    

    public void openCreateFileView(JFrame GuiFrame, String getName) {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                createFileView.hideCreateFileTab();
                SwingUtilities.invokeLater(() -> dashboardView.dashboardGui(GuiFrame, getName));
            }
        };

       
        
    }
    public ActionListener createNoteBtn(JFrame createFileTab, JTextArea bodyArea, String userName) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = createFileTab.getTitle();

                System.out.println("User Name: " + userName);

                if (userName != null) {
                    String sanitizedTitle = title.replaceAll("[^a-zA-Z0-9]", "_");
                    String textToSave = bodyArea.getText();
                    String directoryPath = "database/" + userName;

                    // Create a file within the user's folder
                    saveFile(directoryPath, sanitizedTitle, textToSave);
                } else {
                    // Handle the case when user name is null
                    JOptionPane.showMessageDialog(null, "User name not provided.");
                }
            }
        };
    }

    private void saveFile(String directoryPath, String fileName, String content) {
        File userFolder = new File(directoryPath);
    
        // Create the user's folder if it doesn't exist
        if (!userFolder.exists()) {
            userFolder.mkdirs();
        }
    
        // Create the file path within the user's folder
        String filePath = directoryPath + File.separator + fileName + ".txt";
        File fileToSave = new File(filePath);
    
        // Check if the file already exists
        if (fileToSave.exists()) {
            // File with the same title already exists, prompt user for another title
            String newFileName = JOptionPane.showInputDialog(null, "A file with the same title already exists. Please enter another title:");
            if (newFileName != null && !newFileName.trim().isEmpty()) {
                // Recursively call the saveFile method with the new title
                saveFile(directoryPath, newFileName.replaceAll("[^a-zA-Z0-9]", "_"), content);
            } else {
                // User canceled or entered an empty title, show a message
                JOptionPane.showMessageDialog(null, "File not saved. Please provide a valid title.");
            }
        } else {
            // Save the file if it doesn't exist
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(content);
                JOptionPane.showMessageDialog(null, "File saved successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving file!");
            }
        }
    }
    
}