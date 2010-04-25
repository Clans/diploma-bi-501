package chstu.clans.mycms.client;

import chstu.clans.mycms.Streams;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class CliDeleteFiles {

    public static void delete() {
        try {
            System.out.println("Files to delete from server:\n");
            Streams.out.write(CompareManager.compareForDelete().size());
            System.out.println("Number of files: " + CompareManager.compareForDelete().size() + "\n");
            for (int i = 0; i < CompareManager.compareForDelete().size(); i++) {
                System.out.println(CompareManager.compareForDelete().get(i));
                Streams.out.writeUTF(CompareManager.compareForDelete().get(i).toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(CliDeleteFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
