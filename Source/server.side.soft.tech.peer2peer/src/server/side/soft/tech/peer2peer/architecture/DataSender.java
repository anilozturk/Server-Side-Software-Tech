package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.net.Socket;

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
      try {
        this.clientSocket.getOutputStream().write(Serializer.serialize(this.packet));
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }
}
