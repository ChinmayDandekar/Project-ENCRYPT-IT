
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class Help extends JFrame implements ActionListener {

  /**
   *
   */
  private static final long serialVersionUID = 290479775683122051L;
  JLabel Icon;

  JEditorPane helpArea;
  ImageIcon Projecticon;

  public Help() {

    Projecticon = new ImageIcon("Data\\Encryptit1.png");
    setIconImage(Projecticon.getImage());

    helpArea = new JEditorPane();
    helpArea.setBounds(20, 10, 645, 420);

    URL url = FileEncryption.class.getResource("help.html");

    try {
      helpArea.setPage(url);
    } catch (IOException e) {
      helpArea.setContentType("text/html");
      helpArea.setText("<html>Page not found.</html>");
    }

    JScrollPane help = new JScrollPane(helpArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    help.setBounds(10, 10, 665, 440);
    help.setBorder(BorderFactory.createTitledBorder(""));

    add(help);
    setLayout(null);
    setTitle("ENCRYPT it!");
    setSize(700, 500);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

}
