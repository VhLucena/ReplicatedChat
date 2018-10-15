/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author viniciuslucena
 */
public class InitialTerminal {

    public InitialTerminal() {
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String username, newMessage;
        
        System.out.println("Bem-vindo ao ReplicatedChat Classic Edition");
        
        System.out.print("Por favor, insira seu nome: ");
        username = sc.nextLine();
        ClientAdapter oClientAdapter = new ClientAdapter(username, getIdByName(username));
        
        while(true){
            System.out.println("-------------------------------------");
            System.out.println("Historico: ");
            System.out.println(oClientAdapter.getMessages());
            
    
            System.out.print("Nova Mensagem: ");
            newMessage = sc.nextLine();
            oClientAdapter.sendMessageUltraPrecision(newMessage);
            
            System.out.println("-------------------------------------");
        }
        
        
        
    }
    private static int getIdByName(String usr) {
        int answer = 0;
        
        for(int i = 0; i < usr.length(); i++)
            answer += Character.getNumericValue(usr.charAt(i));
        
        return answer;
    }
}
