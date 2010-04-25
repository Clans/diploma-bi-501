package chstu.clans.mycms.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Clans
 */
public class SSLServer_unused {

    public static void main(String[] args) {
        try {
            int port = 5000;
            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(port);


            System.out.println("Waiting...\n");
            SSLSocket socket = (SSLSocket) ss.accept();
            System.out.println("Accepted connection : " + socket + "\n");

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String str = null;
            while ((str = br.readLine()) != null) {
                System.out.println("str: " + str);
                System.out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(SSLServer_unused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
