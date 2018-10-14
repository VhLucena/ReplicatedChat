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
public class GetterUsersThread implements Runnable {
    private static ClientAdapter clientAdapter = null;
    public String listUsers;
    
    GetterUsersThread(ClientAdapter clientAdapter) {
        GetterUsersThread.clientAdapter = clientAdapter;
        new Thread(this, "GetterUsers").start();
    }

    @Override
    public void run() {
        System.out.println("GetterUsers iniciado");
        
        this.listUsers = GetterUsersThread.clientAdapter.getUsers();
    }
    
}
