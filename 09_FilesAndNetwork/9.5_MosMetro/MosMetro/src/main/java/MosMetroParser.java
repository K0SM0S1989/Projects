import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import java.util.*;

public class MosMetroParser {
    private static Map<String, List<String>> stations = new LinkedHashMap<>();
    private static Map<String, String> linesItem;
    private static List<Map> lines = new ArrayList<>();
    private static String[] lineColors = {"red", "green", "blue", "blue",
            "brown", "orange", "violet", "yellow", "grey", "green", "blue", "blue", "pink"};
    private static LinkedHashSet<Map> linesSet = new LinkedHashSet<>();
    private static Map<String, Object> mosMetro = new LinkedHashMap<>();
    private static List<List> connections = new ArrayList<>();

    public static void getMosMetro() throws IOException {
        Document page = Jsoup.connect("https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена").get();
        Element tableBody = page.select("tbody").get(3);

        Elements elements = tableBody.select("tr").next();
        stationsAndLines(tableBody);

        connections.addAll(connectionsMetod(tableBody,page));



        for (Element el : elements) {
            linesItemMetod(el);
            for (String key : linesItem.keySet()) {
                linesSet.add(linesItemMetod(el));
            }
        }


        lines.addAll(linesSet);
        mosMetro.put("stations", stations);
        mosMetro.put("lines", lines);
        mosMetro.put("connections", connections);


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/main/resources/map.json"), mosMetro);

    }

    private static Map stationsAndLines(Element tbody) {
        final List[] list = new List[]{new ArrayList<>()};
        Elements elements = tbody.select("tr").next();
        elements.stream().filter(element -> !element.hasClass("shadow"))
                .map(element -> {
                    String lineNumberString;
                    Element lineNumber = element.select("span[class=sortkey]").get(0);
                    Element station = element.select("a").get(1);
                    lineNumberString = lineNumber.text();
                    if (element.select("td").get(0).select("span").size() > 3){
                        lineNumberString = element.select("span[class=sortkey]").get(1).text();
                        station = element.select("a").get(2);
                    }
                    if (!stations.containsKey(lineNumberString)) {
                        list[0] = new ArrayList<>();
                    }
                    list[0].add(station.text());
                    return lineNumberString;
                }).forEach(key -> stations.put(key, list[0]));

        addStation(stations);
        return stations;
    }

    private static void addStation(Map<String, List<String>> map) {
        for (int i = 1; i < map.get("11").size(); i++) {
            if (map.containsKey("8А")) {
                map.get("8А").add(map.get("11").get(i));
            }
        }
    }


    private static Map linesItemMetod(Element tr) {

        linesItem = new LinkedHashMap<>();
        String[] keys = {"number", "name", "color"};
        String lineNumber;
        if (!tr.hasClass("shadow")) {
            Element td = tr.select("td").get(0);
            Element elSpanes = td.select("span").get(1);

            lineNumber = td.select("span[class=sortkey]").get(0).text();

            String s = elSpanes.attr("title");
            linesItem.put(keys[0], lineNumber);
            linesItem.put(keys[1], s);

            switch (td.attr("style")) {
                case "background:#EF161E":
                    linesItem.put(keys[2], lineColors[0]);
                    break;
                case "background:#2DBE2C":
                    linesItem.put(keys[2], lineColors[1]);
                    break;
                case "background:#0078BE":
                    linesItem.put(keys[2], lineColors[2]);
                    break;
                case "background:#00BFFF":
                    linesItem.put(keys[2], lineColors[3]);
                    break;
                case "background:#8D5B2D":
                    linesItem.put(keys[2], lineColors[4]);
                    break;
                case "background:#ED9121":
                    linesItem.put(keys[2], lineColors[5]);
                    break;
                case "background:#800080":
                    linesItem.put(keys[2], lineColors[6]);
                    break;
                case "background:#FFD702":
                    linesItem.put(keys[2], lineColors[7]);
                    break;
                case "background:#999999":
                    linesItem.put(keys[2], lineColors[8]);
                    break;
                case "background:#99CC00":
                    linesItem.put(keys[2], lineColors[9]);
                    break;
                case "background:#82C0C0":
                    linesItem.put(keys[2], lineColors[10]);
                    break;
                case "background:#A1B3D4":
                    linesItem.put(keys[2], lineColors[11]);
                    break;
                case "background:#DE64A1":
                    linesItem.put(keys[2], lineColors[12]);
                    break;
                case "background:background-color: #FFD702;" +
                        " background-image:" +
                        " -moz-linear-gradient(top, #FFD702, #82C0C0);" +
                        " background-image: -o-linear-gradient(top, #FFD702, #82C0C0);" +
                        " background-image: -webkit-gradient(linear, left top, left bottom, from(#FFD702)," +
                        " to(#82C0C0));":
                    linesItem.put(keys[2], lineColors[7]);
                    break;
            }
        }

        return linesItem;
    }
    private static List connectionsMetod(Element tbody, Document page) throws IOException {

        Map<String,List<String>> allStatioons = new LinkedHashMap<>();
        List<List> connectionListMetod = new ArrayList<>();
        List<Map> oneConnection;
        HashSet<String> selection = new HashSet<>();
        TreeMap<String, String> oneItemOfConnectionMap;
        String[] keyString = {"line", "station"};
        Elements trs = tbody.select("tr").next();

        allStatioons.putAll(stationsAndLinesForConection(tbody));
        allStatioons.putAll(addMCK(page));
        allStatioons.putAll(addMono(page));
        for (Element element : trs) {

            boolean selectionBoolean = true;
            oneItemOfConnectionMap = new TreeMap<>();

            if (!element.hasClass("shadow")) {
                String lineNumber = element.select("span[class=sortkey]").get(0).text();
                String station = element.select("a").get(1).text();
                oneConnection = new ArrayList<>();

                if (element.select("td").get(0).select("span").size() > 3) {
                    station = element.select("a").get(2).text();
                    oneItemOfConnectionMap.put(keyString[0], lineNumber);
                    oneItemOfConnectionMap.put(keyString[1], station);
                    oneConnection.add(oneItemOfConnectionMap);
                    oneItemOfConnectionMap = new TreeMap<>();
                    lineNumber = element.select("span[class=sortkey]").get(1).text();
                    oneItemOfConnectionMap.put(keyString[0], lineNumber);
                    oneItemOfConnectionMap.put(keyString[1], station);
                    oneConnection.add(oneItemOfConnectionMap);
                } else {
                    oneItemOfConnectionMap.put(keyString[0], lineNumber);
                    oneItemOfConnectionMap.put(keyString[1], station);
                    oneConnection.add(oneItemOfConnectionMap);
                }

                selection.add(lineNumber);
                Element td = element.select("td").get(3);
                if (!td.attr("data-sort-value").equals("Infinity")) {
                    Elements spans = td.select("span");
                    String connectLineNumber;
                    for (Element span : spans) {
                        if (!span.text().equals("")) {
                            oneItemOfConnectionMap = new TreeMap<>();
                            connectLineNumber = span.text();
                            oneItemOfConnectionMap.put(keyString[0], connectLineNumber);
                            if (selection.contains(connectLineNumber)) {
                                selectionBoolean = false;
                            }
                            continue;
                        }
                        if (!span.attr("title").equals("")){
                            for (String st : allStatioons.get(oneItemOfConnectionMap.get(keyString[0]))){
                                if (span.attr("title").contains(st)){
                                    oneItemOfConnectionMap.put(keyString[1],st);
                                }
                            }
                        }

                        oneConnection.add(oneItemOfConnectionMap);
                    }
                    if (selectionBoolean) {
                        connectionListMetod.add(oneConnection);
                    }
                }
            }
        }
        return connectionListMetod;
    }

    private static Map stationsAndLinesForConection(Element tbody) {
        Map<String, List<String>> allStations = new LinkedHashMap<>();
        final List[] list = new List[]{new ArrayList<>()};
        Elements elements = tbody.select("tr").next();
        elements.stream()
                .map(element -> {
                    String lineNumberString;
                    Element lineNumber = element.select("span[class=sortkey]").get(0);
                    Element station = element.select("a").get(1);
                    lineNumberString = lineNumber.text();
                    if (element.select("td").get(0).select("span").size() > 3){
                        lineNumberString = element.select("span[class=sortkey]").get(1).text();
                        station = element.select("a").get(2);
                    }
                    if (!allStations.containsKey(lineNumberString)) {
                        list[0] = new ArrayList<>();
                    }
                    list[0].add(station.text());
                    return lineNumberString;
                }).forEach(key -> allStations.put(key, list[0]));
        addStation(allStations);
        return allStations;
    }

    private static Map addMCK(Document page){
        Element element = page.select("tbody").get(5);
        Map<String, List<String>> mono = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        Elements tr = element.select("tr");
        tr.stream().filter(element1 -> element1 != tr.get(1))
                .map(el -> {
                    String lineNumberString;
                        Element lineNumber = el.select("span").get(0);
                        Element station = el.select("a").get(1);
                        lineNumberString = lineNumber.text();
                        list.add(station.text());
                        return lineNumberString;
                }).forEach(key -> mono.put(key, list));
        return mono;
    }
    private static Map addMono(Document page){
        Element element = page.select("tbody").get(4);
        Map<String, List<String>> mono = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        Elements tr = element.select("tr").next().next();

        tr.stream()
                .map(el -> {
                    String lineNumberString;
                    Element lineNumber = el.select("span").get(0);
                    Element station = el.select("a").get(1);
                    lineNumberString = lineNumber.text();
                    list.add(station.text());
                    return lineNumberString;
                }).forEach(key -> mono.put(key, list));
        return mono;
    }
}
