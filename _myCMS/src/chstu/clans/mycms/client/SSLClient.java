package chstu.clans.mycms.client;

import chstu.clans.mycms.Streams;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Clans
 */
public class SSLClient {

    public static SSLSocket socket;

    public static void main(String[] args) {
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            String hostname = "localhost";
            int port = 5000;
            
            // Generating XML file
            CliConfigFiles.generate();
            System.out.println("Connecting to server...");
            socket = (SSLSocket) sf.createSocket(hostname, port);
            socket.startHandshake();

            // Recieving server config file
            Streams.client();
            RecieveSrvConfigFile.recieve();

            // Files to delete on server
            CliDeleteFiles.delete();

            // Files to create on server
            CliCreateFiles.create();

            socket.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(SSLClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SSLClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
