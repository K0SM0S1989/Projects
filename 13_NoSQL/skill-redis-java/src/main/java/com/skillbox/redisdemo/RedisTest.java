package com.skillbox.redisdemo;


import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis



    //  20 зарегистрированных пользователей
    private static final int USERS = 20;

    // Задержка при повторе бесконечного цикла
    private static final int SLEEP = 1000; // 1 секунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(int UsersOnline) {
        String log = String.format("[%s] На главной странице показываем пользователя: %d", DF.format(new Date()), UsersOnline);
        out.println(log);
    }
    private static void logVIP(int UsersOnline) {
        String log = String.format("Пользователь %d оплатил платную услугу", UsersOnline);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {
        RedisStorage redis = new RedisStorage();
        redis.init();
        int i = 1;

        for (int id = 1; id <= USERS; id++) {
            redis.logPageVisit(id);
        }
        List<String> usersList = redis.output();

        while (true) {
            for (int j = 0; j < USERS; j++) {
                if (i % 10 != 0) {
                    log(Integer.parseInt(usersList.get(j)));
                } else {
                    int user_id = new Random().nextInt(USERS) + 1;
                    logVIP(user_id);
                    log(user_id);
                    log(Integer.parseInt(usersList.get(j)));
                    redis.deleteOldEntries(user_id);
                    redis.logPageVisit(user_id);
                    usersList = redis.output();
                }
                i++;
            }

            Thread.sleep(SLEEP);

        }




    }


}
