import java.io.*;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DownloadImages {
    private static String folderPath = "ForImage";

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://lenta.ru").get();
            Elements img = doc.getElementsByTag("img");
            for (Element el : img) {
                String src = el.absUrl("src");
                System.out.println("Image Found!");
                System.out.println("src attribute is : " + src);
                getImages(src);
            }

        } catch (IOException ex) {
            System.err.println("There was an error");
        }
    }

    private static void getImages(String src) throws IOException {
        String wrangSymbol = "[\\:*?\"<>|]";
        int indexname = src.lastIndexOf("/");
        String nameAll = src.substring(indexname, src.length());
        String name = nameAll.replaceAll(wrangSymbol, "_");
        System.out.println(name);
        URL url = new URL(src);
        InputStream in = url.openStream();
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name))) {
            for (int b = 0; (b = in.read()) != -1; ) {
                out.write(b);
            }
        }
        in.close();
    }
}
