package chstu.clans.mycms.server;

import chstu.clans.mycms.Streams;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Clans
 */
public class SSLServer implements Runnable {

    public static SSLSocket socket;

    public static void main(String[] args)
            throws SocketException {
        try {
            String srvKey = "d:\\Docs\\ChSTU\\_Diplom\\_myCMS\\srvkey.jks";
            char[] ksPass = "srvkey".toCharArray();
            char[] ctPass = "srvkey".toCharArray();

            int port = 5000;
            int count = 0;
            int max_connections = 5;

            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(srvKey), ksPass);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, ctPass);
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(kmf.getKeyManagers(), null, null);


            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            String currentTime = format.format(date);
            System.out.println("SERVER START AT: " + currentTime + "\n");

            SSLServerSocketFactory ssf = sc.getServerSocketFactory();
            SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(port);


            //ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting...\n");

            while (true) {
                if (count >= max_connections) {
                    break;
                }
                socket = (SSLSocket) ss.accept();
                Thread t = new Thread(new SSLServer());
                System.out.println("Accepted connection : " + socket + "\n");
                t.start();
                count++;
                Thread.yield();
            }

        } catch (KeyManagementException ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            Socket socket1 = socket;
            socket1.setSoTimeout(3000);

            // Generating XML file
            SrvConfigFile.generate();

            // Sending config file
            Streams.server();
            SrvConfigFile.send();

            // Deleting files
            SrvDeleteFiles.delete();

            // Creating files
            SrvCreateFiles.create();

            socket1.close();
            System.out.println("Waiting...\n");
        } catch (IOException ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
