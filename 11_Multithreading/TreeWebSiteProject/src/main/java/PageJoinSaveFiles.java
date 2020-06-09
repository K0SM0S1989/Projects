import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.RecursiveTask;

import static java.lang.Thread.sleep;

public class PageJoinSaveFiles extends RecursiveTask<String> {

    String peripheryPage;
    String namePageString;
    List<String> stringList;
    String table;


    public PageJoinSaveFiles(String peripheryPage, List<String> stringList, String table) {
        this.peripheryPage = peripheryPage;
        this.stringList = stringList;
        this.table = table;
    }

    @Override
    protected String compute() {
        try {
            sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return table + searchUri(peripheryPage, stringList);
    }


    String searchUri(String uriSimple, List<String> stringList) {
        namePageString = uriSimple;
        String adress = "https://lenta.ru";
        char firstSymbol = '/';
        List<String> uriListForThisMethod = new ArrayList<>();
        List<String> juniorChildList = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.connect(uriSimple).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element thisMethodElement = document.select("nav.b-tags-list.js-tags").get(0);
        Element divEl = thisMethodElement.select("div").get(3);
        Elements elements = divEl.select("a");
        for (int i = 1; i < elements.size(); i++) {
            Element element = elements.get(i);
            String href = element.attr("href");
            if (!stringList.contains(adress.concat(href)) && href.charAt(0) == firstSymbol) {
                uriListForThisMethod.add(adress.concat(href));
            } else {
                Elements junior = document.select("h4");
                junior.forEach(j -> {
                    Element aEl = j.select("a").get(0);
                    String juniorHref = aEl.attr("href");
                    if (juniorHref.charAt(0) == firstSymbol) {
                        juniorChildList.add(adress.concat(juniorHref));
                    }
                });
                break;
            }
        }
        List<PageJoinSaveFiles> taskList = new ArrayList<>();
        table += "\t";
        if (juniorChildList.isEmpty()) {
            uriListForThisMethod.forEach(s -> {
                PageJoinSaveFiles task = new PageJoinSaveFiles(s, uriListForThisMethod, table);
                task.fork();
                taskList.add(task);
            });
            taskList.forEach(task -> namePageString += "\n" + table + task.join());
            return namePageString;

        } else {
            juniorChildList.forEach(string -> namePageString += "\n" + table + table + string);
            return namePageString;
        }

    }

}
