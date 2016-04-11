package server.side.soft.tech.peer2peer.architecture;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {

  private static ConnectionManager instance;

  public static ConnectionManager getInstance() {
    if (instance == null) {
      instance = new ConnectionManager();
      instance.activePeers = new HashMap<>();
      instance.deactivePeers = new HashMap<>();
    }
    return instance;
  }

  private Map<PeerInfo, Socket> activePeers;

  private Map<PeerInfo, Socket> deactivePeers;

  public void addNewActivePeer(PeerInfo peerInfo, Socket peerSocket) {
    this.activePeers.put(peerInfo, peerSocket);
  }

  public void disposePeer(PeerInfo peerInfo) {
    this.activePeers.remove(peerInfo);
    this.deactivePeers.remove(peerInfo);
  }

  public void dropPeerToInactive(PeerInfo peerInfo) {
    final Socket peerSocket = this.activePeers.get(peerInfo);
    this.activePeers.remove(peerInfo);
    this.deactivePeers.put(peerInfo, peerSocket);
  }

  public Socket getPeerSocket(PeerInfo peerInfo) {
    return this.activePeers.get(peerInfo);
  }
}
