import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RootPage {
    private static final List<String> uriList = new ArrayList<>();
    private static final String adress = "https://lenta.ru";
    private static final char firstSymbol = '/';


    static List<String> go() {

        try {
            Document page = Jsoup.connect(adress).get();
            Elements katalog = page.select("li.b-sidebar-menu__list-item");
            katalog.forEach(li -> {
                Elements aEl = li.select("a");
                aEl.forEach(a -> {
                    String href = a.attr("href");
                    if (href.length() > 1 && href.charAt(0) == firstSymbol) {
                        uriList.add(adress.concat(href));
                    }
                });
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        return uriList;
    }


}
