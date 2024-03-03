package project.finals.fileTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import project.finals.dashboard.DashboardView;
import project.finals.fileTask.LoadFileView;

public class LoadFileController {

    private LoadFileView loadFileView;
    private DashboardView dashboardView;

    public LoadFileController(LoadFileView loadFileView) {
        this.loadFileView = loadFileView;
        this.dashboardView = new DashboardView();
    }

    public void openLoadFileView(JFrame GuiFrame, String getName) {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loadFileView.hideLoadFileTab();
                SwingUtilities.invokeLater(() -> dashboardView.dashboardGui(GuiFrame, getName));
            }
        };
        loadFileBtn(windowListener, getName);  // Call the method directly in the same class
    }
    public void loadFileBtn(WindowListener windowListener, String userName) {
        String notepadTitle;
    
        do {
            notepadTitle = JOptionPane.showInputDialog(loadFileView.loadFileTab, "Enter Notepad Title:");
    
            if (notepadTitle == null) {
                // User clicked cancel, handle it as needed (e.g., go back or exit)
                return;
            }
            if (notepadTitle.trim().isEmpty()) {
                JOptionPane.showMessageDialog(loadFileView.loadFileTab, "Title cannot be blank. Please enter a valid title.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (notepadTitle.trim().isEmpty());
        readAndDisplayFileContent(userName, notepadTitle);
    }
    

    public void readAndDisplayFileContent(String userName, String notepadTitle) {
        String directoryPath = "database/" + userName;
        String fileName = notepadTitle.replaceAll("[^a-zA-Z0-9]", "_") + ".txt";
        String filePath = directoryPath + File.separator + fileName;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
    
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
    
            // Pass the content to the LoadFileView's bodyArea
            loadFileView.loadFileGui(loadFileView.getLoadFileTab(), userName, content.toString(), notepadTitle);
    
        } catch (IOException ex) {
            ex.printStackTrace();
            // Access loadFileTab directly
            JOptionPane.showMessageDialog(loadFileView.getLoadFileTab(), "Error reading file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    

   
