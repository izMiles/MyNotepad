package project.finals.fileTask;

import javax.swing.*;
import java.io.File;

public class DeleteFileController {

    public void deleteFile(String userName) {

        String notepadTitle;

        do {
            notepadTitle = JOptionPane.showInputDialog(null, "Enter Notepad Title:");

            if (notepadTitle == null) {
                // User clicked cancel, handle it as needed (e.g., go back or exit)
                return;
            }
            if (notepadTitle.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Title cannot be blank. Please enter a valid title.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (notepadTitle.trim().isEmpty());

        String directoryPath = "database/" + userName;
        String fileName = notepadTitle.replaceAll("[^a-zA-Z0-9]", "_") + ".txt";
        String filePath = directoryPath + File.separator + fileName;

        File fileToDelete = new File(filePath);

        if (fileToDelete.exists()) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete the file?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                if (fileToDelete.delete()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "File deleted successfully.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error deleting file.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "File not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
