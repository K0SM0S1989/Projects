import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {


    public static void main(String[] args) {

        Path txtFile = Paths.get("map.txt");
        List<String> nameChildPage = RootPage.go();
        String adress = "https://lenta.ru";

        String table = "\t";
        //System.out.println(adress);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(adress);
        nameChildPage.forEach(child -> {
            strings.add(new ForkJoinPool().invoke(new PageJoinSaveFiles(child, nameChildPage, table)));
//            System.out.println(new ForkJoinPool().invoke(new PageJoinSaveFiles(child, nameChildPage, table)));
        });
        try {
            Files.write(txtFile, strings);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}



