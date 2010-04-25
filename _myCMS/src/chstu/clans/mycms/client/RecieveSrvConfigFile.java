package chstu.clans.mycms.client;

import chstu.clans.mycms.Streams;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class RecieveSrvConfigFile {

    public static void recieve() {
        try {
            // Recieving server config file size
            System.out.println("Recieving server config file...");
            String strSize = Streams.in.readUTF();
            int srvSize = Integer.valueOf(strSize);
            System.out.println("Size: " + srvSize);

            // Recieving server config file
            byte[] buf = new byte[512];
            int length = 0;
            int n = 0;
            while (n != srvSize) {
                Streams.out.writeBoolean(false);
                length = Streams.in.read(buf);
                n = n + length;
                Streams.fos.write(buf, 0, length);
                if (n == srvSize) {
                    Streams.out.writeBoolean(true);
                }
            }
            Streams.fos.flush();
            System.out.println("File recieved.\n");
        } catch (IOException ex) {
            Logger.getLogger(RecieveSrvConfigFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
