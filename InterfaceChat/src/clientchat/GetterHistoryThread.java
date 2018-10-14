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
public class GetterHistoryThread implements Runnable {
    private static ClientAdapter clientAdapter = null;
    public String history;
    
    GetterHistoryThread(ClientAdapter clientAdapter) {
        GetterHistoryThread.clientAdapter = clientAdapter;
        System.out.println("GetterHistory iniciado");
        new Thread(this, "GetterHistory").start();
    }

    @Override
    public void run() {
        System.out.println("Metodo Run chamado");
        try {
            this.history = GetterHistoryThread.clientAdapter.getMessages();
            System.out.println("Recebi: " + this.history);
        } catch (IOException ex) {
            Logger.getLogger(GetterHistoryThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
