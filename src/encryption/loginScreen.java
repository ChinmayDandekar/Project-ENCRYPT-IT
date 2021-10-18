
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;

public class loginScreen extends JFrame implements ActionListener, KeyListener ,WindowListener{

    /**
     *
     */
    private static final long serialVersionUID = -8912372175388729483L;
    JButton login, Exit, b;
    JLabel n, p;
    JTextField name;
    JPasswordField pass;
    JPanel panel;
    JCheckBox showPass;
    ImageIcon Projecticon;
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screensize.width;
    int screenHeight = screensize.height;
    String Username, Password;
    protected String CorrectUser[] = { "Om", "Chinmay" };
    protected String CorrectPass[] = { "imagine", "IMAGINE" };

    loginScreen() {

        int x = 10, y = 50, width = 130, height = 20;

        Projecticon = new ImageIcon("Data\\Encryptit1.png");

        n = new JLabel("<html><h3>Username:</h3></html>");
        n.setBounds(x, y + 40, width, height);

        p = new JLabel("<html><h3>Password:</h3></html>");
        p.setBounds(x, 2 * y + 20 + height, width, height);

        pass = new JPasswordField(15);
        pass.setBounds(2 * x + width, 2 * y + 20 + height, width, height);

        name = new JTextField(15);
        name.setBounds(2 * x + width, y + 20 + 20, width, height);
        

        showPass = new JCheckBox("Show Password");
        showPass.setBounds(2 * x + width, 3 * y + 2 * height, width, height);

        login = new JButton("<html><h3>Login</h3></html>");
        login.setBounds(15, 300, width, height + 5);

        Exit = new JButton("<html><h3>Exit</h3></html>");
        Exit.setBounds(2 * 15 + width, 300, width, height + 5);

        b = new JButton("");

        panel = new JPanel();
        panel.setBounds(10, 30, 290, 230);
        panel.setBorder(BorderFactory.createTitledBorder("<html><h1>ENCRYPT it!!</h1></html>"));
        panel.setLayout(null);

        panel.add(n);
        panel.add(name);

        panel.add(p);
        panel.add(pass);
        panel.add(showPass);

        add(panel);
        add(login);
        add(Exit);
        add(b);


        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                name.requestFocus();
            }
        }); 

        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    pass.requestFocusInWindow();
                }
            } 
        });

        pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    Username = name.getText();
                    Password = String.valueOf(pass.getPassword());

                    if (loginCheck(Username, Password)) {
                        JOptionPane.showMessageDialog(null, "Login Successfull");
                        setVisible(false);
                        new mainFrame(Username);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed");
                    }
                }
            }
        });

        showPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPass.isSelected()) {
                    pass.setEchoChar((char) 0);
                } else {
                    pass.setEchoChar('*');
                }
            }
        });

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Username = name.getText();
                Password = String.valueOf(pass.getPassword());

                if (loginCheck(Username, Password)) {
                    JOptionPane.showMessageDialog(null, "Login Successfull");
                    setVisible(false);
                    new mainFrame(Username);
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed");
                }
            }
        });
        
        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setUndecorated(true);
        setVisible(true);
        
        setLayout(null);

        setIconImage(Projecticon.getImage());
        setSize(310, 360);
        setTitle("ENCRYPT it!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

    }

    boolean loginCheck(String username, String password) {
        String correctPass, correctUser;
        boolean c = true;
        for (int i = 0; i < CorrectPass.length; i++) {
            correctPass = CorrectPass[i];
            correctUser = CorrectUser[i];
            if (username.equals(correctUser) && password.equals(correctPass)) {
                c = true;
                break;
            } else {
                c = false;
            }

        }
        return c;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println(e);
        }
        new loginScreen();
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {
	
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
       
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}