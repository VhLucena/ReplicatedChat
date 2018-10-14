/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author viniciuslucena
 */
public class Testes {
    private static List<String> listUsers = new ArrayList<String>();
    private static String usersFormatted = "<html>";
   
    public static void main(String args[]) {
        String newMessage = "users#-#Vinicius#-";
        
        while(true) {
        
            String[] splittedMessage = newMessage.split("#");
            String typeMessage = splittedMessage[0];
            String currentTime = splittedMessage[1];
            String username = splittedMessage[2];
            String message = splittedMessage[3];

            System.out.println(typeMessage);
            System.out.println(currentTime);
            System.out.println(username);
            System.out.println(message);


            if(!listUsers.contains(username)){
                listUsers.add(username);
                usersFormatted += ("<b>" + username + "</b><br>");
            }
            //Server.semaphore.release();

            System.out.println(usersFormatted);
        
        }
        /*
        String user = "Vinicius Lucena";
        
        listUsers.add("Vinicius Lucen");
        listUsers.add("Martins");
        listUsers.add("Cicera");
       
        
        if(listUsers.contains(user))
            System.out.println("tem o elemento");
        else
            System.out.println("nao tem o elemento");
       */

    }


    
}
