package chstu.clans.mycms;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Clans
 */
public class NewClass_unused {

    public static void main(String[] args) throws IOException {
        File dir = new File("d:\\myCMS\\_client");
        File[] fileList = dir.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            System.out.println(fileList[i].getName());
        }
    }
}
