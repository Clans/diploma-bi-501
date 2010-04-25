package chstu.clans.mycms.client;

import chstu.clans.mycms.Streams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class CliCreateFiles {

    public static void create() {
        try {
            System.out.println("\nFiles to create on server:\n");

            // Sending number of files
            Streams.out.write(CompareManager.compareForCreate().size());
            System.out.println("Number of files: " + CompareManager.compareForCreate().size() + "\n");
            for (int j = 0; j < CompareManager.compareForCreate().size(); j++) {
                System.out.println("File name: " + CompareManager.compareForCreate().get(j));
                String cliFileName = CompareManager.compareForCreate().get(j).toString();

                // Sending client file name
                Streams.out.writeUTF(cliFileName);
                Streams.out.flush();

                // Sending client file size
                File file = new File(CliConfigFiles.dir + "\\" + cliFileName);
                String strCliFileSize = Integer.toString((int) file.length());
                Streams.out.writeUTF(strCliFileSize);
                Streams.out.flush();
                System.out.println("File size: " + strCliFileSize);

                // Sending client file
                FileInputStream fis = new FileInputStream(CliConfigFiles.dir + "\\" + cliFileName);
                System.out.println("Sending a file...");
                byte[] buf = new byte[512];
                int length;
                while (!Streams.in.readBoolean()) {
                    length = fis.read(buf);
                    Streams.out.write(buf, 0, length);
                }
                Streams.out.flush();
                System.out.println("File sent.\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(CliCreateFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
