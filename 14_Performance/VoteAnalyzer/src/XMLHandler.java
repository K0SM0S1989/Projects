import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;


public class XMLHandler extends DefaultHandler {

    private StringBuilder builder = new StringBuilder();
    private long count = 0;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
            String birthDay = attributes.getValue("birthDay");
            birthDay = birthDay.replace('.', '-');
            String name = attributes.getValue("name");

            builder.append((builder.length() == 0 ? "" : ",")
                    + "('" + name + "', '" + birthDay + "')");
            count++;
            if (count % 80000 == 0) {
                try {
                    DBConnection.executeMultiInsert(builder);
                    builder = new StringBuilder();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }


    @Override
    public void endDocument() throws SAXException {
        try {
            DBConnection.executeMultiInsert(builder);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
