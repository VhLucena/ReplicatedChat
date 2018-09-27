
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
    private String users = "";
    private int messageCounter = 0;
    private static int BUF_SIZE = 4096;
    private int counter = 0;
    
    public Server(int id) {
    	new ServiceReplica(id, this, this);
    }

   
     /**
     * Este método é responsável por gravar a nova mensagem na sua lista de Mensagens
     * 
     */
    @Override
    public byte[] appExecuteOrdered(byte[] command, MessageContext msgCtx) {
        
        try {
            
            String newMessage = convertByteArrayToString(command);
            
            
            
            String[] splittedMessage = newMessage.split("#");
            String typeMessage = splittedMessage[0];
            
            if(typeMessage.equals("send")) {
                String currentTime = splittedMessage[1];
                String username = splittedMessage[2];
                String message = splittedMessage[3];
                                
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
            
            if(typeMessage.equals("history")) {
                return convertStringToByteArray(this.historyConversationStringFormatted + "</html>");
            }
            
            return null;
        } catch (IOException ex) {
            System.err.println("Invalid request received!");
            return new byte[0];
        }
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
            counter = in.readInt();
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
            out.writeInt(counter);
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
