package project.finals.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Login {

	public String loginFileHandling(String getUserName, String getPassword){

		try (BufferedReader reader = new BufferedReader(new FileReader("database\\user.dat"))){


            String line;

            while((line = reader.readLine()) != null){
                String [] parts = line.split("\t");
        
                if(parts.length == 3) {
                
                String name = parts[0].trim();
                String username = parts[1].trim();
                String password = parts[2].trim();

                if(getUserName.trim().equals(username) && getPassword.trim().equals(password)) {

                    return name;

                }
            
                }

            }


        } catch (IOException e) {

            e.printStackTrace();

        }

    return null;

    }

    public String loginManagerHandling(String getUserName, String getPassword){
        try(BufferedReader reader = new BufferedReader(new FileReader("database\\manager.dat"))){
            String line;

            while((line = reader.readLine()) != null){
                String [] parts = line.split("\t");
        
                if(parts.length == 3) {
                
                String name = parts[0].trim();
                String username = parts[1].trim();
                String password = parts[2].trim();

                if(getUserName.trim().equals(username) && getPassword.trim().equals(password)) {

                    return name;

                }
            
                }

            }


        } catch (IOException e) {

            e.printStackTrace();

        }

    return null;

    }

}