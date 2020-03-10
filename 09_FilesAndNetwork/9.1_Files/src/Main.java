import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

       try {
           Stream<Path> pathStream = Files.walk(Paths.get("C:\\Users\\Kosmos\\Desktop\\new"));
           int kilo = 1024;
           long sum = pathStream.filter(Files::isRegularFile).map(Path::toFile).map(File::length).mapToLong(Long::longValue).sum();
           if (sum >= kilo && sum <=Math.pow(kilo,2)){
               System.out.println( (int)(sum/kilo) + " кб");
           }else if (sum > Math.pow(kilo,2) && sum <=Math.pow(kilo,3)){
               System.out.println(String.format("%.1f",sum/Math.pow(kilo,2))  + " Мб");
           }else if (sum > Math.pow(kilo,3) && sum <=Math.pow(kilo,4)){
               System.out.println( String.format("%.1f",sum/Math.pow(kilo,3))  + " Гб");
           }
           else System.out.println(sum+" байт");

       }catch (Exception e) {
           e.printStackTrace();
       }

    }
}
