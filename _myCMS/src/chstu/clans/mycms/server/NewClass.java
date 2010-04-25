package chstu.clans.mycms.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugene
 */
public class NewClass implements Runnable {

    private static Socket s;
    private static ServerSocket ss;
    //private static int count;
    //private static int max_connection;
    static int nameFile;
    static String directory;

    public static void main(String[] args) throws IOException, InterruptedException {
        //count = 0;
        nameFile = 0;
        //max_connection = 4;
        int port = 2345;
        String host = "localhost";
        directory = "d:\\";
        if (args.length > 0) {
            port = new Integer(args[0]);
        }
        if (args.length > 1) {
            host = args[1];
        }
        if (args.length > 2) {
            directory = args[2];
        }
        ss = new ServerSocket(port, 0, InetAddress.getByName(host));
        System.out.println("Server is running");
        while (true) {
            /*System.out.println(count);
            if (count >= max_connection) {
            break;
            }*/
            s = ss.accept();
            Thread t = new Thread(new NewClass());
            t.start();
            //count++;
            nameFile++;
            Thread.yield();
        }
    }

    public void run() {
        Socket s1 = s;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            s1.setSoTimeout(30000);
            br = new BufferedReader(new InputStreamReader(s.getInputStream(), "Cp1251"));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directory + nameFile + ".txt"), "Cp1251"));
            String str;

            while ((str = br.readLine()) != null) {
                bw.write(str);
                bw.newLine();
                bw.flush();
            }

        } catch (SocketException se) {
            //se.printStackTrace();
        } catch (SocketTimeoutException ste) {
            System.out.println("timeout");
            //ste.printStackTrace();
        } catch (IOException ex1) {
            //Logger.getLogger(tcpServer.class.getName()).log(Level.SEVERE, null, ex1);
        } finally {
            try {
                bw.close();
                br.close();
                s1.close();
            } catch (IOException ex2) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }
    }
}
