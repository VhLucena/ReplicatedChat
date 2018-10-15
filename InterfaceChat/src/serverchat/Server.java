
package serverchat;

import bftsmart.tom.MessageContext;
import bftsmart.tom.ServiceReplica;
import bftsmart.tom.server.defaultservices.DefaultSingleRecoverable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example replica that implements a BFT replicated service (a counter).
 * If the increment > 0 the counter is incremented, otherwise, the counter
 * value is read.
 * 
 * @author alysson
 */

public final class Server extends DefaultSingleRecoverable  {
    private String historyConversationString = "";
    private String historyConversationStringFormatted = "<html>";
    private String usersFormatted = "<html>";
    private List<String> listUsers = new ArrayList<String>();
    public int messageCounter = 0;
    private static int BUF_SIZE = 4096;
    
    public Server(int id) {
        //clearListUsers();
    	new ServiceReplica(id, this, this);
    }

   
    @Override
    public byte[] appExecuteOrdered(byte[] command, MessageContext msgCtx) {
        
        try {
            String newMessage = convertByteArrayToString(command);

            // Exemplo de mensagem: send#10:30:35#Vinicius#Oi
            String[] splittedMessage = newMessage.split("#");
            String typeMessage = splittedMessage[0];
            String currentTime = splittedMessage[1];
            String username = splittedMessage[2];
            String message = splittedMessage[3];
            
            if(!this.listUsers.contains(username)){
                System.out.println("NOVO USUARIO: " + username);
                this.listUsers.add(username);
                this.usersFormatted += ("<b>" + username + "</b><br>");
            }
            
            //System.out.println(Server.usersFormatted);
            
            if(typeMessage.equals("send")) {
                System.out.println("Nova mensagem: " + newMessage);
                this.messageCounter++;
                this.historyConversationString += (currentTime + " " + username + ": " + message + "\n");
                this.historyConversationStringFormatted += ("<b>" + currentTime + " " + username + ":</b> " + message + "<br>");
                
                System.out.println("------------------------------");
                System.out.println("Quantidade de Mensagens: " + this.messageCounter);

                // Imprimindo o histórico
                System.out.println("Historico de Mensagens: ");
                System.out.println(this.historyConversationString);
                System.out.println("------------------------------");
                
                return null;
            }
            
            if(typeMessage.equals("historyFormatted")) {
                return convertStringToByteArray(this.historyConversationStringFormatted + "</html>");
            }
            
            if(typeMessage.equals("history")) {
                return convertStringToByteArray(this.historyConversationString);
            }
            
            if(typeMessage.equals("users")) {
                return convertStringToByteArray(this.usersFormatted + "</html>");
            }
            
            
        } catch (IOException ex) {
            System.err.println("Invalid request received!");
            return new byte[0];
        } 
        return null;
    }

    
    private void clearListUsers() {
        /*
        new Thread() {
            
            public void run() {
                
                while(true) {
                  
                    //Server.semaphore.acquire();

                    Server.listUsers.clear();
                    Server.usersFormatted = "<html>";
                    //Server.semaphore.release();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            
        }.start();
        */
    }
    
    private String formatHistory(String history) {
        String[] splitted = history.split("\n");
        String formattedHistory = "<html>";
        
        for(String message : splitted) {
            String[] splittedMessage = message.split(":");
            formattedHistory += ("<b>" + splittedMessage[0] + ":</b>" + splittedMessage[1] + "<br>");
        }
        return formattedHistory += "</html>";
    }

    public static void main(String[] args){
        System.out.println("Serviço de Bate-Papo");
        if(args.length < 1) {
            System.out.println("Use: java Server <processId>");
            System.exit(-1);
        }      
        new Server(Integer.parseInt(args[0]));
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public void installSnapshot(byte[] state) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(state);
            ObjectInput in = new ObjectInputStream(bis);
            
            messageCounter = in.readInt();
            historyConversationString = in.readUTF();
            historyConversationStringFormatted = in.readUTF();

            in.close();
            bis.close();
        } catch (IOException e) {
            System.err.println("[ERROR] Error deserializing state: "
                    + e.getMessage());
        }
    }

    @Override
    public byte[] getSnapshot() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            
            out.writeInt(messageCounter);
            out.writeUTF(historyConversationString);
            out.writeUTF(historyConversationStringFormatted);
            
            out.flush();
            bos.flush();
            out.close();
            bos.close();
            return bos.toByteArray();
        } catch (IOException ioe) {
            System.err.println("[ERROR] Error serializing state: "
                    + ioe.getMessage());
            return "ERROR".getBytes();
        }
    }
    
    private String convertByteArrayToString(byte[] byteArrayValue) throws IOException{
        return new DataInputStream(new ByteArrayInputStream(byteArrayValue)).readUTF();
    }
    
    private byte[] convertStringToByteArray(String stringValue) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream(Server.BUF_SIZE);
        new DataOutputStream(out).writeUTF(stringValue);
        
        return out.toByteArray();
    }

    @Override
    public byte[] appExecuteUnordered(byte[] bytes, MessageContext mc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
