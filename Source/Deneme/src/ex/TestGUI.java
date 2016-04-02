package ex;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
          final TestGUI frame = new TestGUI();
          frame.setVisible(true);
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private final JPanel contentPane;

  /**
   * Create the frame.
   */
  public TestGUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(this.contentPane);
  }

}
