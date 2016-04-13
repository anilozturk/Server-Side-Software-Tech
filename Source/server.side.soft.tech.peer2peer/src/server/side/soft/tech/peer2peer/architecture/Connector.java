package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Connector implements IConnection {

  private final ExecutorService service;

  private IDataListener listener;

  private ServerSocket serverSocket;

  private Socket clientSocket;

  private boolean connected;

  private PeerInfo info;

  public Connector(IDataListener listener) {
    this.listener = listener;
    this.service = Executors.newCachedThreadPool();
    this.connected = false;
  }

  public Connector(PeerInfo info) {
    this.service = Executors.newCachedThreadPool();
    this.info = info;
    this.connected = false;
  }

  @Override
  public void connect() {
    if (this.connected) {
      return;
    }
    try {
      this.serverSocket = new ServerSocket(this.info.getPort(), 100);
    } catch (final IOException e) {
      e.printStackTrace();
    }

    this.connected = true;
    ConnectionManager.getInstance().addNewActivePeer(this.info);

    final Runnable r = new Runnable() {

      @Override
      public void run() {
        while (true) {
          Socket clientSocket;
          try {
            clientSocket = Connector.this.serverSocket.accept();
            Connector.this.service.execute(new DataReceiver(Connector.this.listener, clientSocket));
          } catch (final IOException e) {
            e.printStackTrace();
          }
        }
      }
    };

    this.service.execute(r);
  }

  @Override
  public void disconnect() {
    if (!this.connected) {
      return;
    }
    ConnectionManager.getInstance().dropPeerToInactive(this.info);
  }

  public void dispose() {
    ConnectionManager.getInstance().disposePeer(this.info);
  }

  public PeerInfo getInfo() {
    return this.info;
  }

  @Override
  public void sendData(DataPacket packet) {
    if (!this.connected) {
      return;
    }
    final PeerInfo receiver = packet.getReceiver();
    try {
      this.clientSocket = new Socket(receiver.getIp(), receiver.getPort());
    } catch (final IOException e) {
      e.printStackTrace();
    }
    this.service.execute(new DataSender(this.clientSocket, packet));
  }

  public void setInfo(PeerInfo info) {
    this.info = info;
  }
}
