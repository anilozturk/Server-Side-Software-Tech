package server.side.soft.tech.peer2peer.architecture;

import java.util.ArrayList;
import java.util.List;

public final class ConnectionManager {

  private static ConnectionManager instance;

  public static ConnectionManager getInstance() {
    if (ConnectionManager.instance == null) {
      ConnectionManager.instance = new ConnectionManager();
      ConnectionManager.instance.activePeers = new ArrayList<>();
      ConnectionManager.instance.deactivePeers = new ArrayList<>();
    }
    return ConnectionManager.instance;
  }

  private List<PeerInfo> activePeers;

  private List<PeerInfo> deactivePeers;

  public void addNewActivePeer(final PeerInfo peerInfo) {
    this.activePeers.add(peerInfo);
  }

  public void disposePeer(final PeerInfo peerInfo) {
    this.activePeers.remove(peerInfo);
    this.deactivePeers.remove(peerInfo);
  }

  public void dropPeerToInactive(final PeerInfo peerInfo) {
    this.activePeers.remove(peerInfo);
    this.deactivePeers.add(peerInfo);
  }

  public List<PeerInfo> getActivePeersExceptMe(final PeerInfo myInfo) {
    final List<PeerInfo> cpyArray = new ArrayList<>();
    for (final PeerInfo peerInfo : this.activePeers) {
      if (!peerInfo.equals(myInfo)) {
        cpyArray.add(peerInfo);
      }
    }
    return cpyArray;
  }
}
