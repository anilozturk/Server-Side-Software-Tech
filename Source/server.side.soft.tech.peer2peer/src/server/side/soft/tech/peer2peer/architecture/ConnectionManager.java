package server.side.soft.tech.peer2peer.architecture;

import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {

  private static ConnectionManager instance = null;

  public static ConnectionManager getInstance() {
    if (instance == null) {
      synchronized (ConnectionManager.class) {
        if (instance == null) {
          instance = new ConnectionManager();
        }
      }
    }
    return instance;
  }

  private final List<PeerInfo> activePeers = new ArrayList<>();

  private final List<PeerInfo> deactivePeers = new ArrayList<>();

  private ConnectionManager() {}

  public void addNewActivePeer(PeerInfo peerInfo) {
    this.activePeers.add(peerInfo);
  }

  public void disposePeer(PeerInfo peerInfo) {
    this.activePeers.remove(peerInfo);
    this.deactivePeers.remove(peerInfo);
  }

  public void dropPeerToInactive(PeerInfo peerInfo) {
    this.activePeers.remove(peerInfo);
    this.deactivePeers.add(peerInfo);
  }

  public List<PeerInfo> getActivePeersExceptMe(PeerInfo myInfo) {
    final List<PeerInfo> cpyArray = new ArrayList<>();
    for (final PeerInfo peerInfo : this.activePeers) {
      if (!peerInfo.equals(myInfo)) {
        cpyArray.add(peerInfo);
      }
    }
    return cpyArray;
  }
}
