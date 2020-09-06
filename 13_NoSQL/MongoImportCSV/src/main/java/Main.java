
public class Main {

    public static void main(String[] args) {
        //docker run --rm --name skill-mongo -p 127.0.0.1:27017:27017/tcp -d scalar4eg/skill-mongo-with-hacker
        String filePath = "src/main/resources/mongo.csv";
        HomeWork homeWork = new HomeWork(filePath);
        homeWork.init();

        System.out.println("Общее количество студентов - " + homeWork.addDataToMongoDB());

        System.out.println("Количество студентов старше 40 лет - " + homeWork.studentsOld40YearsCount());

        System.out.println("Имя самого молодого студента - " + homeWork.youngStudentName());

        System.out.println("Список курсов самого старого студента - " + homeWork.oldStudentCourses());


    }

}
