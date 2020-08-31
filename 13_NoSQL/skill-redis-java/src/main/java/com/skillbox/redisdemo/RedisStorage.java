package com.skillbox.redisdemo;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.*;

import static java.lang.System.out;


public class RedisStorage {
    private String address;
    private String port;

    public RedisStorage() {
    }

    public RedisStorage(String address, String port) {
        this.address = address;
        this.port = port;
    }

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> onlineUsers;

    private final static String KEY = "ONLINE_USERS";


    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + address + ":" + port);
        //config.useSingleServer().setAddress("redis://127.0.0.1:6379");
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
    public void logPageVisit(double time, int user_id) {
        //ZADD ONLINE_USERS
        onlineUsers.add(time, String.valueOf(user_id));
       // out.println("Redis after add - " + onlineUsers.readAll());
    }

    // Удаление
    public void deleteOldEntries(int removeId) {
        //ZREVRANGEBYSCORE ONLINE_USERS 0 <time_5_seconds_ago>??? ZREMRANGEBYSCORE ???
        onlineUsers.remove(String.valueOf(removeId));

        //out.println("Redis after delete - " + onlineUsers.readAll());

    }

    //Вывод всех пользователей в список
    public ArrayList<String> output() {

        ArrayList<String> userList = (ArrayList<String>) onlineUsers.readAll();
        return userList;
    }
}
