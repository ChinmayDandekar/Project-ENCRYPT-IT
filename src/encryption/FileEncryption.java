
import javax.swing.*;
import java.net.URL;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.filechooser.*;

public class FileEncryption extends JFrame implements ActionListener, Runnable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JButton encryptFile, decryptFile, reset, reset1, b, c, SaveKey, Back, fileChooser, fileChooser2;
    JLabel efilePath, dfilePath, sKey, keyWarning;
    JTextField efilePathText, dfilePathText;
    JPasswordField sKeyField;
    JPanel encryption, decryption, t, key;
    JTable j;
    JCheckBox setPass;
    ImageIcon Projecticon;
    JEditorPane infoArea;

    private String[] ext = { "pdf", "txt", "png", "docx", "jpeg", "jpg" };

    FileEncryption(String username) {

        Projecticon = new ImageIcon("Data\\Encryptit1.png");
        setIconImage(Projecticon.getImage());
        Encapsulate en = new Encapsulate();

        int x = 20, y = 20;
        int width = 120, height = 25;

        infoArea = new JEditorPane();
        infoArea.setBounds(10, 20, 320, 370);
        infoArea.setEditable(false);

        URL url = FileEncryption.class.getResource("AESinformation.html");

        try {
            infoArea.setPage(url);
        } catch (IOException e) {
            infoArea.setContentType("text/html");
            infoArea.setText("<html>Page not found.</html>");
        }

        JScrollPane infoPanel = new JScrollPane(infoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        infoPanel.setBounds(330, 20, 340, 400);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Definition"));

        // Encryption

        efilePath = new JLabel("Enter File Path");
        efilePath.setBounds(x, y, width, height);

        efilePathText = new JTextField(15);
        efilePathText.setBounds(2 * x + width, y, width - 21, height);

        fileChooser = new JButton("...");
        fileChooser.setBounds(260, y, 20, height);

        encryptFile = new JButton("Encrypt");
        encryptFile.setBounds(x, 2 * y + height, width, height);

        reset = new JButton("Reset");
        reset.setBounds(2 * x + width, 2 * y + height, width, height);

        Back = new JButton("Back");
        Back.setBounds(20, 430, 120, 25);

        b = new JButton("");
        c = new JButton("");

        encryption = new JPanel();
        encryption.setLayout(null);
        encryption.setBounds(20, 20, 300, 120);
        encryption.setBorder(BorderFactory.createTitledBorder("Encryption"));

        encryption.add(efilePath);
        encryption.add(efilePathText);
        encryption.add(fileChooser);
        encryption.add(encryptFile);
        encryption.add(reset);

        // Decryption
        dfilePath = new JLabel("Enter File Path");
        dfilePath.setBounds(x, y, width, height);

        dfilePathText = new JTextField(15);
        dfilePathText.setBounds(2 * x + width, y, width - 21, height);

        fileChooser2 = new JButton("...");
        fileChooser2.setBounds(260, y, 20, height);

        decryptFile = new JButton("Decrypt");
        decryptFile.setBounds(x, 2 * y + height, width, height);

        reset1 = new JButton("Reset");
        reset1.setBounds(2 * x + width, 2 * y + height, width, height);

        decryption = new JPanel();
        decryption.setLayout(null);
        decryption.setBounds(20, 20 + 100 + 40, 300, 120);
        decryption.setBorder(BorderFactory.createTitledBorder("Decryption"));

        decryption.add(dfilePath);
        decryption.add(dfilePathText);
        decryption.add(fileChooser2);
        decryption.add(decryptFile);
        decryption.add(reset1);

        // Secret Key

        sKey = new JLabel("Enter Key:");
        sKey.setBounds(x, y, width, height);

        sKeyField = new JPasswordField(15);
        sKeyField.setBounds(2 * x + width, y, width, height);

        SaveKey = new JButton("Save");
        SaveKey.setBounds(x, 2 * y + height, width, height);

        setPass = new JCheckBox("Show Password");
        setPass.setBounds(2 * x + width, 2 * y + height, width, height);

        key = new JPanel();
        key.setLayout(null);
        key.setBorder(BorderFactory.createTitledBorder("Secret Key"));
        key.setBounds(20, 20 + 240 + 40, 300, 120);
        key.add(sKey);
        key.add(sKeyField);
        key.add(SaveKey);
        key.add(setPass);

        add(infoPanel);
        add(Back);
        add(key);
        add(encryption);
        add(decryption);
        add(c);

        fileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser();

                j.addChoosableFileFilter(
                        new FileNameExtensionFilter("Files", "png", "jpg", "docx", "pdf", "txt", "jpeg"));
                j.setAcceptAllFileFilterUsed(false);

                int r = j.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = j.getSelectedFile().getAbsolutePath();
                    efilePathText.setText(path);
                } else {
                    efilePathText.setText("");
                }
            }
        });
        

        setPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (setPass.isSelected()) {
                    sKeyField.setEchoChar((char) 0);
                } else {
                    sKeyField.setEchoChar('*');
                }
            }
        });

        fileChooser2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser j1 = new JFileChooser();

                j1.addChoosableFileFilter(new FileNameExtensionFilter("IMAGINE Files", "imagine"));
                j1.setAcceptAllFileFilterUsed(false);
                int r = j1.showOpenDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = j1.getSelectedFile().getAbsolutePath();
                    dfilePathText.setText(path);
                } else {
                    dfilePathText.setText("");
                }
            }
        });

        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new mainFrame(username);
            }
        });

        encryptFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String extension = "";

                int index = efilePathText.getText().lastIndexOf('.');
                if (index > 0) {
                    extension = efilePathText.getText().substring(index + 1);
                }
                if (extCheck(extension)) {
                    if ((String.valueOf(sKeyField.getPassword()).equals("")) || (efilePath.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Please Enter the Secret key and File path.");
                    } else {
                        en.setInputFilePath(efilePathText.getText());
                        en.setOutputFilePath(efilePathText.getText());
                        try {
                            new EncryptionAES(en.getKey(), en.getInputFilePath(), en.getOutputFilePath());
                        } catch (Exception ae) {
                            System.out.println(ae);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This file format doesnt support for file encryption",
                            "Invalid File Format", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        decryptFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String extension = "";
                int index = dfilePathText.getText().lastIndexOf('.');
                if (index > 0) {
                    extension = dfilePathText.getText().substring(index + 1);
                }
                if (extCheckd(extension)) {

                    if ((String.valueOf(sKeyField.getPassword()).equals("")) || (efilePath.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Please Enter the Secret key and File path.");
                    } else {
                        en.setInputFilePath(dfilePathText.getText());
                        en.setOutputFilePath(dfilePathText.getText());
                        try {
                            new DecryptionAES(en.getKey(), en.getInputFilePath(), en.getOutputFilePath());
                        } catch (Exception ae) {
                            System.out.println(ae);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Only IMAGINE files are supported.", "Invalid File Format",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                efilePathText.setText("");
            }
        });

        reset1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dfilePathText.setText("");
            }
        });

        SaveKey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char pass[] = sKeyField.getPassword();

                if (!String.valueOf(pass).equals("")) {
                    if (pass.length < 16) {
                        char pextend[] = new char[16 - pass.length];

                        if (pass.length == 16) {
                            en.setKey(String.valueOf(pass));
                            JOptionPane.showMessageDialog(null, "key saved : " + String.valueOf(pass));
                        } else {
                            for (int i = 0; i < 16 - pass.length; i++) {
                                pextend[i] = '*';
                            }
                            en.setKey(String.valueOf(pextend) + String.valueOf(pass));
                            JOptionPane.showMessageDialog(null, "key saved : " + String.valueOf(pass));
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Key cannot be more than 16 characters");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Key Cannot be null!!");
                }
            }

        });

        setVisible(true);
        setLayout(null);

        pack();
        setTitle("ENCRYPT it!");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public boolean extCheck(String extension) {
        boolean c = false;
        for (int i = 0; i < ext.length; i++) {
            if (ext[i].equals(extension)) {
                c = true;
                break;
            } else {
                c = false;
            }
        }
        return c;
    }

    public boolean extCheckd(String extension) {
        if (extension.equals("imagine")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        if (String.valueOf(sKeyField.getPassword()).length() > 16) {
            keyWarning.setText("Key cannot be bigger than 16 characters");
        } else {
            keyWarning.setText("");
        }
    }

}
