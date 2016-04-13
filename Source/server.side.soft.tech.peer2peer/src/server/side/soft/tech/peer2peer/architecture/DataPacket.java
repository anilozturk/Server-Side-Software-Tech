package server.side.soft.tech.peer2peer.architecture;

import java.io.Serializable;

/**
 * This class is our communication data which has common fields (sender,receiver,data,...) you can
 * widen this.
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class DataPacket implements Serializable {

  private static final long serialVersionUID = -7394311034936837970L;

  private Object data;

  private PeerInfo sender;

  private PeerInfo receiver;

  public DataPacket(PeerInfo sender, PeerInfo receiver, Object data) {
    this.sender = sender;
    this.receiver = receiver;
    this.data = data;
  }

  public Object getData() {
    return this.data;
  }

  public PeerInfo getReceiver() {
    return this.receiver;
  }

  public PeerInfo getSender() {
    return this.sender;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public void setReceiver(PeerInfo receiver) {
    this.receiver = receiver;
  }

  public void setSender(PeerInfo sender) {
    this.sender = sender;
  }
}
