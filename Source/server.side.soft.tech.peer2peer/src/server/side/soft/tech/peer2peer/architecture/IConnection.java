package server.side.soft.tech.peer2peer.architecture;

public interface IConnection { // GUI deki bağlanma durumları için interface

  public void connect();

  public void disconnect();

  public void sendData(DataPacket packet);
}
