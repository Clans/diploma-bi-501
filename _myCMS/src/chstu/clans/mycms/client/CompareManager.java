package chstu.clans.mycms.client;

import chstu.clans.mycms.Item;
import chstu.clans.mycms.XMLParser;
import java.util.List;

/**
 *
 * @author Clans
 */
public class CompareManager {

    static List<Item> readConfig;

    public static void main(String args[]) {
        XMLParser read = new XMLParser();
        readConfig = read.readConfig(Client.cliFile);
        for (Item cliFile : readConfig) {
            System.out.println(cliFile);
        }

        System.out.println("");

        readConfig = read.readConfig(Client.srvFile);
        for (Item srvFile : readConfig) {
            System.out.println(srvFile);
        }

        
    }
}
