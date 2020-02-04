import com.skillbox.airport.Airport;

public class Main {
    public static void main(String[] args) {
        Airport pulkovo = Airport.getInstance();
        System.out.println(pulkovo.getAllAircrafts());
        System.out.println("Количество самолетов = "+pulkovo.getAllAircrafts().size());

    }
}
