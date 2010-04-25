package chstu.clans.mycms.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Clans
 */
public class Myfilereader {

    public static void main(String[] args) throws java.io.IOException {
//Getting the name of the files to be compared.
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter 1st File name:");
        String str = br2.readLine();
        System.out.println("Enter 2nd File name:");
        String str1 = br2.readLine();

        String s1 = "";
        String s2 = "", s3 = "", s4 = "";
        String y = "", z = "";

//Reading the contents of the files
        BufferedReader br = new BufferedReader(new FileReader(str));
        BufferedReader br1 = new BufferedReader(new FileReader(str1));

        while ((z = br1.readLine()) != null) {
            s3 += z;
        }

        while ((y = br.readLine()) != null) {
            s1 += y;
        }

        System.out.println();

//String tokenizing
        int numTokens = 0;
        StringTokenizer st = new StringTokenizer(s1);
        String[] a = new String[10000];
        for (int l = 0; l < 10000; l++) {
            a[l] = "";
        }
        int i = 0;
        while (st.hasMoreTokens()) {
            s2 = st.nextToken();
            a[i] = s2;
            i++;
            numTokens++;
        }

        int numTokens1 = 0;
        StringTokenizer st1 = new StringTokenizer(s3);
        String[] b = new String[10000];
        for (int k = 0; k < 10000; k++) {
            b[k] = "";
        }
        int j = 0;
        while (st1.hasMoreTokens()) {
            s4 = st1.nextToken();
            b[j] = s4;
            j++;
            numTokens1++;
        }

//comparing the contents of the files and printing the differences, if any.
        int x = 0;
        for (int m = 0; m < a.length; m++) {
            if (a[m].equals(b[m])) {
            } else {
                x++;
                System.out.println(a[m] + " — " + b[m]);
                System.out.println();
            }
        }
        System.out.println("No. of differences : " + x);
        if (x > 0) {
            System.out.println("Files are not equal");
        } else {
            System.out.println("Files are equal. No difference found");
        }

    }
}
