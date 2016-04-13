package server.side.soft.tech.peer2peer.architecture;

public interface IConnection {

  public void connect();

  public void disconnect();

  public void sendData(DataPacket packet);
}
