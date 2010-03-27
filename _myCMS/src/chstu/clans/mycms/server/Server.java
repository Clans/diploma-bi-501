package chstu.clans.mycms.server;

import chstu.clans.mycms.XMLWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class Server {

    static File outFile = new File("d:\\Docs\\ChSTU\\_Diplom\\myCMS\\_server\\srv_config.xml");
    public static File dir = new File("d:\\Distrib\\!!!Saves!!!\\CCleaner");
    //static File file;

    public static void main(String[] args)
            throws SocketException, IOException, InterruptedException {

        //file = "d:\\Docs\\ChSTU\\_Diplom\\myCMS\\_server\\srv_config.xml";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String currentTime = format.format(date);
        System.out.println("SERVER START AT: " + currentTime + "\n");

        int port = 5000;

        ServerSocket welcomeSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting...\n");

            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Accepted connection : " + connectionSocket);

            System.out.println("Generating config file...");

            try {
                XMLWriter configFile = new XMLWriter();
                try {
                    configFile.saveConfig(outFile, dir);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                FileInputStream fos = new FileInputStream(outFile);
                OutputStream out = new DataOutputStream(connectionSocket.getOutputStream());

                byte[] buf = new byte[1024];
                int len;
                while ((len = fos.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                fos.close();
                out.close();
                System.out.println("File sent.");
            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe.getMessage() + " in the specified directory.");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
