package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.side.soft.tech.peer2peer.test.PeerNode;

public class Connector implements IConnection, IDataListener {

  private final ExecutorService service;

  private PeerNode node;

  private ServerSocket serverSocket;

  private Socket clientSocket;

  private final boolean connected;

  private PeerInfo info;

  public Connector(PeerInfo info) {
    this.service = Executors.newCachedThreadPool();
    this.connected = true;
    this.info = info;
  }

  public Connector(PeerNode node) {
    this.service = Executors.newCachedThreadPool();
    this.connected = true;
    this.node = node;
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

    while (true) {
      Socket clientSocket;
      try {
        clientSocket = this.serverSocket.accept();
        this.service.execute(new DataReceiver(this, clientSocket));
        // ConnectionManager.getInstance().addNewActivePeer(this.info, clientSocket);
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void dataReceive(DataPacket packet) {
    final String fromName = packet.getSender().getNickname();
    final Object data = packet.getData();

    this.node.writePacket(fromName, data);
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
