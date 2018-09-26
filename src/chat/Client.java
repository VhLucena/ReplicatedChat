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
package chat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import bftsmart.tom.ServiceProxy;
import java.util.Scanner;


/**
 * Example client that updates a BFT replicated service (a counter).
 * 
 * @author alysson
 */
public class Client {
    private static int MSG_SIZE = 10;
    private static int BUF_SIZE = 4096;
    private static String name;
    

    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            System.out.println("Usage: java ... Client <process id>");
            System.exit(-1);
        }
        int idClient = Integer.parseInt(args[0]);
        
        System.out.println("Digite seu nome: ");
        Client.name = new Scanner(System.in).nextLine();

        ServiceProxy counterProxy = new ServiceProxy(idClient);
        
        while(true) {
            try {
                System.out.println("Digite uma mensagem: ");
                String newMessage = new Scanner(System.in).nextLine();
                String newPackage = Client.name + ": " + newMessage;

                ByteArrayOutputStream out = new ByteArrayOutputStream(Client.BUF_SIZE);
                new DataOutputStream(out).writeUTF(newPackage);
                
                byte[] reply = counterProxy.invokeOrdered(out.toByteArray()); 

                if(reply != null) {
                    System.out.println("-----------------------");
                    System.out.println("Histórico de Conversa: ");
                    String newValue = new DataInputStream(new ByteArrayInputStream(reply)).readUTF();
                    System.out.println(newValue);
                    System.out.println("-----------------------\n");
                } else {
                    System.out.println(", ERROR! Exiting.");
                }

            } catch(IOException | NumberFormatException e){
                counterProxy.close();
            }
        }
    }

}
