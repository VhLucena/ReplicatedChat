/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author viniciuslucena
 */
public class GetterUsersThread implements Runnable {
    private static ClientAdapter clientAdapter = null;
    public String listUsers;
    
    GetterUsersThread(ClientAdapter clientAdapter) {
        GetterUsersThread.clientAdapter = clientAdapter;
        new Thread(this, "GetterUsers").start();
    }

    @Override
    public void run() {
        System.out.println("GetterUsers iniciado.");
        while(true){
            try {
                this.listUsers = GetterUsersThread.clientAdapter.getUsers();
            } catch (IOException ex) {
                Logger.getLogger(GetterUsersThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
