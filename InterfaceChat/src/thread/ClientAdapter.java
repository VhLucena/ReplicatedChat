/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

/**
 *
 * @author viniciuslucena
 */
public class ClientAdapter {

    public ClientAdapter(String username, int idClient) {
        
    }
    
    public synchronized void sendMessage(String msg) {
        System.out.println("Mensagem " + msg + "enviada");
    }
    
    public synchronized String getMessages() {
        return "Lista de mensagens..";
    }
    
    public synchronized String getUsers() {
        return "Lista de usuarios...";
    }
}
