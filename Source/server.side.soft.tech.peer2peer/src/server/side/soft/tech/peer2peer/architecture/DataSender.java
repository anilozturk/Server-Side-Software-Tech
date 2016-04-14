package server.side.soft.tech.peer2peer.architecture;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;

import server.side.soft.tech.peer2peer.util.Serialization;

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

  public DataSender(final Socket clientSocket, final DataPacket packet) {
    this.clientSocket = clientSocket;
    this.packet = packet;
  }

  @Override
  public void run() {
    if (this.packet != null) {
      try {
        final Formatter output = new Formatter(this.clientSocket.getOutputStream());
        output.format("%s\n", Serialization.getInstance().toString(this.packet)); // we convert our
                                                                                  // DataPacket to
                                                                                  // String because
                                                                                  // we have to give
                                                                                  // end of stream
                                                                                  // character to
                                                                                  // output stream.
        output.flush();
        output.close();
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }
}
