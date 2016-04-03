package server.side.soft.tech.peer2peer.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class TestGUI extends JFrame {

  private static final long serialVersionUID = -4148257519270926391L;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
          final TestGUI frame = new TestGUI();
          frame.setVisible(true);
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public TestGUI() {
    setTitle("Test GUI");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
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

    final JMenuItem connItem = new JMenuItem("Connect");
    connItem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    connMenu.add(connItem);

    final JMenuItem disconnItem = new JMenuItem("Disconnect");
    disconnItem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    connMenu.add(disconnItem);

    final JMenu helpMenu = new JMenu("Help");
    helpMenu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    helpMenu.setMnemonic('H');
    menuBar.add(helpMenu);

    final JMenuItem searchItem = new JMenuItem("Search");
    searchItem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    helpMenu.add(searchItem);

    final JMenuItem aboutItem = new JMenuItem("About");
    aboutItem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    helpMenu.add(aboutItem);

    final JPanel viewPanel = new JPanel();
    contentPane.add(viewPanel, BorderLayout.CENTER);
    final GridBagLayout gbl_viewPanel = new GridBagLayout();
    gbl_viewPanel.rowHeights = new int[] {80, 80, 80};
    gbl_viewPanel.columnWeights = new double[] {1.0, 0.0};
    gbl_viewPanel.rowWeights = new double[] {1.0, 1.0, 1.0};
    viewPanel.setLayout(gbl_viewPanel);

    final JTextArea outputArea = new JTextArea();
    outputArea.setText("Örnek bir çıktı");
    outputArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    outputArea.setEditable(false);
    final GridBagConstraints gbc_outputArea = new GridBagConstraints();
    gbc_outputArea.insets = new Insets(0, 0, 5, 0);
    gbc_outputArea.gridheight = 2;
    gbc_outputArea.gridwidth = 0;
    gbc_outputArea.fill = GridBagConstraints.BOTH;
    gbc_outputArea.gridx = 0;
    gbc_outputArea.gridy = 0;
    viewPanel.add(outputArea, gbc_outputArea);

    final JTextArea inputArea = new JTextArea();
    inputArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    inputArea.setText("Örnek bir girdi");
    final GridBagConstraints gbc_inputArea = new GridBagConstraints();
    gbc_inputArea.insets = new Insets(0, 0, 0, 5);
    gbc_inputArea.fill = GridBagConstraints.BOTH;
    gbc_inputArea.gridx = 0;
    gbc_inputArea.gridy = 2;
    viewPanel.add(inputArea, gbc_inputArea);

    final JButton sendButton = new JButton("Send");
    sendButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
    final GridBagConstraints gbc_sendButton = new GridBagConstraints();
    gbc_sendButton.fill = GridBagConstraints.HORIZONTAL;
    gbc_sendButton.anchor = GridBagConstraints.WEST;
    gbc_sendButton.gridwidth = 0;
    gbc_sendButton.gridx = 1;
    gbc_sendButton.gridy = 2;
    viewPanel.add(sendButton, gbc_sendButton);
  }

}
