import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader {
    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static final HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static final HashMap<Voter, Integer> voterCounts = new HashMap<>();



    public static void main(String[] args) throws Exception {
        String fileName = "res/data-1572M.xml";
        long start = System.currentTimeMillis();

        SAXParserFactory factory = SAXParserFactory.newNSInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        System.out.println(System.currentTimeMillis() - start + " - Parsing duration of DB");

        //====================================================================================================
//        long usageSAX = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//

//        handler.printDublicatedVoters();
//        handler.printWorkTimes();
//
//        usageSAX = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) - usageSAX;
//        System.out.println(usageSAX + " - SAXParser");

        //=====================================================================================================
//
//        long usageDOM = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        parseFile(fileName);
//
//        //Printing results
//        System.out.println("Voting station work times: ");
//        for (Integer votingStation : voteStationWorkTimes.keySet()) {
//            WorkTime workTime = voteStationWorkTimes.get(votingStation);
//            System.out.println("\t" + votingStation + " - " + workTime);
//        }
//
//        System.out.println("Duplicated voters: ");
//        for (Voter voter : voterCounts.keySet()) {
//            Integer count = voterCounts.get(voter);
//            if (count > 1) {
//                System.out.println("\t" + voter + " - " + count);
//            }
//        }
//        usageDOM = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) - usageDOM;
//        System.out.println(usageDOM + " - DOMParser");
    }

    private static void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
//        fixWorkTimes(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        StringBuilder builder = new StringBuilder();
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            //Date birthDay = birthDayFormat.parse(attributes.getNamedItem("birthDay").getNodeValue());
            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();
            // DBConnection.countVoter(name, birthDay);
//            Voter voter = new Voter(name, birthDay);
//            Integer count = voterCounts.get(voter);
//            voterCounts.put(voter, count == null ? 1 : count + 1);
            birthDay = birthDay.replace('.', '-');
            builder.append((builder.length() == 0 ? "" : ",")
                    + "('" + name + "', '" + birthDay + "', 1)");
            if (builder.length() > 1024) {
                DBConnection.executeMultiInsert(builder);
                builder = new StringBuilder();
            }
        }
        //  DBConnection.executeMultiInsert();
    }

    private static void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}