import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

class PageJoinSaveFiles extends RecursiveAction {
    private final String adress;
    private final String newUri;
    private final ConcurrentSkipListSet<String> result;
    private final ConcurrentSkipListSet<String> treeResult;
    private String indentSymbol = "\t";
    private String tagsPageNotFound;
    private String partsPageNotFound;

    public PageJoinSaveFiles(String adress, String newUri,
                             ConcurrentSkipListSet<String> result,
                             String indentSymbol,
                             ConcurrentSkipListSet<String> treeResult,
                             String tagsPageNotFound,
                             String partsPageNotFound) {
        this.adress = adress;
        this.result = result;
        this.newUri = newUri;
        this.indentSymbol += indentSymbol;
        this.treeResult = treeResult;
        this.tagsPageNotFound = tagsPageNotFound;
        this.partsPageNotFound = partsPageNotFound;
    }

    @Override
    protected void compute() {


        treeResult.add(indentSymbol + newUri);
        parsing();
    }

    private void parsing() {
        Pattern pattern = Pattern.compile(adress);
        List<String> newUriList = new ArrayList<>();
        try {

            Document page = Jsoup.connect(newUri).get();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String link = page.select("link").first().attr("href");
            Matcher outsideUri = pattern.matcher(link);

            if (outsideUri.find()) {
                Elements elements = page.select("a");
                for (int i = 1; i < elements.size(); i++) {
                    String href = elements.get(i).attr("href");


                    Pattern p = Pattern.compile(tagsPageNotFound);
                    Pattern pat = Pattern.compile(partsPageNotFound);
                    Matcher notFoundUri = pat.matcher(href);
                    Matcher matcher = p.matcher(href);
                    char firstSymbol = '/';
                    if (href.length() >= 1 && href.charAt(0) == firstSymbol && !matcher.find() && !notFoundUri.find()) {

                        if (!result.contains(adress.concat(href)) && !newUriList.contains(adress.concat(href))) {
                            newUriList.add(adress.concat(href));
                            result.add(adress.concat(href));

                        }

                    }
                }

                List<PageJoinSaveFiles> tasklist = new ArrayList<>();
                if (!newUriList.isEmpty()) {

                    newUriList.forEach(s -> {

                        PageJoinSaveFiles task = new PageJoinSaveFiles(adress, s, result, indentSymbol, treeResult, tagsPageNotFound, partsPageNotFound);
                        tasklist.add((PageJoinSaveFiles) task.fork());
                    });
                    invokeAll(tasklist);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
