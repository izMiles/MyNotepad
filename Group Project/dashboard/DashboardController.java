package project.finals.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File; 
import java.util.Arrays;
import project.finals.login.LoginView;
import project.finals.fileTask.CreateFileView;
import project.finals.fileTask.LoadFileView;
import project.finals.fileTask.CreateFileController; 
import project.finals.fileTask.LoadFileController;   
import project.finals.fileTask.DeleteFileController;


public class DashboardController {

    private DashboardView dashboardView;
    private LoginView loginView;
    private CreateFileView createFileView;
    private LoadFileView loadFileView;
    private LoadFileController loadFileController;
    private DeleteFileController deleteFileController;


    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        this.loginView = new LoginView();
        this.loadFileView = new LoadFileView(new LoadFileController(loadFileView), new CreateFileView(new CreateFileController(createFileView, loadFileView)));
        this.createFileView = new CreateFileView(new CreateFileController(createFileView, loadFileView));
        this.loadFileController = new LoadFileController(loadFileView);
        this.deleteFileController = new DeleteFileController();
    }

    public ActionListener getLogoutBtn(JFrame guiFrame) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiFrame.getContentPane().removeAll();
                guiFrame.repaint();
                loginView.loginGui(guiFrame);
            }
        };
    }

    public ActionListener getCreateFile(WindowListener windowListener, String getName) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = getName;
                createFileView.createFileGui(windowListener, userName);
            }
        };
    }

    public ActionListener getLoadFile(WindowListener windowListener, String getName) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = getName;
                loadFileController.loadFileBtn(windowListener, userName); 
            }
        };
    }

    public ActionListener getDeleteFile(String getName) {
    return new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String userName = getName;

            // Use an anonymous subclass of WindowAdapter
            WindowAdapter windowAdapter = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Handle window closing event if needed
                }
            };

            deleteFileController.deleteFile(userName);
        }
    };
}

}