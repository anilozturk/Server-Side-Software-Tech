package server.side.soft.tech.peer2peer.architecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import server.side.soft.tech.peer2peer.util.Serialization;

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

  private BufferedReader br;

  public DataReceiver(final IDataListener listener, final Socket clientSocket) {
    this.listener = listener;

    try {
      clientSocket.setSoTimeout(5000);
      this.br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // we
                                                                                          // created
                                                                                          // this
                                                                                          // buffer
                                                                                          // outside
                                                                                          // the
                                                                                          // loop
                                                                                          // for
                                                                                          // avoid
                                                                                          // the
                                                                                          // exception.
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (this.keepListening) { // continue until we dont care clients.
      DataPacket packet = null;
      try {
        final String str = this.br.readLine();
        if (str != null) {
          packet = Serialization.getInstance().fromString(str); // convert decoded string to
                                                                // DataPacket which has been sent
                                                                // from client
        }
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
