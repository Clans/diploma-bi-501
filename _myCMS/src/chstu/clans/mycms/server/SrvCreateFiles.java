package chstu.clans.mycms.server;

import chstu.clans.mycms.Streams;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class SrvCreateFiles {

    public static void create() {
        try {
            int createQuantity = Streams.in.read();
            System.out.println("\n" + "Number of files to recieve: " + createQuantity + "\n");
            while (createQuantity > 0) {
                // Recieving client file name
                String createFileName = Streams.in.readUTF();
                FileOutputStream fos = new FileOutputStream(SrvConfigFile.dir + "\\" + createFileName);
                System.out.println("File name: " + createFileName);
                
                // Recieving client file size
                int createFileSize = Integer.valueOf(Streams.in.readUTF());
                System.out.println("File size: " + createFileSize);

                // Recieving files from client
                byte[] buf = new byte[512];
                int length;
                int i = 0;
                while (i != createFileSize) {
                    Streams.out.writeBoolean(false);
                    length = Streams.in.read(buf);
                    i = i + length;
                    fos.write(buf, 0, length);
                    if (i == createFileSize) {
                        Streams.out.writeBoolean(true);
                    }
                }
                fos.flush();
                fos.close();
                System.out.println("File " + "'" + createFileName + "'" + " recieved.\n");
                createQuantity--;
            }
        } catch (IOException ex) {
            Logger.getLogger(SrvCreateFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
