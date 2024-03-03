package project.finals.fileTask;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {

    public void createNoteHandling(JFrame createFileTab, String directoryPath, String fileName, String textToSave ) {
        // Null checks for parameters
        if (createFileTab == null || directoryPath == null || fileName == null || textToSave == null) {
            JOptionPane.showMessageDialog(null, "Invalid parameters provided.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        File fileToSave = new File(directoryPath, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
            writer.write(textToSave);
            JOptionPane.showMessageDialog(createFileTab, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException exception) {
            // Log or display more information about the exception
            JOptionPane.showMessageDialog(createFileTab, "Error saving file: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
