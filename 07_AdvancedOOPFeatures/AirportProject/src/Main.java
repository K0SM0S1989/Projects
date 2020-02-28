
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    private static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Terminal> flList = airport.getTerminals();

        Date date = new Date();
        flList.stream().flatMap(x -> x.getFlights().stream())
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
                .filter(h -> (h.getDate().getHours() - date.getHours()) <= 2)
                .filter(h -> (h.getDate().getHours() - date.getHours()) > 0)
                .filter(h -> h.getDate().getMinutes() <= date.getMinutes())
                .forEach(h -> {
                    System.out.println(HOUR_FORMAT.format(h.getDate()) + " - " + h.getAircraft());
                });
    }

}
