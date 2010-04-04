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

    public static List compare() {
        XMLParser read = new XMLParser();
        srvItems = read.readConfig(Client.srvFile);
        cliItems = read.readConfig(Client.cliFile);

        if (!srvItems.equals(cliItems)) {
            List names = new ArrayList();
            for (int i = 0; i < srvItems.size(); i++) {
                for (int j = 0; j < cliItems.size(); j++) {
                    if (srvItems.get(i).equals(cliItems.get(j))) {
                        //System.out.println(cliItems.get(j).getName());
                        names.add(cliItems.get(j).getName());
                    }
                }
            }
            //System.out.println(names);
            return names;
        } else {
            return null;
        }
    }
}
