

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.awt.HeadlessException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.awt.Desktop;

import javax.swing.JOptionPane;

public class EncryptionAES extends Encapsulate {

  private String[] ext = { "pdf", "txt", "png", "docx", "jpeg", "jpg" };
  private String[] extno = { "1", "2", "3", "4", "5", "6" };
  private String Ext = "";

  public EncryptionAES(String Key, String InputFile, String OutputFile)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
      IllegalBlockSizeException, BadPaddingException, HeadlessException {

    // input file
    var key = new SecretKeySpec(Key.getBytes(), "AES");
    var cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);

    var fileInput = new File(InputFile);
    var inputStream = new FileInputStream(fileInput);
    var inputBytes = new byte[(int) fileInput.length()];

    inputStream.read(inputBytes);

    var outputBytes = cipher.doFinal(inputBytes);

    // output file
    var fileEncryptOut = new File(OutputFile);
    var outputStream = new FileOutputStream(fileEncryptOut);
    outputStream.write(outputBytes);

    inputStream.close();
    outputStream.close();

    {
      File file = new File(OutputFile);
      // convert the file name into string
      String fileName = file.toString();
      String extension = "";

      int index = fileName.lastIndexOf('.');
      if (index > 0) {
        extension = fileName.substring(index + 1);
      }
      Ext = extCheck(extension);
      changeExtension(file, ".imagine");

      int input = JOptionPane.showOptionDialog(null, "Do you want to goto the parent Folder?",
          "Encryption Successful!!!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

      if (input == JOptionPane.OK_OPTION) {
        try {
          if (!Desktop.isDesktopSupported())
          // check if Desktop is supported by Platform or not
          {
            System.out.println("not supported");
            return;
          }
          Desktop desktop = Desktop.getDesktop();
          if (file.getParentFile().exists()) // checks file exists or not
            desktop.open(file.getParentFile());
          // opens the specified file
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    }

  }

  public boolean changeExtension(File f, String newExtension) {
    int i = f.getName().lastIndexOf('.');

    String name = f.getName().substring(0, i);
    return f.renameTo(new File(f.getParent(), name + Ext + newExtension));
  }

  String extCheck(String extension) {
    String c = "";
    for (int i = 0; i < extno.length; i++) {
      if (ext[i].equals(extension)) {
        c = extno[i];
        break;
      } else {
        c = "";
      }
    }
    return c;
  }
}
