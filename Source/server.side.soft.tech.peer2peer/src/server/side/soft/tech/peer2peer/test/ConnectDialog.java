package server.side.soft.tech.peer2peer.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import server.side.soft.tech.peer2peer.architecture.Connector;
import server.side.soft.tech.peer2peer.architecture.IConnection;
import server.side.soft.tech.peer2peer.architecture.PeerInfo;

public class ConnectDialog extends JDialog {

  private static final long serialVersionUID = 5924591995838142604L;
  private JTextField ipField;
  private JTextField portField;
  private JTextField nickField;
  private JTextField toField;
  private JLabel ipLabel;
  private JLabel portLabel;
  private JLabel nickLabel;
  private JLabel toLabel;
  private final JButton okButton;

  private final PeerNode parent;

  /**
   * Create the dialog.
   *
   * @param peerNode
   * @param connUtil
   */
  public ConnectDialog(final PeerNode peerNode, final IConnection connUtil) {
    this.setModal(true);
    this.setTitle("Connection Info");
    this.setType(Type.POPUP);
    this.setSize(450, 200);
    this.getContentPane().setLayout(new BorderLayout());

    this.parent = peerNode;

    final JPanel contentPanel = new JPanel();
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    final GridBagLayout gbl_contentPanel = new GridBagLayout();
    gbl_contentPanel.columnWeights = new double[] {0.0, 1.0};
    gbl_contentPanel.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0};
    contentPanel.setLayout(gbl_contentPanel);
    {
      this.ipLabel = new JLabel("IP");
      this.ipLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
      final GridBagConstraints gbc_ipLabel = new GridBagConstraints();
      gbc_ipLabel.fill = GridBagConstraints.BOTH;
      gbc_ipLabel.insets = new Insets(0, 0, 5, 5);
      gbc_ipLabel.gridx = 0;
      gbc_ipLabel.gridy = 0;
      contentPanel.add(this.ipLabel, gbc_ipLabel);
    }
    {
      this.ipField = new JTextField();
      this.ipField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
      this.ipLabel.setLabelFor(this.ipField);
      final GridBagConstraints gbc_ipField = new GridBagConstraints();
      gbc_ipField.insets = new Insets(0, 0, 5, 0);
      gbc_ipField.fill = GridBagConstraints.HORIZONTAL;
      gbc_ipField.gridx = 1;
      gbc_ipField.gridy = 0;
      contentPanel.add(this.ipField, gbc_ipField);
      this.ipField.setColumns(10);
    }
    {
      this.portLabel = new JLabel("Port");
      this.portLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
      final GridBagConstraints gbc_portLabel = new GridBagConstraints();
      gbc_portLabel.fill = GridBagConstraints.BOTH;
      gbc_portLabel.anchor = GridBagConstraints.EAST;
      gbc_portLabel.insets = new Insets(0, 0, 5, 5);
      gbc_portLabel.gridx = 0;
      gbc_portLabel.gridy = 1;
      contentPanel.add(this.portLabel, gbc_portLabel);
    }
    {
      this.portField = new JTextField();
      this.portField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
      this.portLabel.setLabelFor(this.portField);
      final GridBagConstraints gbc_portField = new GridBagConstraints();
      gbc_portField.insets = new Insets(0, 0, 5, 0);
      gbc_portField.fill = GridBagConstraints.BOTH;
      gbc_portField.gridx = 1;
      gbc_portField.gridy = 1;
      contentPanel.add(this.portField, gbc_portField);
      this.portField.setColumns(10);
    }
    {
      this.nickLabel = new JLabel("Nickname");
      this.nickLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
      final GridBagConstraints gbc_nickLabel = new GridBagConstraints();
      gbc_nickLabel.fill = GridBagConstraints.BOTH;
      gbc_nickLabel.anchor = GridBagConstraints.EAST;
      gbc_nickLabel.insets = new Insets(0, 0, 5, 5);
      gbc_nickLabel.gridx = 0;
      gbc_nickLabel.gridy = 2;
      contentPanel.add(this.nickLabel, gbc_nickLabel);
    }
    {
      this.nickField = new JTextField();
      this.nickField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
      this.nickLabel.setLabelFor(this.nickField);
      final GridBagConstraints gbc_nickField = new GridBagConstraints();
      gbc_nickField.insets = new Insets(0, 0, 5, 0);
      gbc_nickField.fill = GridBagConstraints.BOTH;
      gbc_nickField.gridx = 1;
      gbc_nickField.gridy = 2;
      contentPanel.add(this.nickField, gbc_nickField);
      this.nickField.setColumns(10);
    }
    this.ipLabel.setLabelFor(this.ipField);
    {
      this.toLabel = new JLabel("To");
      this.toLabel.setToolTipText(
          "Enter ports that you want to connect. Use this pattern.  ip1:port1,ip2:port2");
      this.toLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
      final GridBagConstraints gbc_toLabel = new GridBagConstraints();
      gbc_toLabel.anchor = GridBagConstraints.EAST;
      gbc_toLabel.fill = GridBagConstraints.BOTH;
      gbc_toLabel.insets = new Insets(0, 0, 0, 5);
      gbc_toLabel.gridx = 0;
      gbc_toLabel.gridy = 3;
      contentPanel.add(this.toLabel, gbc_toLabel);
    }
    {
      this.toField = new JTextField();
      this.toLabel.setLabelFor(this.toField);
      final GridBagConstraints gbc_toField = new GridBagConstraints();
      gbc_toField.fill = GridBagConstraints.BOTH;
      gbc_toField.gridx = 1;
      gbc_toField.gridy = 3;
      contentPanel.add(this.toField, gbc_toField);
      this.toField.setColumns(10);
    }
    {
      final JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
      this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
      {
        this.okButton = new JButton("OK");
        this.okButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        this.okButton.setActionCommand("OK");

        this.okButton.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(final ActionEvent arg0) {
            if (ConnectDialog.this.ipField.getText().isEmpty()
                || ConnectDialog.this.portField.getText().isEmpty()
                || ConnectDialog.this.nickField.getText().isEmpty()) {
              JOptionPane.showMessageDialog(ConnectDialog.this.getParent(),
                  "Please fill all the fields!", "Blank Field Error", JOptionPane.ERROR_MESSAGE);
            } else {
              if (connUtil instanceof Connector) {
                final PeerInfo info = new PeerInfo(ConnectDialog.this.ipField.getText(),
                    Integer.valueOf(ConnectDialog.this.portField.getText()),
                    ConnectDialog.this.nickField.getText());
                ((Connector) connUtil).setInfo(info);
              }
              ConnectDialog.this.parent.connSuccess();
              ConnectDialog.this.dispose();
            }
          }
        });

        buttonPane.add(this.okButton);
        this.getRootPane().setDefaultButton(this.okButton);
      }
    }
  }

  public String getToIpAndPorts() {
    return this.toField.getText();
  }
}
