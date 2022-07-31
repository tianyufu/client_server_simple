import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A TCP sever that starts in passive mode listening for a transmission from the client.
 */
public class Server {

    /**
     * Start the server.
     *
     * @param args the port the server listens to
     * @throws IOException if an I/O error occurs when operating on the socket
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Port number needed as argument.");
        }
        
        ServerSocket s = new ServerSocket(Integer.parseInt(args[0]));
        Socket s1 = s.accept();

        InputStream s1in = s1.getInputStream();
        OutputStream s1out = s1.getOutputStream();

        DataInputStream dis = new DataInputStream(s1in);
        String inputStr = dis.readUTF();

        DataOutputStream dos = new DataOutputStream(s1out);
        dos.writeUTF(reverse(inputStr));

        dis.close();
        dos.close();
        s1in.close();
        s1out.close();
        s1.close();
    }

    /**
     * Reverse the order and capitalization of a string.
     *
     * @param s the string to be reversed
     * @return the revered string
     */
    private static String reverse(String s) {
        char[] chArr = s.toCharArray();
        int i = 0;
        int j = chArr.length - 1;

        while (i <= j) {
            char temp = chArr[i];
            chArr[i++] = Character.isUpperCase(chArr[j]) ? Character.toLowerCase(chArr[j]) :
                    Character.toUpperCase(chArr[j]);
            chArr[j--] = Character.isUpperCase(temp) ? Character.toLowerCase(temp) :
                    Character.toUpperCase(temp);
        }

        return new String(chArr);
    }
}
