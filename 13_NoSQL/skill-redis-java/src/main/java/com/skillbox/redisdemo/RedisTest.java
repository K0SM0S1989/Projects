package com.skillbox.redisdemo;


import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

//    Host = 127.0.0.1
//    Port = 6379
//    Protocol/Scheme = Tcp

    private static String addres = "127.0.0.1";
    private static String port = "6379";


    //  20 зарегистрированных пользователей
    private static final int USERS = 20;

    // Задержка при повторе бесконечного цикла
    private static final int SLEEP = 1000; // 1 секунда

    // private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(int i, int UsersOnline) {
        String log = String.format(i + ") На главной странице показываем пользователя: %d", UsersOnline);
        out.println(log);
    }

    private static double getTs() {
        return new Date().getTime() / 1000;
    }

    private static void logVIP(int UsersOnline) {
        String log = String.format("Пользователь %d оплатил платную услугу", UsersOnline);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage(addres, port);
        redis.init();
        int i = 1;

        for (int id = 1; id <= USERS; id++) {
            redis.logPageVisit(getTs(), id);
        }
        ArrayList<String> usersList = redis.output();

        while (true) {
            for (int j = 0; j < USERS; j++) {
                if (i % 10 != 0) {
                    log(i, Integer.parseInt(usersList.get(j)));
                } else {
                    int user_id = new Random().nextInt(USERS) + 1;
                    logVIP(user_id);
                    log(i, user_id);
                    int index = usersList.indexOf(String.valueOf(user_id));
                    if (index < j) {
                        j = j - 2;
                    } else j = j - 1;

                    redis.deleteOldEntries(user_id);
                    redis.logPageVisit(getTs() + 100 * i, user_id);
                    usersList = redis.output();
                }
                i++;
            }
            out.println("===============================");
            Thread.sleep(SLEEP);
        }
    }
}
