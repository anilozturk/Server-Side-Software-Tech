package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.net.Socket;

public class DataReceiver implements Runnable {

  private final IDataListener listener;

  private final Socket clientSocket;

  private boolean keepListening;

  public DataReceiver(IDataListener listener, Socket clientSocket) {
    this.listener = listener;
    this.clientSocket = clientSocket;
  }

  @Override
  public void run() {
    this.keepListening = true;

    while (this.keepListening) {
      final byte[] in = new byte[Byte.MAX_VALUE];
      try {
        this.clientSocket.getInputStream().read(in);
        final DataPacket packet = Serializer.deserialize(in, DataPacket.class);
        this.listener.dataReceive(packet);
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void stop() {
    this.keepListening = false;
  }
}
