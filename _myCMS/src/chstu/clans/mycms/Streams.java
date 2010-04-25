package chstu.clans.mycms;

import chstu.clans.mycms.client.CliConfigFiles;
import chstu.clans.mycms.client.SSLClient;
import chstu.clans.mycms.client.RecieveSrvConfigFile;
import chstu.clans.mycms.server.SSLServer;
import chstu.clans.mycms.server.SrvConfigFile;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class Streams {

    public static DataInputStream in;
    public static DataOutputStream out;
    public static FileInputStream fis;
    public static FileOutputStream fos;

    public static void server() {
        try {
            in = new DataInputStream(SSLServer.socket.getInputStream());
            out = new DataOutputStream(SSLServer.socket.getOutputStream());
            fis = new FileInputStream(SrvConfigFile.outFile);
        } catch (IOException ex) {
            Logger.getLogger(Streams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void client() {
        try {
            in = new DataInputStream(SSLClient.socket.getInputStream());
            out = new DataOutputStream(SSLClient.socket.getOutputStream());
            fos = new FileOutputStream(CliConfigFiles.srvFile);
        } catch (IOException ex) {
            Logger.getLogger(Streams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
