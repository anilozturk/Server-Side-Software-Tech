package server.side.soft.tech.peer2peer.architecture;

public class PeerInfo {

  private String ip;

  private int port;

  private final String nickname;

  public PeerInfo(String ip, int port, String nickname) { // Kullanılan her bir akranın bilgileri için yapıcı method.
    this.ip = ip;
    this.port = port;
    this.nickname = nickname;
  } // Bağlanılan ip, port numarası ve nickname bu alanda saklanmaktadır.


  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof PeerInfo)) {
      return false;
    }
    final PeerInfo other = (PeerInfo) obj;
    if (other.getIp().equals(getIp()) && other.getPort() == getPort()) {
      return true;
    }
    return false;
  }

// getter ve setter metotları
  public String getIp() {
    return this.ip;
  }

  public String getNickname() {
    return this.nickname;
  }

  public int getPort() {
    return this.port;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
