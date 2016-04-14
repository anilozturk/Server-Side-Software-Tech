package server.side.soft.tech.peer2peer;

import java.awt.EventQueue;

import javax.swing.UIManager;

import server.side.soft.tech.peer2peer.gui.PeerNode;

/**
 * Our initializer class.
 * 
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class NodeInitializer {

  /**
   * Launch the application.
   */
  public static void main(final String[] args) {
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
