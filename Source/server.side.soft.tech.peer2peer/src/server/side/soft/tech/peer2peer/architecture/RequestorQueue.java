package server.side.soft.tech.peer2peer.architecture;

import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class takes the given client socket and add to queue and then run that. This mechanism also
 * may be provide some messaging priority at the future.
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class RequestorQueue {

  private final IDataListener listener;

  private final Queue<Object> queue;

  private Thread thread;

  public RequestorQueue(final IDataListener listener) {
    this.listener = listener;
    this.queue = new LinkedBlockingQueue<>();
  }

  public void addToQueue(final Object client) {
    this.queue.add(client);

    this.thread = new Thread(new DataReceiver(this.listener, (Socket) client));
    this.thread.start();

    this.queue.remove();
  }
}
