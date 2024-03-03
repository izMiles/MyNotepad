package project.finals.fileTask;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class CreateFileView {

    private JFrame createFileTab;
    private JTextArea bodyArea;
    private JLabel wordCountLabel;
    private CreateFileController createFileController;
    private boolean wordLimitReached;

    public CreateFileView(CreateFileController createFileController) {
        this.createFileController = createFileController;
        
    }

    public void createFileGui(WindowListener windowListener, String userName) {

        createFileTab = new JFrame();

        // Prompt user for notepad title
        String notepadTitle;
        do {
            notepadTitle = JOptionPane.showInputDialog(createFileTab, "Enter Notepad Title:");

            if (notepadTitle == null) {
                // User clicked cancel, handle it as needed (e.g., go back or exit)
                return;
            }
            if (notepadTitle.trim().isEmpty()) {
                JOptionPane.showMessageDialog(createFileTab, "Title cannot be blank. Please enter a valid title.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (notepadTitle.trim().isEmpty());

        // Set the title of the JFrame
        createFileTab.setTitle(notepadTitle);
        createFileTab.setSize(800, 600);
        createFileTab.setLayout(new BorderLayout());
        createFileTab.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(200, 100, 40), 5));
        createFileTab.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setPreferredSize(new Dimension(800, 35));
        headerPanel.setBackground(new Color(219, 211, 204));
        createFileTab.add(headerPanel, BorderLayout.NORTH);

        wordCountLabel = new JLabel("Word Count: 0");
        headerPanel.add(wordCountLabel);

        bodyArea = new JTextArea();
        bodyArea.setLineWrap(true);
        bodyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK);
        bodyArea.getInputMap().put(keyStroke, "shiftEnter");
        bodyArea.getActionMap().put("shiftEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bodyArea.append("\n");
            }
        });

        // Add a DocumentListener to track changes in the JTextArea
        bodyArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateWordCount(userName);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateWordCount(userName);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateWordCount(userName);
            }
        });

        JScrollPane scrollPane = new JScrollPane(bodyArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        createFileTab.add(scrollPane, BorderLayout.CENTER);

        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBackground(new Color(255, 196, 161));
        saveBtn.setFocusPainted(false);
        headerPanel.add(saveBtn);
        saveBtn.addActionListener(createFileController.createNoteBtn(createFileTab, bodyArea, userName));

        createFileTab.setVisible(true);

        if (windowListener != null) {
            createFileTab.addWindowListener(windowListener);
        }
    }

    private void updateWordCount(String userName) {
        String text = bodyArea.getText();
        String[] words = text.split("\\s+");
        int wordCount = words.length;
    
        wordCountLabel.setText("Word Count: " + wordCount);
    
        // Check if the user's name starts with "prem" and limit words accordingly
        if (!userName.toLowerCase().startsWith("prem")) {
            // Limit to 1000 words
            int maxWords = 1000;
            if (wordCount > maxWords) {
                // Set the flag to true
                if (!wordLimitReached) {
                    wordLimitReached = true;
    
                    // Show error message and remove the last entered word
                    SwingUtilities.invokeLater(() -> {
                        showError("Maximum number of words reached. You cannot input more words.");
    
                        int lastSpaceIndex = text.lastIndexOf(" ", text.length() - 2);
                        if (lastSpaceIndex != -1) {
                            bodyArea.setText(text.substring(0, lastSpaceIndex + 1));
                        } else {
                            bodyArea.setText("");
                        }
                    });
                }
            } else {
                // Reset the flag if the word count is below the limit
                wordLimitReached = false;
            }
        }
    }
    private void showError(String message) {
        JOptionPane.showMessageDialog(createFileTab, message, "Limit Exceeded", JOptionPane.WARNING_MESSAGE);
    }

    public void hideCreateFileTab() {
        if (createFileTab != null) {
            createFileTab.setVisible(false);
        }
    }
}
