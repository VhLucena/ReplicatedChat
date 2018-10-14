/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import static java.lang.Thread.sleep;

/**
 *
 * @author viniciuslucena
 */
public class ClientChat {

    
    public static void main(String[] args) throws InterruptedException {
        ClientAdapter clientAdapter = new ClientAdapter("Vinicius", 150);
    
        GetterUsersThread t = new GetterUsersThread(clientAdapter); 
        
        while(true){
            System.out.println("Lista de usuarios: " + t.listUsers);
            sleep(1000);
        }
        
    }
}
