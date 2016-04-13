package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * This class required for providing queue mechanism for listen incoming client connections.
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class ListenRequests implements Runnable {

  private final ServerSocket serverSocket;

  private final IDataListener listener; // the listener interface for changing the outputArea.

  private final ExecutorService service;

  public ListenRequests(ServerSocket serverSocket, IDataListener listener,
      ExecutorService service) {
    this.serverSocket = serverSocket;
    this.listener = listener;
    this.service = service;
  }

  @Override
  public void run() {
    while (true) {
      Socket clientSocket;
      try {
        clientSocket = this.serverSocket.accept();
        this.service.execute(new DataReceiver(this.listener, clientSocket));
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }
}
