package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The backbone of architecture. This class has 3 main activities (connect, disconnect, sendData)
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class Connector implements IConnection {

  private final ExecutorService service;

  private final IDataListener listener;

  private ServerSocket serverSocket; // server side of peer.

  private Socket clientSocket; // client side of peer.

  private boolean connected;

  private PeerInfo info;

  public Connector(final IDataListener listener) {
    this.listener = listener;
    this.service = Executors.newCachedThreadPool();
    this.connected = false;
  }

  @Override
  public void connect() {
    if (this.connected) {
      return;
    }
    try { // create server socket and send this to ListenRequests for listen its port for incoming
          // client connection requests.
      this.serverSocket = new ServerSocket(this.info.getPort(), 100);
    } catch (final IOException e) {
      e.printStackTrace();
    }

    this.connected = true;

    final ListenRequests requestListener =
        new ListenRequests(this.serverSocket, this.listener, this.service);
    this.service.execute(requestListener);
  }

  @Override
  public void disconnect() {
    if (!this.connected) {
      return;
    }
  }

  public PeerInfo getInfo() {
    return this.info;
  }

  @Override
  public void sendData(final DataPacket packet) {
    if (!this.connected) {
      return;
    }
    final PeerInfo receiver = packet.getReceiver();
    try { // create clientSocket for to be client and use datapacket to understand where this data
          // want to go ?
      this.clientSocket = new Socket(InetAddress.getByName(receiver.getIp()), receiver.getPort());
    } catch (final IOException e) {
      e.printStackTrace();
    }
    this.service.execute(new DataSender(this.clientSocket, packet)); // call the thread which has
                                                                     // responsibility to write
                                                                     // packet to server port.
  }

  public void setInfo(final PeerInfo info) {
    this.info = info;
  }
}
