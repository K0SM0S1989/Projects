import core.Line;
import core.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteCalculatorTest{
    List<Station> route;
    List<Station> connection1;
    List<Station> connection2;
    Line line1 = new Line(1, "первая");
    Line line2 = new Line(2, "вторая");
    Line line3 = new Line(3, "третья");
   StationIndex stationIndex;
   RouteCalculator routeCalculator;


    @BeforeEach
    protected void setUp(){


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
    @Test
    public void calculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 19.5;
        assertEquals(expected, actual);
    }

    @Test
    public void getShortestRouteWithOneConnect()  {
        List<Station> actual =  routeCalculator.getShortestRoute(route.get(1),route.get(5));
        List<Station> stationListExpected = new ArrayList<>();
        stationListExpected.add(new Station("Елизаровская",line1));
        stationListExpected.add(new Station("Новочеркасская",line1));
        stationListExpected.add(new Station("Ладожская",line1));
        stationListExpected.add(new Station("Проспект Большевиков",line2));
        stationListExpected.add(new Station("Дыбенко",line2));
        assertEquals(stationListExpected, actual);
    }
    @Test
    public void getShortestRouteWithTwoConnect()  {
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

    @Test
    public void getShortestRouteWithoutConnect()  {
        List<Station> actual =  routeCalculator.getShortestRoute(route.get(0),route.get(3));
        List<Station> stationListExpected = new ArrayList<>();
        stationListExpected.add(new Station("Пролетарская", line1));
        stationListExpected.add(new Station("Елизаровская",line1));
        stationListExpected.add(new Station("Новочеркасская",line1));
        stationListExpected.add(new Station("Ладожская",line1));

        assertEquals(stationListExpected, actual);
    }
    @Test
    public void getShortestRouteWithoutConnectNull()  {
        List<Station> actual =  routeCalculator.getShortestRoute(null, null);
        List<Station> stationListExpected = new ArrayList<>();
        stationListExpected.add(null);
        stationListExpected.add(null);

        assertEquals(stationListExpected, actual);
    }




}
