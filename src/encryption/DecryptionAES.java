import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import java.awt.Desktop;

public class DecryptionAES extends Encapsulate {

  private String[] ext = { ".pdf", ".txt", ".png", ".docx", ".jpeg", ".jpg" };
  private String[] extno = { "1.imagine", "2.imagine", "3.imagine", "4.imagine", "5.imagine", "6.imagine" };
  private String Ext = "";
  FileInputStream inputStream;
  FileOutputStream outputStream;

  public DecryptionAES(String Key, String InputFile, String OutputFile) throws NoSuchAlgorithmException,
      NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {

    String OF = OutputFile;

    try {
      var key = new SecretKeySpec(Key.getBytes(), "AES");
      var cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.DECRYPT_MODE, key);

      var fileInput = new File(InputFile);
      inputStream = new FileInputStream(fileInput);
      var inputBytes = new byte[(int) fileInput.length()];
      inputStream.read(inputBytes);

      byte[] outputBytes = cipher.doFinal(inputBytes);

      var fileEncryptOut = new File(OutputFile);
      outputStream = new FileOutputStream(fileEncryptOut);
      outputStream.write(outputBytes);

    } catch (BadPaddingException e) {
      JOptionPane.showMessageDialog(null, "Invalid Key");
    }

    inputStream.close();

    try {
      outputStream.close();

      File file = new File(OF);

      // convert the file name into string
      String fileName = file.toString();
      String extension = "";

      int index = fileName.lastIndexOf('.');
      if (index > 0) {
        extension = fileName.substring(index - 1);
      }
      Ext = extCheck(extension);
      changeExtension(file, Ext);

      int input = JOptionPane.showOptionDialog(null, "Do you want to goto the parent Folder?",
          "Decryption Successful!!!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

      if (input == JOptionPane.YES_OPTION) {
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
    } catch (NullPointerException e) {
    }

  }

  public boolean changeExtension(File f, String newExtension) {
    int i = f.getName().lastIndexOf('.');

    String name = f.getName().substring(0, i - 1);

    return f.renameTo(new File(f.getParent(), name + newExtension));
  }

  String extCheck(String extension) {
    String c = "";
    for (int i = 0; i < extno.length; i++) {
      if (extno[i].equals(extension)) {
        c = ext[i];
        break;
      } else {
        c = "";
      }
    }
    return c;
  }
}
