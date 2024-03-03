package project.finals.register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Register {

    
    // Checks if the User Already Exist in the File.
    public boolean usernameAlreadyExist(String userName) {

        try(BufferedReader read = new BufferedReader(new FileReader("database\\user.dat"))) {

            String line;

            while ((line = read.readLine()) != null) {

                String[] data = line.split(" \t ");

                if(data.length >= 2 && data[1].trim().equals(userName)) {

                    return true;

                }
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return false;     

    }

    
    public boolean nameAlreadyExist(String name) {

        try(BufferedReader read = new BufferedReader(new FileReader("database\\user.dat"))) {

            String line;

            while ((line = read.readLine()) != null) {

                String[] data = line.split("\t");

                if(data.length >= 2 && data[0].trim().equals(name)) {

                    return true;

                }
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return false;     

    }

    // Saving User's Registration Data to the File.
	public void registerFileHandling(String name, String userName, String password) {
     
        if (usernameAlreadyExist(userName)) {
     
            JOptionPane.showMessageDialog(null, "Username already exists! Please choose another username.");
     
        } else if (nameAlreadyExist(name)) {
     
            JOptionPane.showMessageDialog(null, "Name already exists! Please choose another name.");
     
        } else {
     
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("database\\user.dat", true))) {
     
                writer.write(name + "\t" + userName + "\t" + password);
                writer.newLine();
     
                JOptionPane.showMessageDialog(null, "You've Successfully Registered!");
     
            } catch (IOException e) {
     
                e.printStackTrace();
            }
        }
    }

        // Helper method to create a folder with the given name in the "database" directory
     public void createFolder(String folderName) {

        File folder = new File("database\\" + folderName);

        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Folder created: " + folder.getAbsolutePath());
            } else {
                System.out.println("Failed to create folder!");
            }
        } else {
            System.out.println("Folder already exists!");
        }
    }

}

     
    