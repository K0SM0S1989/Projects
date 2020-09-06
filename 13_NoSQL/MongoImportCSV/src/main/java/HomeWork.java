import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;

/**
 * класс для подключения к базе,
 * парсинга файла csv,
 * оформления методов по домашнему заданию и вывод на печать результатов
 */
public class HomeWork {

    private final String filePath;
    private MongoCollection<Document> collection;

    public HomeWork(String filePath) {
        this.filePath = filePath;
    }
    /**
     * метод для подключения к базе
     */

    void init() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        collection = database.getCollection("StudentList");

        // Удалим из нее все документы
        collection.drop();
    }

    void removeAll(){
        collection.drop();
    }


    /**
     * метод для парсинга .csv файла
     * возвращает список объектов Student
     * с данными -- Имя, возраст, список курсов
     *
     * @return
     */
    List<Student> parseStudentMongoCsv(String filePath) {
        List<Student> students = new ArrayList<>();
        try {
            List<String> list = Files.readAllLines(Paths.get(filePath));
            students = list.stream().map(s -> s.split(","))
                    .map(strings -> {
                        List<String> stringList = new ArrayList<>();
                        for (String s : strings) {
                            String string = s.replace('\"', ' ').trim();
                            stringList.add(string);
                        }
                        return stringList;
                    }).map(studentsString -> {
                        List<String> courses = new ArrayList<>();
                        for (int i = 2; i < studentsString.size(); i++) {
                            courses.add(studentsString.get(i));
                        }
                        return new Student(studentsString.get(0), Integer.parseInt(studentsString.get(1)), courses);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * добавление данных из файла csv в базу MongoDB
     * вывод количества добавленных студентов из файла csv
     *
     * @return
     */
    long addDataToMongoDB() {
        List<Student> studentList = parseStudentMongoCsv(filePath);
        studentList.forEach(student -> {
            Document document = new Document()
                    .append("Name", student.getName())
                    .append("Age", student.getAge())
                    .append("Courses", student.getCourses());
            collection.insertOne(document);
        });
        return collection.countDocuments();
    }

    /**
     * метод для поиска и подсчета количества студентов старше 40 лет
     *
     * @return
     */

    int studentsOld40YearsCount() {
        AtomicInteger i = new AtomicInteger();
        List<Document> docList = new ArrayList<>();
        collection.find(gt("Age", 40)).forEach((Consumer<Document>) doc -> {
            docList.add(doc);
            i.set(docList.size());
        });
        return i.get();
    }

    /**
     * Метод для поиска самого молодого студента и вывода его имени
     *
     * @return
     */

    String youngStudentName() {
        AtomicReference<String> name = new AtomicReference<>();
        collection.find().sort(ascending("Age")).limit(1).forEach((Consumer<Document>) doc -> {
            name.set(String.valueOf(doc.get("Name")));
        });
        return name.get();
    }

    /**
     * Метод для поиска самого старого студента и вывода списка его курсов
     *
     * @return
     */

    List<String> oldStudentCourses() {
        List<String> courses = new ArrayList<>();
        collection.find().sort(descending("Age"))
                .limit(1).forEach((Consumer<Document>) doc -> courses
                .addAll((Collection<? extends String>) doc.get("Courses")));
        return courses;
    }
}
