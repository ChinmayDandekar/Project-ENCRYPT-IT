
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class mainFrame extends JFrame implements ActionListener, MouseListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JButton ForFile, ForText, LogOut, b;
    JMenuBar menu;
    JMenu userName, left;
    JMenu Help, About;
    JMenuItem logout;
    BufferedImage encryptit;
    JLabel imageLabel;
    JLabel fontLabel;
    ImageIcon icon, exiticon, normalicon, fileIcon, messageIcon;
    JPanel panel;
    ImageIcon Projecticon;

    public mainFrame(String username) {

        menu = new JMenuBar();

        userName = new JMenu("Welcome, " + username);
        left = new JMenu("left");

        logout = new JMenuItem("Log Out");
        Help = new JMenu("Help");
        About = new JMenu("About");

        userName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        userName.add(logout);

        menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        menu.add(userName);
        menu.add(About);
        menu.add(Help);
        setJMenuBar(menu);

        imageLabel = new JLabel("");

        ImageIcon iconLogo = new ImageIcon("Data\\keyIcon.png");

        imageLabel.setIcon(iconLogo);

        panel = new JPanel();
        panel.setBounds(20, 20, 260, 400);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("Options:"));

        int x = 40, y = 70, width = 180, height = 40;

        ForFile = new JButton("FILE ENCRYPTION");
        ForFile.setBounds(x, y, width, height);

        ForText = new JButton("TEXT ENCRYPTION");
        ForText.setBounds(x, 2 * y + height, width, height);

        LogOut = new JButton("LOG OUT");
        LogOut.setBounds(x, 3 * y + 2 * height, width, height);

        panel.add(ForFile);
        panel.add(ForText);
        panel.add(LogOut);

        imageLabel.setBounds(320, 20, 300, 400);

        b = new JButton("");

        add(imageLabel);

        validate();

        add(panel);
        add(b);

        fontLabel = new JLabel("<html><h1>Om Thakre</h1> SE-A-49<br><h1>Chinmay Dandekar</h1> SE-A-9</html>");
        fontLabel.setFont(new Font("", Font.BOLD, 14));

        icon = new ImageIcon("Data\\imagineicon.png");
        exiticon = new ImageIcon("Data\\logout.png");
        fileIcon = new ImageIcon("Data\\fileIcon.png");
        messageIcon = new ImageIcon("Data\\messageIcon.png");

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new loginScreen();
            }
        });

        ForText.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                imageLabel.setIcon(messageIcon);
                
            }

            public void mouseExited(MouseEvent e) {
                imageLabel.setIcon(iconLogo);
                
            }
        });

        LogOut.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
                imageLabel.setIcon(exiticon);
                imageLabel.setBounds(350, 20, 300, 400);
            }

            public void mouseExited(MouseEvent e) {
                imageLabel.setIcon(iconLogo);
                imageLabel.setBounds(320, 20, 300, 400);
            }
        });

        ForFile.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
                imageLabel.setIcon(fileIcon);
            }

            public void mouseExited(MouseEvent e) {
                imageLabel.setIcon(iconLogo);
            }
        });

        About.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, fontLabel, "About", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

        Help.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                new Help();
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

        ForFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new FileEncryption(username);
            }
        });

        ForText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TextEncryption(username);
            }
        });

        LogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new loginScreen().setVisible(true);
            }
        });

        Projecticon = new ImageIcon("Data\\Encryptit1.png");
        setIconImage(Projecticon.getImage());
        setTitle("ENCRYPT it!");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}