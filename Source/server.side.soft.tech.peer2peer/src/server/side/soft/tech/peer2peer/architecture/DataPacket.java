package server.side.soft.tech.peer2peer.architecture;

import java.io.Serializable;

public class DataPacket implements Serializable {

  private static final long serialVersionUID = -7394311034936837970L;

  private Object data;

  private PeerInfo sender;

  private PeerInfo receiver;

  public DataPacket(PeerInfo sender, PeerInfo receiver, Object data) { // Bu sınıfın yapıcı methodu. 
    this.sender = sender;
    this.receiver = receiver;
    this.data = data;
  } // İçinde gönderici, alıcı ve gönderilen mesaj bilgisini tutmaktadır.


// getter ve setter methodları asağıda tanımlanmıştır.
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
