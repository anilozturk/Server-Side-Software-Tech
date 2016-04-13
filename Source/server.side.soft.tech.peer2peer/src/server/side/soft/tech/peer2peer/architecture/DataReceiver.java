package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * This thread reading our communication data from server port.
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class DataReceiver implements Runnable {

  private final IDataListener listener;

  private boolean keepListening = true;

  private ObjectInputStream ois;

  public DataReceiver(IDataListener listener, Socket clientSocket) {
    this.listener = listener;

    try {
      clientSocket.setSoTimeout(5000);
      this.ois = new ObjectInputStream(clientSocket.getInputStream()); // we are creating this
                                                                       // stream outside the loop
                                                                       // for avoid the exception.
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (this.keepListening) { // continue until we dont care clients.
      DataPacket packet;
      try {
        packet = (DataPacket) this.ois.readObject();
      } catch (final SocketTimeoutException e) {
        continue;
      } catch (final IOException e) {
        e.printStackTrace();
        break;
      } catch (final ClassNotFoundException e) {
        e.printStackTrace();
        break;
      }
      if (packet != null) {
        this.listener.dataReceive(packet);
      }
    }

    try {
      this.ois.close();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    this.keepListening = false;
  }
}
