package chstu.clans.mycms.server;

import chstu.clans.mycms.Streams;
import chstu.clans.mycms.XMLWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class SrvConfigFile {

    public static File outFile = new File("d:\\myCMS\\DB_srv\\srv_config.xml");
    public static File dir = new File("d:\\myCMS\\_server");

    public static void generate() {
        System.out.println("Generating config file...");
        XMLWriter configFile = new XMLWriter();
        try {
            configFile.saveConfig(outFile, dir);
        } catch (Exception ex) {
            Logger.getLogger(SSLServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void send() {
        try {
            Streams.in = new DataInputStream(SSLServer.socket.getInputStream());
            Streams.out = new DataOutputStream(SSLServer.socket.getOutputStream());
            Streams.fis = new FileInputStream(outFile);

            // Sending config file size
            String strSize = Integer.toString((int) outFile.length());
            Streams.out.writeUTF(strSize);
            Streams.out.flush();
            System.out.println("Size: " + strSize);

            // Sending config file
            byte[] buf = new byte[512];
            int length;
            while (!Streams.in.readBoolean()) {
                length = Streams.fis.read(buf);
                Streams.out.write(buf, 0, length);
            }
            Streams.fis.close();
            Streams.out.flush();
            System.out.println("File sent.\n");
        } catch (IOException ex) {
            Logger.getLogger(SrvConfigFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
