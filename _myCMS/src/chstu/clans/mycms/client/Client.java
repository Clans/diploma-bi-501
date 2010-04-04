package chstu.clans.mycms.client;

import chstu.clans.mycms.XMLWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class Client {

    public static File cliFile = new File("d:\\myCMS\\_client\\cli_config.xml");
    public static File srvFile = new File("d:\\myCMS\\_client\\srv_config.xml");
    private static File dir = new File("d:\\myCMS\\_client");

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

        DataInputStream dataInputStream = new DataInputStream(connectionSocket.getInputStream());
        OutputStream out = new FileOutputStream(srvFile);

        byte[] buf = new byte[1024];
        int len;
        while ((len = dataInputStream.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        dataInputStream.close();
        out.close();
        System.out.println("File recieved.\n");
        System.out.println("New files on client:\n");

        CompareManager.compare();

        for (int i = 0; i < CompareManager.compare().size(); i++) {
            System.out.println(CompareManager.compare().get(i));

            /*FileInputStream fileInputStream = new FileInputStream("d:\\Distrib\\!!!Saves!!!\\CCleaner\\" + CompareManager.compare().get(i).toString());
            DataOutputStream outToSrv = new DataOutputStream(connectionSocket.getOutputStream());
            outToSrv.writeBytes(CompareManager.compare().get(i).toString());

            DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());            
            String sentence = CompareManager.compare().get(i).toString();
            outToServer.writeBytes(sentence + "\n");

            byte[] cliFilesBuf = new byte[1024];
            int lenCli;
            while ((lenCli = fileInputStream.read(cliFilesBuf)) > 0) {
                outToSrv.write(cliFilesBuf, 0, lenCli);
            }
            fileInputStream.close();
            outToSrv.close();
            System.out.println("File sent.");*/
        }

        /*
         * while (??.hasNext) {
         * ...
         * file = ??.getName();
         * ...
         * }
         */

    }
}
