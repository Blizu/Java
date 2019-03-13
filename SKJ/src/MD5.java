import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import static java.lang.System.out;


public class MD5 {
    public File katalog;
    public MD5() {
    }

    String zwrocMD5(File katalog) {

        String wynik = null;
        for (File fileEntry : katalog.listFiles()) {
            try {
                MessageDigest messageDiegest = MessageDigest.getInstance("MD5");
                InputStream inputStream = Files.newInputStream(fileEntry.toPath());
                DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDiegest);
                while (digestInputStream.read() != -1) ;
                byte[] digest = messageDiegest.digest();
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b : digest) {
                    stringBuffer.append(String.format("%02x", b));
                }
                wynik += (fileEntry.getName() + " " + stringBuffer.toString() + "\r\n");

            } catch (Exception ex) {
                out.println(ex.getMessage());
            } finally {
            }
        }
        return wynik;
    }
}
