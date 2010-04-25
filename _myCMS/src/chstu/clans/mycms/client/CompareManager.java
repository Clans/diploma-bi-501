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
        srvItems = read.readConfig(CliConfigFiles.srvFile);
        cliItems = read.readConfig(CliConfigFiles.cliFile);

        srvItems.removeAll(cliItems);

        for (int i = 0; i < srvItems.size(); i++) {
            for (int j = 0; j < cliItems.size(); j++) {
                try {
                    if (!srvItems.get(i).getModified().equals(cliItems.get(j).getModified())
                            && srvItems.get(i).getName().equals(cliItems.get(j).getName())
                            && srvItems.get(i).getSize().equals(cliItems.get(j).getSize())
                            && srvItems.get(i).getLastsync().equals(cliItems.get(i).getLastsync())) {
                        srvItems.remove(srvItems.get(i));
                    }
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        }

        List deleteFiles = new ArrayList();
        for (int m = 0; m < srvItems.size(); m++) {
            deleteFiles.add(srvItems.get(m).getName());
        }
        return deleteFiles;
    }

    /**
     *
     * @return files to create on server
     */
    public static List compareForCreate() {
        XMLParser read = new XMLParser();
        srvItems = read.readConfig(CliConfigFiles.srvFile);
        cliItems = read.readConfig(CliConfigFiles.cliFile);

        cliItems.removeAll(srvItems);

        for (int i = 0; i < cliItems.size(); i++) {
            for (int j = 0; j < srvItems.size(); j++) {
                try {
                    if (!cliItems.get(i).getModified().equals(srvItems.get(j).getModified())
                            && cliItems.get(i).getName().equals(srvItems.get(j).getName())
                            && cliItems.get(i).getSize().equals(srvItems.get(j).getSize())
                            && cliItems.get(i).getLastsync().equals(srvItems.get(j).getLastsync())) {
                        cliItems.remove(cliItems.get(i));
                    }
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        }
        List createFiles = new ArrayList();
        for (int n = 0; n < cliItems.size(); n++) {
            createFiles.add(cliItems.get(n).getName());
            //createFiles.add(cliItems.get(n).getSize());
        }
        return createFiles;
    }
}
