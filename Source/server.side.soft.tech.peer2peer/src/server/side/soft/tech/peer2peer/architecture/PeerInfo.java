package server.side.soft.tech.peer2peer.architecture;

import java.io.Serializable;

/**
 * This class holds peer information like ip, port, nickname.
 * 
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class PeerInfo implements Serializable {

  private static final long serialVersionUID = -6095816718709183701L;

  private String ip;

  private int port;

  private final String nickname;

  public PeerInfo(final String ip, final int port, final String nickname) {
    this.ip = ip;
    this.port = port;
    this.nickname = nickname;
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof PeerInfo)) {
      return false;
    }
    final PeerInfo other = (PeerInfo) obj;
    if (other.getIp().equals(getIp()) && other.getPort() == getPort()) {
      return true;
    }
    return false;
  }

  public String getIp() {
    return this.ip;
  }

  public String getNickname() {
    return this.nickname;
  }

  public int getPort() {
    return this.port;
  }

  public void setIp(final String ip) {
    this.ip = ip;
  }

  public void setPort(final int port) {
    this.port = port;
  }

  @Override
  public String toString() {
    return getIp() + ":" + getPort() + " /" + getNickname();
  }
}
