import java.io.*;

public class Pliki extends Thread {

private String katalogDocelowy;
private String lokalnaSciezka;

private String katalogRoboczy = System.getProperty("user.dir");

    public Pliki(){};
    public Pliki(String katalog){
        this.katalogDocelowy =katalog;
    }


    @Override
    public void run() {
        String sciezkaRobocza=katalogRoboczy+"\\"+oddziel(katalogDocelowy);
        File katalogZrodlowy = new File(sciezkaRobocza);
        File katalogDoc = new File (katalogDocelowy);

        kopiujFolder(katalogZrodlowy,katalogDoc);


    }


public String oddziel(String katalog){
        String wartoscPoczatkowa = katalog;
        String wartoscOczekiwana = wartoscPoczatkowa.substring(3,12);
       return wartoscOczekiwana;
}
    public static void kopiujFolder(File katalogZrodlowy, File katalogDocelowy) {
        if (katalogZrodlowy.isDirectory()) {
            if (!katalogDocelowy.exists()) {
                katalogDocelowy.mkdirs();
            }

            String pliki[] = katalogZrodlowy.list();

            for (String file : pliki) {
                File srcFile = new File(katalogZrodlowy, file);
                File destFile = new File(katalogDocelowy, file);

                kopiujFolder(srcFile, destFile);
            }
        } else {
            InputStream in = null;
            OutputStream out = null;

            try {
                in = new FileInputStream(katalogZrodlowy);
                out = new FileOutputStream(katalogDocelowy);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            } catch (Exception e) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
