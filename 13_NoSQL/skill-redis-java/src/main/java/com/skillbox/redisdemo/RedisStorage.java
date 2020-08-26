package com.skillbox.redisdemo;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.List;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> onlineUsers;

    private final static String KEY = "ONLINE_USERS";

    private double getTs() {
        return new Date().getTime() / 1000;
    }


    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        onlineUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    // Регистрация пользователей по времени
    void logPageVisit(int user_id) {
        //ZADD ONLINE_USERS
        onlineUsers.add(getTs(), String.valueOf(user_id));
    }

    // Удаление
    void deleteOldEntries(int removeId) {
        //ZREVRANGEBYSCORE ONLINE_USERS 0 <time_5_seconds_ago>??? ZREMRANGEBYSCORE ???
        onlineUsers.remove(removeId);

    }

    //Вывод всех пользователей в список
    List<String> output() {
        List<String> userList = (List<String>) onlineUsers.readAll();
        return userList;
    }
}
