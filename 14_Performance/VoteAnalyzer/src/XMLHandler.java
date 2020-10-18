import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private Voter voter;
    private final HashMap<Voter, Integer> voterCounts;
    private final HashMap<Integer, WorkTime> voteStationWorkTimes;
//    private final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
//    private final SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private final SimpleDateFormat birthDayFormat;
    private final SimpleDateFormat visitDateFormat;


    public XMLHandler(SimpleDateFormat birthDayFormat, SimpleDateFormat visitDateFormat) {
        voterCounts = new HashMap<>();
        voteStationWorkTimes = new HashMap<>();
        this.birthDayFormat = birthDayFormat;
        this.visitDateFormat = visitDateFormat;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                Date birthday = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthday);
                int count = voterCounts.getOrDefault(voter, 0);
                voterCounts.put(voter, count + 1);
            } else if (qName.equals("visit") && voter != null) {
                Integer station = Integer.parseInt(attributes.getValue("station"));
                Date time = visitDateFormat.parse(attributes.getValue("time"));
                WorkTime workTime = voteStationWorkTimes.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    voteStationWorkTimes.put(station, workTime);
                }
                workTime.addVisitTime(time.getTime());
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void printDublicatedVoters() {
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter.toString() + " - " + count);
            }
        }
    }

    public void printWorkTimes() {
        for (int votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }
    }
}
