package chstu.clans.mycms.client;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Clans
 */
public class DirListCli {

    public static void main(String[] args) {
        SimpleDateFormat dateformatter = new SimpleDateFormat("yyyyMMdd HH:mm:ssZ");
        File file = new File("d:\\x_Download\\");
        /*try {
        file = env.pathToFile(dir);
        } catch (IOException e) {
        env.println("error reading path: " + e);
        return;
        }

        if (!file.exists() || !file.canRead()) {
        env.println("Can't read " + file);
        return;
        }
        if (!file.isDirectory()) {
        env.println("'" + dir + "' is not a directory");
        }*/

        String[] files = file.list();
        //files = bubbleSort(files);

        for (int i = 0; i < files.length; i++) {
            File f = new File(file + File.separator + files[i]);
            StringBuffer sb = new StringBuffer();
            sb.append(f.canRead()? "r" : "-");
            sb.append(f.canWrite()? "w" : "-");
            sb.append("_");
            sb.append("  ");

            Date d = new Date(f.lastModified());
            sb.append(dateformatter.format(d.getTime()));
            sb.append(" ");

            // hack to get fixed length 'length' field
            int fieldlen = 8;
            StringBuffer len = new StringBuffer();
            for (int j = 0; j < fieldlen; j++) {
                len.append("  ");
            }
            len.insert(0, f.length());
            len.setLength(fieldlen);
            // hack to move the spaces to the front
            int si = len.toString().indexOf(" ");
            //System.out.println("length: " + f.length());
            if (si != -1) {
                String pad = len.toString().substring(si);
                len.setLength(si);
                len.insert(0, pad);
            }

            sb.append(len);

            sb.append("  " + f.getName());
            if (f.isDirectory()) {
                sb.append("/");
            }
            //System.out.println(files.length);
            System.out.println(sb.toString());
        }
    }

    /*public static String[] bubbleSort(String[] in) {
    Vector v = new Vector();
    for (int i = 0; i < in.length; i++) {
    v.addElement(in[i]);
    }

    int n = v.size();
    boolean swap = true;
    while (swap) {
    swap = false;
    for (int i = 0; i < (n - 1); i++) {
    if (((String) v.elementAt(i)).compareTo(
    ((String) v.elementAt(i + 1))) > 0) {
    String tmp = (String) v.elementAt(i + 1);
    v.removeElementAt(i + 1);
    v.insertElementAt(tmp, i);
    swap = true;
    }
    }
    }

    String[] out = new String[n];
    v.copyInto(out);
    return out;
    }*/
}

