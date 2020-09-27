import app.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class ApplicationTest {
    private Application application;

    @Container
    public GenericContainer mongoDBContainer = new GenericContainer("mongo:4.0.10")
            .withExposedPorts(27017);

    @BeforeEach
    void setup() {
        String host = mongoDBContainer.getHost();
        int port = mongoDBContainer.getMappedPort(27017);
        application = new Application();
        application.init(host, port);

        application.addShop("ДОБАВИТЬ_МАГАЗИН смарт");
        application.addProduct("ДОБАВИТЬ_ТОВАР виски 1500");
    }

    @Test
    void addShopTest() {
        String actual = application.addShop("ДОБАВИТЬ_МАГАЗИН лента");
        String expected = "лента";
        assertEquals(expected, actual);
    }

    @Test
    void addShopTestWrongCommand() {
        String actual = application.addShop("ДОБАВИТЬ_МАГАЗИН ");
        String expected = "Неправильный ввод магазина";
        assertEquals(expected, actual);
    }

    @Test
    void addProductTest() {
        String actual = application.addProduct("ДОБАВИТЬ_ПРОДУКТ хлеб 55");
        String expected = "хлеб";
        assertEquals(expected, actual);
    }

    @Test
    void addProductTestWrongCommand() {
        String actual = application.addProduct("ДОБАВИТЬ_ПРОДУКТ хлеб");
        String expected = "Неправильный ввод товара";
        assertEquals(expected, actual);
    }

    @Test
    void displayProductTestNullShopOrProduct() {
        String actual = application.displayProduct("ВЫСТАВИТЬ_ТОВАР сухари ларёк");
        String expected = "Нет такого товара или магазина";
        assertEquals(expected, actual);
    }

    @Test
    void displayProductTest() {
        String actual = application.displayProduct("ВЫСТАВИТЬ_ТОВАР виски смарт");
        String expected = "Товар виски выставлен в магазине смарт";
        assertEquals(expected, actual);
    }

    @Test
    void productStatisticsTest() {
        application.displayProduct("ВЫСТАВИТЬ_ТОВАР виски смарт");
        StringBuilder actual = application.productStatistics();
        StringBuilder expected = new StringBuilder();
        expected.append("Название магазина: смарт" + "\n")
                .append("Общее количество товаров: " + 1 + "\n")
                .append("Средняя цена товаров: " + 1500.0 + "\n")
                .append("Самый дорогой товар: " + 1500 + "\n")
                .append("Самый дешёвый товар: " + 1500 + "\n")
                .append("Количество товаров дешевле 100 рублей: " + 0 + "\n");

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void productStatisticsNullProdInShopTest() {
        StringBuilder actual = application.productStatistics();
        String expected = "В магазине смарт товаров не выставлено\n";

        assertEquals(expected, actual.toString());
    }
}
