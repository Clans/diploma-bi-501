package chstu.clans.mycms.client;

import chstu.clans.mycms.Item;
import chstu.clans.mycms.XMLParser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Clans
 */
public class CompareManager {

    private List items;
    private static List<Item> srvItems;
    private static List<Item> cliItems;
    //public static String[] filename;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompareManager other = (CompareManager) obj;
        if (this.items != other.items && (this.items == null || !this.items.equals(other.items))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.items != null ? this.items.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @return files to delete on server
     */
    public static List compareForDelete() {
        XMLParser read = new XMLParser();
        srvItems = read.readConfig(Client.srvFile);
        cliItems = read.readConfig(Client.cliFile);

        if (!srvItems.equals(cliItems)) {
            List deleteFiles = new ArrayList();
            for (int i = 0; i < srvItems.size(); i++) {
                for (int j = 0; j < cliItems.size(); j++) {
                    if (srvItems.get(i).equals(cliItems.get(j))) {
                        srvItems.remove(cliItems.get(j));
                    }
                }
            }
            for (int m = 0; m < srvItems.size(); m++) {
                deleteFiles.add(srvItems.get(m).getName());
            }
            return deleteFiles;
        } else {
            return null;
        }
    }

    /**
     *
     * @return files to create on server
     */
    public static List compareForCreate() {
        XMLParser read = new XMLParser();
        srvItems = read.readConfig(Client.srvFile);
        cliItems = read.readConfig(Client.cliFile);

        if (!srvItems.equals(cliItems)) {
            List createFiles = new ArrayList();
            for (int i = 0; i < srvItems.size(); i++) {
                for (int j = 0; j < cliItems.size(); j++) {
                    if (srvItems.get(i).equals(cliItems.get(j))) {
                        cliItems.remove(srvItems.get(i));
                    }
                }
            }
            for (int n = 0; n < cliItems.size(); n++) {
                createFiles.add(cliItems.get(n).getName());
            }
            return createFiles;
        } else {
            return null;
        }
    }
}
