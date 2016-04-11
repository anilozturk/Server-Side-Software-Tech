package server.side.soft.tech.peer2peer.architecture;

import java.awt.EventQueue;

import javax.swing.UIManager;

import server.side.soft.tech.peer2peer.test.PeerNode;

public class NodeInitializer {

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
          final PeerNode node = new PeerNode();
          node.setLocationRelativeTo(null);
          node.setVisible(true);
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
