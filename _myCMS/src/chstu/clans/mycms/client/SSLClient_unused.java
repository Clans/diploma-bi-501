package chstu.clans.mycms.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Clans
 */
public class SSLClient_unused {

    public static void main(String[] args) {
        try {
            int port = 5000;
            SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) ssf.createSocket("localhost", port);

            socket.startHandshake();

            InputStream in = System.in;
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);

            OutputStream out = socket.getOutputStream();
            OutputStreamWriter outw = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(outw);

            String str = null;
            while ((str = br.readLine()) != null) {
                bw.write(str + "\n");
                bw.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(SSLClient_unused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
