package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This thread writing our communication data to server port.
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class DataSender implements Runnable {

  private final Socket clientSocket;

  private final DataPacket packet;

  public DataSender(Socket clientSocket, DataPacket packet) {
    this.clientSocket = clientSocket;
    this.packet = packet;
  }

  @Override
  public void run() {
    if (this.packet != null) {
      ObjectOutputStream oos = null;
      try {
        oos = new ObjectOutputStream(this.clientSocket.getOutputStream());
        oos.writeObject(this.packet); // this is our DataPacket instance which has sender, receiver
                                      // and content.
        oos.flush(); // flush is required if we want to implement this program on a web.
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }
}
