package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * This class required for providing queue mechanism for listen incoming client connections.
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class ConnRequestCollector implements Runnable {

  private final ServerSocket serverSocket;

  private final RequestorQueue queue;

  public ConnRequestCollector(final ServerSocket serverSocket, final IDataListener listener) {
    this.serverSocket = serverSocket;
    this.queue = new RequestorQueue(listener);
  }

  @Override
  public void run() {
    while (true) {
      Socket clientSocket = null;
      try {
        clientSocket = this.serverSocket.accept();
      } catch (final SocketException e) {
        if (this.serverSocket.isClosed()) {
          break;
        }
      } catch (final IOException e) {
        e.printStackTrace();
      }

      this.queue.addToQueue(clientSocket);
    }
  }
}
