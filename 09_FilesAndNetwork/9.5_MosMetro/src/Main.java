
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            MosMetroParser.getMosMetro();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = getJsonFile();
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(data);
            JSONObject stat = (JSONObject) object.get("stations");
            JSONArray array = (JSONArray) object.get("lines");
            array.forEach(o -> {
                JSONArray statArray = (JSONArray) stat.get(((JSONObject) o).get("number"));
                System.out.println(((JSONObject) o)
                        .get("number").toString() + " - " + ((JSONObject) o)
                        .get("name") + " - " + statArray.size() + " станций(и)");
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("data/map.json"));
            lines.forEach(builder::append);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

}



