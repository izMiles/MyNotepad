package project.finals.fileTask;

import javax.swing.*;

import project.finals.fileTask.CreateFileController;
import project.finals.fileTask.CreateFileView;

import java.awt.*;
import java.awt.event.*;

public class LoadFileView {

    public JFrame loadFileTab;
    private JTextArea bodyArea;
    private LoadFileController loadFileController;
    private CreateFileController createFileController;

    public LoadFileView(LoadFileController loadFileController, CreateFileView createFileViewInstance) {
        this.loadFileController = loadFileController;
        this.createFileController = new CreateFileController(createFileViewInstance, this); // Pass both instances
    }

     
    
   public void loadFileGui(JFrame loadFileTab, String userName, String content, String notepadTitle) {

        loadFileController = new LoadFileController(this);
  

        loadFileTab = new JFrame();
        loadFileTab.setTitle(notepadTitle);
        loadFileTab.setSize(800, 600);
        loadFileTab.setLayout(new BorderLayout());
        loadFileTab.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(200, 100, 40), 5));
        loadFileTab.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setPreferredSize(new Dimension(800, 35));
        headerPanel.setBackground(new Color(219, 211, 204));
        loadFileTab.add(headerPanel, BorderLayout.NORTH);

        bodyArea = new JTextArea();
        bodyArea.setText(content);
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

        JScrollPane scrollPane = new JScrollPane(bodyArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        loadFileTab.add(scrollPane, BorderLayout.CENTER);

        JButton saveBtn = new JButton("SAVE");
    saveBtn.setBackground(new Color(255, 196, 161));
    saveBtn.setFocusPainted(false);
    headerPanel.add(saveBtn);
    saveBtn.addActionListener(createFileController.createNoteBtn(loadFileTab, bodyArea, userName));
    loadFileTab.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            hideLoadFileTab();
        }
    });
    loadFileTab.setVisible(true);
}
    

   

    public void hideLoadFileTab() {
        if (loadFileTab != null) {
            loadFileTab.setVisible(false);
        }
    }
    public JFrame getLoadFileTab() {
    return loadFileTab;
}
}
