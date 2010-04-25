package chstu.clans.mycms.server;

import chstu.clans.mycms.Streams;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class SrvDeleteFiles {

    public static void delete() {
        try {
            int deleteQuantity = Streams.in.read();
            System.out.println("Number of files to delete: " + deleteQuantity + "\n");
            while (deleteQuantity > 0) {
                // Recieving file name from client
                String delFileName = Streams.in.readUTF();
                File delFile = new File(SrvConfigFile.dir + "\\" + delFileName);
                delFile.delete();
                System.out.println("File " + "'" + delFileName + "'" + " deleted!");
                deleteQuantity--;
            }
        } catch (IOException ex) {
            Logger.getLogger(SrvDeleteFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
