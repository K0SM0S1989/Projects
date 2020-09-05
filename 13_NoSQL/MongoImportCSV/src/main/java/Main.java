
public class Main {

    public static void main(String[] args) {
        //docker run --rm --name skill-mongo -p 127.0.0.1:27017:27017/tcp -d scalar4eg/skill-mongo-with-hacker
        String filePath = "src/main/resources/mongo.csv";
        HomeWork homeWork = new HomeWork(filePath);
        homeWork.go();
    }

}
