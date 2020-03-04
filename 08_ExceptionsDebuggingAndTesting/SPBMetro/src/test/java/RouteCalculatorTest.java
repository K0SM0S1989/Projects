import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    List<Station> connection1;
    List<Station> connection2;
    Line line1 = new Line(1, "первая");
    Line line2 = new Line(2, "вторая");
    Line line3 = new Line(3, "третья");
   StationIndex stationIndex;
   RouteCalculator routeCalculator;


    @Override
    protected void setUp() throws Exception {


        route = new ArrayList<>();
        route.add(new Station("Пролетарская",line1));
        route.add(new Station("Елизаровская",line1));
        route.add(new Station("Новочеркасская",line1));
        route.add(new Station("Ладожская",line1));
        route.add(new Station("Проспект Большевиков",line2));
        route.add(new Station("Дыбенко",line2));
        route.add(new Station("Политехническая",line3));
        route.add(new Station("Академическая",line3));

        connection1 = new ArrayList<>();
        connection1.add(route.get(3));
        connection1.add(route.get(4));

        connection2 = new ArrayList<>();
        connection2.add(route.get(5));
        connection2.add(route.get(6));

        line1.addStation(route.get(0));
        line1.addStation(route.get(1));
        line1.addStation(route.get(2));
        line1.addStation(route.get(3));
        line2.addStation(route.get(4));
        line2.addStation(route.get(5));
        line3.addStation(route.get(6));
        line3.addStation(route.get(7));
        stationIndex = new StationIndex();
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);

        routeCalculator = new RouteCalculator(stationIndex);

    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 19.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteWithOneConnect()  {

        List<Station> actual =  routeCalculator.getShortestRoute(route.get(1),route.get(5));
        List<Station> stationListExpected = new ArrayList<>();
        stationListExpected.add(new Station("Елизаровская",line1));
        stationListExpected.add(new Station("Новочеркасская",line1));
        stationListExpected.add(new Station("Ладожская",line1));
        stationListExpected.add(new Station("Проспект Большевиков",line2));
        stationListExpected.add(new Station("Дыбенко",line2));
        assertEquals(stationListExpected, actual);
    }
    public void testGetShortestRouteWithTwoConnect()  {

        List<Station> actual =  routeCalculator.getShortestRoute(route.get(1),route.get(7));
        List<Station> stationListExpected = new ArrayList<>();
        stationListExpected.add(new Station("Елизаровская",line1));
        stationListExpected.add(new Station("Новочеркасская",line1));
        stationListExpected.add(new Station("Ладожская",line1));
        stationListExpected.add(new Station("Проспект Большевиков",line2));
        stationListExpected.add(new Station("Дыбенко",line2));
        stationListExpected.add(new Station("Политехническая",line3));
        stationListExpected.add(new Station("Академическая",line3));
        assertEquals(stationListExpected, actual);
    }



    @Override
    protected void tearDown() throws Exception {

    }
}
