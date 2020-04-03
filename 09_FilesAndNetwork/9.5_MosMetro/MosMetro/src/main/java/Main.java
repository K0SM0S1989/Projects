import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            MosMetroParser.getMosMetro();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ObjectMapper mapper = new ObjectMapper();
        try {
            Map obj = mapper.readValue(new File("src/main/resources/map.json"), Map.class);
            Map stations = (Map) obj.get("stations");
            List<Map> lines = (ArrayList) obj.get("lines");
            lines.forEach(o -> {
                List stationList = (ArrayList) stations.get(o.get("number"));

                System.out.println(o.get("number") + " - " + o.get("name") + " - " + stationList.size() + " станций(и)");
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
