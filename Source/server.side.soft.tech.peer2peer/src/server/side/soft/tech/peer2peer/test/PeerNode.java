package server.side.soft.tech.peer2peer.test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import server.side.soft.tech.peer2peer.architecture.ConnectionConstants;
import server.side.soft.tech.peer2peer.architecture.Connector;
import server.side.soft.tech.peer2peer.architecture.DataPacket;
import server.side.soft.tech.peer2peer.architecture.IConnection;
import server.side.soft.tech.peer2peer.architecture.IDataListener;
import server.side.soft.tech.peer2peer.architecture.PeerInfo;

/**
 * The base class for Peer
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class PeerNode extends JFrame {

  /**
   * This implementation shows Client's sent object in the outputArea.
   *
   * @author anıl öztürk
   * @author ahmet gül
   * @author asım zorlu
   */
  private class DataListener implements IDataListener {

    @Override
    public void dataReceive(final DataPacket packet) { // unpack the packet.
      final String fromName = packet.getSender().getNickname();
      final Object data = packet.getData();
      SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
          PeerNode.this.outputArea.append(
              fromName + ConnectionConstants.MESSAGE_SEPARATOR + data + ConnectionConstants.EOL);
        }
      });
    }
  }

  private static final long serialVersionUID = -4148257519270926391L;

  final JMenuItem connItem;

  final JMenuItem disconnItem;

  final JButton sendButton;

  final JTextArea inputArea;

  final JTextArea outputArea;

  private final IConnection connUtil;

  private final IDataListener listener;

  private String[] toIpsAndPorts;

  private ConnectDialog connectDialog;

  private PeerInfo myInfo;

  /**
   * Create the frame.
   */
  public PeerNode() {
    setTitle(ConnectionConstants.GUI_TITLE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 400);

    this.listener = new DataListener();
    this.connUtil = new Connector(this.listener);

    final JPanel contentPane = new JPanel();
    contentPane.setBorder(null);
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    final JMenuBar menuBar = new JMenuBar();
    menuBar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    contentPane.add(menuBar, BorderLayout.NORTH);

    final JMenu connMenu = new JMenu("Connection");
    connMenu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    connMenu.setMnemonic('C');
    menuBar.add(connMenu);

    this.connItem = new JMenuItem("Connect");
    this.connItem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    connMenu.add(this.connItem);

    this.disconnItem = new JMenuItem("Disconnect");
    this.disconnItem.setEnabled(false);
    this.disconnItem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    connMenu.add(this.disconnItem);

    final JPanel viewPanel = new JPanel();
    contentPane.add(viewPanel, BorderLayout.CENTER);
    final GridBagLayout gbl_viewPanel = new GridBagLayout();
    gbl_viewPanel.rowHeights = new int[] {80, 80, 80};
    gbl_viewPanel.columnWeights = new double[] {1.0, 0.0};
    gbl_viewPanel.rowWeights = new double[] {1.0, 1.0, 1.0};
    viewPanel.setLayout(gbl_viewPanel);

    this.outputArea = new JTextArea();
    this.outputArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    this.outputArea.setEditable(false);
    final GridBagConstraints gbc_outputArea = new GridBagConstraints();
    gbc_outputArea.insets = new Insets(0, 0, 5, 0);
    gbc_outputArea.gridheight = 2;
    gbc_outputArea.gridwidth = 0;
    gbc_outputArea.fill = GridBagConstraints.BOTH;
    gbc_outputArea.gridx = 0;
    gbc_outputArea.gridy = 0;
    viewPanel.add(this.outputArea, gbc_outputArea);

    this.inputArea = new JTextArea();
    this.inputArea.setEditable(false);
    this.inputArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    final GridBagConstraints gbc_inputArea = new GridBagConstraints();
    gbc_inputArea.insets = new Insets(0, 0, 0, 5);
    gbc_inputArea.fill = GridBagConstraints.BOTH;
    gbc_inputArea.gridx = 0;
    gbc_inputArea.gridy = 2;
    viewPanel.add(this.inputArea, gbc_inputArea);

    this.sendButton = new JButton("Send");
    this.sendButton.setEnabled(false);
    this.sendButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    final GridBagConstraints gbc_sendButton = new GridBagConstraints();
    gbc_sendButton.fill = GridBagConstraints.HORIZONTAL;
    gbc_sendButton.anchor = GridBagConstraints.WEST;
    gbc_sendButton.gridwidth = 0;
    gbc_sendButton.gridx = 1;
    gbc_sendButton.gridy = 2;
    viewPanel.add(this.sendButton, gbc_sendButton);

    installListeners(); // all required listeners has been installed.
  }

  public void connSuccess() { // if connect dialog has been returned with success then init this
                              // node.
    this.connUtil.connect();
    this.toIpsAndPorts =
        this.connectDialog.getToIpAndPorts().trim().split(ConnectionConstants.SEPERATOR);
    this.connItem.setEnabled(false);
    this.disconnItem.setEnabled(true);
    this.sendButton.setEnabled(true);
    this.inputArea.setEditable(true);
    if (PeerNode.this.connUtil instanceof Connector) {
      PeerNode.this.myInfo = ((Connector) PeerNode.this.connUtil).getInfo();
      setTitle(getTitle() + "(" + this.myInfo.getNickname() + ")");
    }
    this.inputArea.requestFocus();
  }

  private void installConnectListener() { // it asks the required ip, port, bla bla for constructed
                                          // to be node.
    this.connItem.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent e) {
        PeerNode.this.connectDialog = new ConnectDialog(PeerNode.this, PeerNode.this.connUtil);
        PeerNode.this.connectDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        PeerNode.this.connectDialog.setLocationRelativeTo(PeerNode.this);
        PeerNode.this.connectDialog.setVisible(true);
      }
    });

  }

  private void installDisconnectListener() {
    this.disconnItem.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent e) {
        PeerNode.this.connUtil.disconnect();
        PeerNode.this.connItem.setEnabled(true);
        PeerNode.this.disconnItem.setEnabled(false);
        PeerNode.this.sendButton.setEnabled(false);
        PeerNode.this.inputArea.setEditable(false);
        setTitle(ConnectionConstants.GUI_TITLE);
      }
    });
  }

  private void installExitListener() { // if window is going to close then do some garbage collector
                                       // operation.
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(final WindowEvent e) {
        super.windowClosing(e);
        PeerNode.this.connUtil.disconnect();
      }
    });
  }

  private void installListeners() {
    installConnectListener();
    installDisconnectListener();
    installSendListener();
    installExitListener();
  }

  private void installSendListener() {
    this.sendButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent e) { // now we are client and we want to send
                                                         // some info, then go on.
        for (final String ipAndPort : PeerNode.this.toIpsAndPorts) { // all of registered nodes will
                                                                     // be effected.
          final String[] vals = ipAndPort.split(ConnectionConstants.ADDRESS_SEPERATOR);
          final PeerInfo receiver = new PeerInfo(vals[0], Integer.valueOf(vals[1]), "");
          final DataPacket packet =
              new DataPacket(PeerNode.this.myInfo, receiver, PeerNode.this.inputArea.getText());
          PeerNode.this.connUtil.sendData(packet);
        }

        PeerNode.this.outputArea // also change the our outputArea.
            .append(PeerNode.this.myInfo.getNickname() + ConnectionConstants.MESSAGE_SEPARATOR
                + PeerNode.this.inputArea.getText() + ConnectionConstants.EOL);
        PeerNode.this.inputArea.setText("");
      }
    });
  }
}
