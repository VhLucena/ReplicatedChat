
package chat;

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
    private List<String> historyConversation = new ArrayList<String>();
    private String historyConversationString = "";
    private String newMessage;
    private int messageCounter = 0;
    private int counter = 0;
    private int iterations = 0;
    private int BUF_SIZE = 4096;
    
    public Server(int id) {
    	new ServiceReplica(id, this, this);
    }

    
    /**
     * Este método é responsável por gravar a nova mensagem na sua lista de Mensagens
     * 
     */
    @Override
    public byte[] appExecuteUnordered(byte[] command, MessageContext msgCtx) {         
        iterations++;
        System.out.println("(" + iterations + ") Counter current value: " + counter);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(4);
            new DataOutputStream(out).writeInt(counter);
            return out.toByteArray();
        } catch (IOException ex) {
            System.err.println("Invalid request received!");
            return new byte[0];
        }
    }
   
     /**
     * Este método é responsável por gravar a nova mensagem na sua lista de Mensagens
     * 
     */
    @Override
    public byte[] appExecuteOrdered(byte[] command, MessageContext msgCtx) {
        this.messageCounter++;
        try {
            this.newMessage = new DataInputStream(new ByteArrayInputStream(command)).readUTF();
            System.out.println("Nova mensagem: " + this.newMessage);
            this.historyConversation.add(newMessage);
            this.historyConversationString += (newMessage + "\n");
            
            System.out.println("------------------------------");
            System.out.println("Quantidade de Mensagens: " + this.messageCounter);
            
            // Imprimindo o histórico
            System.out.println("Historico de Mensagens: ");
            System.out.println(this.historyConversationString);
            System.out.println("------------------------------");
            
            ByteArrayOutputStream out = new ByteArrayOutputStream(this.BUF_SIZE);
            new DataOutputStream(out).writeUTF(this.historyConversationString);
            return out.toByteArray();
        } catch (IOException ex) {
            System.err.println("Invalid request received!");
            return new byte[0];
        }
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
}
