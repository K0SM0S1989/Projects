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

/**
 * класс для подключения к базе,
 * парсинга файла csv,
 * оформления методов по домашнему заданию и вывод на печать результатов
 */
public class HomeWork {

//    private String host;
//    private Integer port;
    private String filePath;

    public HomeWork() {
    }

    public HomeWork(String filePath) {
        this.filePath = filePath;
    }

    public void init(){

    }

    /**
     * метод для подключения к базе
     * и выводу на печать ответов по домашнему заданию
     */

    public void go() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        MongoCollection<Document> collection = database.getCollection("StudentList");

        // Удалим из нее все документы
        collection.drop();

        System.out.println("Общее количество студентов - " + addDataToMongoDB(collection));

        System.out.println("Количество студентов старше 40 лет - " + studentsOld40YearsCount(collection));

        System.out.println("Имя самого молодого студента - " + youngStudentName(collection));

        System.out.println("Список курсов самого старого студента - " + oldStudentCourses(collection));

    }

    /**
     * метод для парсинга .csv файла
     * возвращает список объектов Student
     * с данными -- Имя, возраст, список курсов
     *
     * @return
     */
    public List<Student> parseStudentMongoCsv(String filePath) {
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
     * @param collection
     */

    public long addDataToMongoDB(MongoCollection<Document> collection) {
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
     * @param collection
     * @return
     */
    int studentsOld40YearsCount(MongoCollection<Document> collection) {
        AtomicInteger i = new AtomicInteger();
        List<Document> docList = new ArrayList<>();
        collection.find(new Document("Age", new Document("$gt", 40))).forEach((Consumer<Document>) doc -> {
            docList.add(doc);
            i.set(docList.size());
        });
        return i.get();
    }

    /**
     * Метод для поиска самого молодого студента и вывода его имени
     *
     * @param collection
     * @return
     */

    String youngStudentName(MongoCollection<Document> collection) {
        AtomicReference<String> name = new AtomicReference<>();
        collection.find().sort(new Document("Age", 1)).limit(1).forEach((Consumer<Document>) doc -> {
            name.set(String.valueOf(doc.get("Name")));
        });
        return name.get();
    }

    /**
     * Метод для поиска самого старого студента и вывода списка его курсов
     *
     * @param collection
     * @return
     */

    List<String> oldStudentCourses(MongoCollection<Document> collection) {
        List<String> courses = new ArrayList<>();
        collection.find().sort(new Document("Age", -1))
                .limit(1).forEach((Consumer<Document>) doc -> courses
                .addAll((Collection<? extends String>) doc.get("Courses")));
        return courses;
    }
}
