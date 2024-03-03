package project.finals.dashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ManagerController {

    private ManagerView managerView;

    public ManagerController(ManagerView managerView) {
        this.managerView = managerView;
    }

    
    public ActionListener addPremiumBtn(JFrame guiFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter a name for premium
                String premiumName = JOptionPane.showInputDialog("Enter name for premium:");
    
                if (premiumName != null && !premiumName.isEmpty()) {
                    // Read user.dat file and check if there is a matching name
                    boolean nameFound = false;
                    File usersFile = new File("database/user.dat");
    
                    try {
                        // Read the existing data
                        BufferedReader reader = new BufferedReader(new FileReader(usersFile));
                        StringBuilder sb = new StringBuilder();
                        String line;
    
                        while ((line = reader.readLine()) != null) {
                            // Split the line into name, username, and password using tab as the delimiter
                            String[] userData = line.split("\t");
                            if (userData.length >= 1 && userData[0].equalsIgnoreCase(premiumName)) {
                                nameFound = true;
    
                                // Append "prem" to the name
                                userData[0] = "prem" + userData[0];
    
                                // Rename the existing user folder to the premium name
                                File userFolder = new File("database/" + premiumName);
                                File premiumFolder = new File("database/" + userData[0]);
                                if (userFolder.exists() && userFolder.isDirectory()) {
                                    userFolder.renameTo(premiumFolder);
                                }
                            }
                            // Rebuild the line with potential modifications
                            sb.append(String.join("\t", userData)).append("\n");
                        }
    
                        reader.close();
    
                        // Write the updated data back to user.dat
                        BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile));
                        writer.write(sb.toString());
                        writer.close();
    
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
    
                    if (nameFound) {
                        // Display the premiumized name
                        JOptionPane.showMessageDialog(null, "New Premium User: " + premiumName);
                    } else {
                        JOptionPane.showMessageDialog(null, "Name not found in user.dat.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid premium name.");
                }
            }
        };
    }
    public ActionListener removePremiumBtn(JFrame guiFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter the name of the user to remove premium
                String userNameToRemovePremium = JOptionPane.showInputDialog("Enter name of user to remove premium:");
    
                if (userNameToRemovePremium != null && !userNameToRemovePremium.isEmpty()) {
                    // Read user.dat file and check if there is a matching name
                    boolean nameFound = false;
                    File usersFile = new File("database/user.dat");
    
                    try {
                        // Read the existing data
                        BufferedReader reader = new BufferedReader(new FileReader(usersFile));
                        StringBuilder sb = new StringBuilder();
                        String line;
    
                        while ((line = reader.readLine()) != null) {
                            // Split the line into name, username, and password using tab as the delimiter
                            String[] userData = line.split("\t");
                            if (userData.length >= 1 && userData[0].equalsIgnoreCase("prem" + userNameToRemovePremium)) {
                                nameFound = true;
    
                                // Remove "prem" from the name
                                userData[0] = userNameToRemovePremium;
    
                                // Rename the existing user folder to the original name
                                File premiumFolder = new File("database/prem" + userNameToRemovePremium);
                                File userFolder = new File("database/" + userNameToRemovePremium);
                                if (premiumFolder.exists() && premiumFolder.isDirectory()) {
                                    premiumFolder.renameTo(userFolder);
                                }
                            }
                            // Rebuild the line with potential modifications
                            sb.append(String.join("\t", userData)).append("\n");
                        }
    
                        reader.close();
    
                        // Write the updated data back to user.dat
                        BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile));
                        writer.write(sb.toString());
                        writer.close();
    
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
    
                    if (nameFound) {
                        // Display the de-premiumized name
                        JOptionPane.showMessageDialog(null, "Deactivated Premium: " + userNameToRemovePremium);
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found in user.dat.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid user name.");
                }
            }
        };
    }
}
