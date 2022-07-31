import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * The client that calls the server by provided hostname and port number. The client will pass
 * server a string and then display the response from the server.
 */
public class Client {

    /**
     * Get input from user and call the server to get response.
     *
     * @param args the port number of the server
     * @throws IOException if an I/O error occurs when operating on the socket
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Server address and port number needed as " +
                    "arguments.");
        }

        Socket s1 = new Socket(args[0], Integer.parseInt(args[1]));

        OutputStream s1out = s1.getOutputStream();
        InputStream s1in = s1.getInputStream();

        Scanner scanner = new Scanner(System.in);
        String inputText;

        while (true) {
            System.out.print("Enter text: ");
            inputText = scanner.nextLine();
            if (inputText.length() > 80) {
                System.out.println("The text cannot be over 80 characters in length.");
            } else {
                break;
            }
        }

        DataOutputStream dos = new DataOutputStream(s1out);
        dos.writeUTF(inputText);

        DataInputStream dis = new DataInputStream(s1in);
        System.out.print("Response from server: " + dis.readUTF());

        // When done, close the connection and exit
        dis.close();
        dos.close();
        s1in.close();
        s1out.close();
        s1.close();
    }
}
