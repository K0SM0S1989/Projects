import com.skillbox.redisdemo.RedisStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
class RedisStorageTest {
    private RedisStorage redisStorage;

    @Container
    public GenericContainer redis = new GenericContainer("redis:5.0.3-alpine")
            .withExposedPorts(6379);

    @BeforeEach
    void setup() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();

        // Now we have an address and port for Redis, no matter where it is running
        redisStorage = new RedisStorage(address, String.valueOf(port));
        redisStorage.init();

        redisStorage.logPageVisit(new Date().getTime() / 1000, 1);
        redisStorage.logPageVisit(new Date().getTime() / 1000, 3);


    }


    @Test
    void logPageVisitTest() {
        redisStorage.logPageVisit(new Date().getTime() / 1000, 2);
        String expected = "2";
        assertEquals(expected, redisStorage.output().get(1));
    }

    @Test
    void deleteOldEntriesTest() throws InterruptedException {

        int removeId = 3;

        redisStorage.deleteOldEntries(removeId);
        List<String> afterDelete = redisStorage.output();
        List<String> expected = new ArrayList<>();
        expected.add("1");
        assertEquals(expected, afterDelete);

    }

    @Test
    void outputTest() {
        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("3");
        assertEquals(expected, redisStorage.output());
    }
}