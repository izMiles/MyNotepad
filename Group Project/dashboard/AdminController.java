package project.finals.dashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AdminController {

    private AdminView adminView;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;
    }

    public ActionListener promptForUserNameAndDelete(JFrame GuiFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userNameToDelete = JOptionPane.showInputDialog("Enter name of user to delete:");

                if (userNameToDelete != null && !userNameToDelete.isEmpty()) {
                    // Assuming 'database' is the folder where user folders are stored
                    File userFolder = new File("database/" + userNameToDelete);

                    if (userFolder.exists() && userFolder.isDirectory()) {
                        deleteUserData(userFolder, userNameToDelete);
                        JOptionPane.showMessageDialog(null, "User deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid user.");
                }
            }
        };
    }

    private void deleteUserData(File folder, String userName) {
        // Delete user data from users.dat
        File usersFile = new File("database/user.dat");
        if (usersFile.exists()) {
            try {
                // Read the existing data
                BufferedReader reader = new BufferedReader(new FileReader(usersFile));
                StringBuilder sb = new StringBuilder();
                String line;
    
                while ((line = reader.readLine()) != null) {
                    // Split the line into name, username, and password using tab as the delimiter
                    String[] userData = line.split("\t");
                    if (userData.length == 3 && !userData[1].equalsIgnoreCase(userName)) {
                        // Append only if the username is not the one being deleted
                        sb.append(line).append("\n");
                    }
                }
    
                reader.close();
    
                // Write the updated data back to users.dat
                BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile));
                writer.write(sb.toString());
                writer.close();
    
                System.out.println("User data removed from user.dat");
    
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("user.dat file not found!");
        }
    
        // Delete user folder
        deleteFolder(folder);
    }
    

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }

    public ActionListener addManagerBtn(JFrame guiFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter name, username, and password for the manager
                String mgrName = JOptionPane.showInputDialog("Enter manager name:");
                String mgrUsername = JOptionPane.showInputDialog("Enter manager username:");
                String mgrPassword = JOptionPane.showInputDialog("Enter manager password:");

                if (mgrName != null && !mgrName.isEmpty() &&
                    mgrUsername != null && !mgrUsername.isEmpty() &&
                    mgrPassword != null && !mgrPassword.isEmpty()) {

                    // Add "mgr" in the starting of the name
                    mgrName = "mgr" + mgrName;
                    mgrUsername = "mgr" + mgrUsername;
                    // Create or open the manager.dat file
                    File managerFile = new File("database/manager.dat");

                    try {
                        FileWriter fileWriter = new FileWriter(managerFile, true);
                        BufferedWriter writer = new BufferedWriter(fileWriter);

                        // Append the manager data to the manager.dat file
                        writer.write(mgrName + "\t" + mgrUsername + "\t" + mgrPassword);
                        writer.newLine();

                        writer.close();
                        fileWriter.close();

                        JOptionPane.showMessageDialog(null, "Manager added successfully.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error adding manager.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid manager data.");
                }
            }
        };
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
                        JOptionPane.showMessageDialog(null, "Premium User: " + premiumName);
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
    public ActionListener removeManagerBtn(JFrame guiFrame) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter the name of the manager to remove
                String managerNameToRemove = JOptionPane.showInputDialog("Enter manager name to remove:");
    
                if (managerNameToRemove != null && !managerNameToRemove.isEmpty()) {
                    // Read manager.dat file and check if there is a matching name
                    boolean nameFound = false;
                    File managerFile = new File("database/manager.dat");
    
                    try {
                        // Read the existing data
                        BufferedReader reader = new BufferedReader(new FileReader(managerFile));
                        StringBuilder sb = new StringBuilder();
                        String line;
    
                        while ((line = reader.readLine()) != null) {
                            // Split the line into name, username, and password using tab as the delimiter
                            String[] managerData = line.split("\t");
                            if (managerData.length >= 1 && managerData[0].equalsIgnoreCase(managerNameToRemove)) {
                                nameFound = true;
                            } else {
                                // Append the data for managers other than the one being removed
                                sb.append(line).append("\n");
                            }
                        }
    
                        reader.close();
    
                        // Write the updated data back to manager.dat
                        BufferedWriter writer = new BufferedWriter(new FileWriter(managerFile));
                        writer.write(sb.toString());
                        writer.close();
    
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
    
                    if (nameFound) {
                        // Display a message indicating successful removal
                        JOptionPane.showMessageDialog(null, "Manager removed successfully: " + managerNameToRemove);
                    } else {
                        JOptionPane.showMessageDialog(null, "Manager not found in manager.dat.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid manager name.");
                }
            }
        };
    }
}
