/**
Copyright (c) 2007-2013 Alysson Bessani, Eduardo Alchieri, Paulo Sousa, and the authors indicated in the @author tags

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package clientchat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import bftsmart.tom.ServiceProxy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JTextField;


/**
 * Example client that updates a BFT replicated service (a counter).
 * 
 * @author alysson
 */
public class ClientAdapter {
    private static int MSG_SIZE = 10;
    private static int BUF_SIZE = 4096;
    private static String name;
    private static int idClient;
    private static ServiceProxy counterProxy;



    public ClientAdapter (String username, int idClient) throws IOException {
        ClientAdapter.name = username;
        ClientAdapter.idClient = idClient;
  
        ClientAdapter.counterProxy = new ServiceProxy(idClient);
      
    }
    
    
    /**
     * Send a message to all replicas
     * 
     * @param newMessage: String that save a new message to send
     * @return Server reply 
     */
    public String sendMessage(String newMessage) throws IOException {
        try {
            String currentTime = getCurrentTime();
            // FORMATO DA MENSAGEM: [send#(8:55) nomeCliente: mensagem]
            String newPackage = "send#"+ currentTime + "#" + ClientAdapter.name + "#" + newMessage;
            
            byte[] out = convertStringToByteArray(newPackage);

            byte[] reply = ClientAdapter.counterProxy.invokeOrdered(out); 

            if(reply == null)   
                return "Erro no envio da mensagem";

            return "OK";
        } catch(IOException | NumberFormatException e) {
            ClientAdapter.counterProxy.close();
        }
        return null;
    }
    
    public String getMessages() throws IOException {
        String newPackage = "history";
        
        byte[] out = convertStringToByteArray(newPackage);
        
        byte[] reply = ClientAdapter.counterProxy.invokeOrdered(out); 
        
        String replyString = convertByteArrayToString(reply);
                
        return replyString;
    }
    
    public String getUsers() throws IOException {
         String newPackage = "users";
        
        byte[] out = convertStringToByteArray(newPackage);
        
        byte[] reply = ClientAdapter.counterProxy.invokeOrdered(out); 
        
        String replyString = convertByteArrayToString(reply);
        
        return replyString;
    }
    
    public String getServers() throws IOException {
         String newPackage = "servers";
        
        byte[] out = convertStringToByteArray(newPackage);
        
        byte[] reply = ClientAdapter.counterProxy.invokeOrdered(out); 
        
        String replyString = convertByteArrayToString(reply);
        
        return replyString;
    }

    public String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	Date date = new Date();
	return dateFormat.format(date);
    }
    
    private String convertByteArrayToString(byte[] byteArrayValue) throws IOException{
        return new DataInputStream(new ByteArrayInputStream(byteArrayValue)).readUTF();
    }
    
    private byte[] convertStringToByteArray(String stringValue) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream(ClientAdapter.BUF_SIZE);
        new DataOutputStream(out).writeUTF(stringValue);
        
        return out.toByteArray();
    }

    void sendMessage(JTextField txtInputMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
