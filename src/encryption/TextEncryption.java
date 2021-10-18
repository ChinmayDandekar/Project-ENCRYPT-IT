

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.awt.event.*;

public class TextEncryption extends JFrame implements ActionListener, WindowListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static SecretKeySpec secretKey;
    private static byte[] key;

    JTextArea encryptText, decryptText;
    JButton encrypt, decrypt, back, b, clear;
    JLabel eText, dText;
    JPanel p1, p2;
    boolean count = true;
    ImageIcon Projecticon;

    public TextEncryption(String username) {

        Projecticon = new ImageIcon("Data\\Encryptit1.png");
        setIconImage(Projecticon.getImage());

        encrypt = new JButton("Encrypt");
        encrypt.setBounds(20, 430, 150, 20);

        decrypt = new JButton("Decrypt");
        decrypt.setBounds(20 + 150 + 20, 430, 150, 20);

        back = new JButton("Back");
        back.setBounds(10 + 2 * 150 + 2 * 20, 430, 150, 20);

        clear = new JButton("Clear");
        clear.setBounds(10 + 3 * 150 + 3 * 20, 430, 150, 20);

        encryptText = new JTextArea();

        decryptText = new JTextArea();

        decryptText.setLineWrap(true);
        decryptText.setWrapStyleWord(true);

        encryptText.setLineWrap(true);
        encryptText.setWrapStyleWord(true);
        b = new JButton("");

        JScrollPane scrollPane = new JScrollPane(encryptText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 20, 320, 400);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Enter Text here"));

        JScrollPane scrollPane2 = new JScrollPane(decryptText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setBounds(350, 20, 320, 400);
        scrollPane2.setBorder(BorderFactory.createTitledBorder("Encrypt/Decrypt here"));

        add(scrollPane);
        add(scrollPane2);
        add(encrypt);
        add(decrypt);
        add(back);
        add(clear);
        add(b);

        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                encryptText.requestFocus();
            }
        }); 

        encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (encryptText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Write Your Message.");
                } else {
                    decryptText.setText(encrypt(encryptText.getText()));
                }
            }

        });

        decrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (decryptText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Encrypt your message first!!");
                } else {
                    decryptText.setText(decrypt(decryptText.getText()));
                }

            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new mainFrame(username);
            }
        });

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encryptText.setText("");
                decryptText.setText("");
                encryptText.requestFocus();
                count = true;
            }
        });

        setVisible(true);
        setLayout(null);
        setTitle("ENCRYPT it!");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    }

    public static void setKey() {
        MessageDigest sha = null;
        String myKey = "omchinmayimagine";
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (IllegalBlockSizeException e) {
            JOptionPane.showMessageDialog(null, "Enter the correct decrypted text.", "Message Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Enter the correct decrypted text.", "Message Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
