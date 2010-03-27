package chstu.clans.mycms.client;

import chstu.clans.mycms.Item;
import chstu.clans.mycms.XMLParser;
import chstu.clans.mycms.XMLWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class Client {

    public static File cliFile = new File("d:\\Docs\\ChSTU\\_Diplom\\myCMS\\_client\\cli_config.xml");
    public static File srvFile = new File("d:\\Docs\\ChSTU\\_Diplom\\myCMS\\_client\\srv_config.xml");
    public static File dir = new File("d:\\Distrib\\!!!Saves!!!\\CSS");

    public static void main(String[] args)
            throws UnknownHostException, SocketException, IOException, InterruptedException {

        String hostname = "localhost";
        int port = 5000;

        System.out.println("Generating config file...");
        XMLWriter configFile = new XMLWriter();
        try {
            configFile.saveConfig(cliFile, dir);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        Socket connectionSocket = new Socket(hostname, port);
        System.out.println("Connecting to server...");

        DataInputStream dis = new DataInputStream(connectionSocket.getInputStream());
        OutputStream out = new FileOutputStream(srvFile);

        byte[] buf = new byte[1024];
        int len;
        while ((len = dis.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        dis.close();
        out.close();
        System.out.println("File recieved.");

        XMLParser read = new XMLParser();
        List<Item> readConfig = read.readConfig(srvFile);
        for (Item file : readConfig) {
            System.out.println(file);
        }
    }
}
