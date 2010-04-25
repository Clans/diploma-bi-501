package chstu.clans.mycms.client;

import chstu.clans.mycms.XMLWriter;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clans
 */
public class CliConfigFiles {
    
    public static File cliFile = new File("d:\\myCMS\\DB_cli\\cli_config.xml");
    public static File srvFile = new File("d:\\myCMS\\DB_cli\\srv_config.xml");
    public static File dir = new File("d:\\myCMS\\_client");

    public static void generate() {
        System.out.println("Generating config file...");
        XMLWriter configFile = new XMLWriter();
        try {
            configFile.saveConfig(cliFile, dir);
        } catch (Exception ex) {
            Logger.getLogger(SSLClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
