package server.side.soft.tech.peer2peer.architecture;

public interface IDataListener { // Dinleme halindeki serverın veri alması için kullanılabilecek interface

  public void dataReceive(DataPacket packet);
}
