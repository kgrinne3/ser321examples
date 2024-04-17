import java.net.*;
import java.io.*;

/**
 * A class to demonstrate a simple client-server connection using sockets.
 * Ser321 Foundations of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Tim Lindquist Tim.Lindquist@asu.edu
 *         Software Engineering, CIDSE, IAFSE, ASU Poly
 * @version August 2020
 * 
 * @modified-by David Clements <dacleme1@asu.edu> September 2020
 */
public class SockServer {
  public static void main (String args[]) {
    Socket sock = null;
    try {
      ServerSocket serv = new ServerSocket(8888);
      System.out.println("Server ready for 3 connections");

      for (int rep = 0; rep < 3; rep++){
        System.out.println("Server waiting for a connection");
        sock = serv.accept(); // blocking wait

        ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

        String s = (String) in.readObject();
        System.out.println("Received the String "+s);

        Integer i = (Integer) in.readObject();
        System.out.println("Received the Integer "+ i);

        OutputStream out = sock.getOutputStream();

        ObjectOutputStream os = new ObjectOutputStream(out);

        os.writeObject("Got it!");

        os.flush();
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      if (sock != null)
        try {
          sock.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }
}
