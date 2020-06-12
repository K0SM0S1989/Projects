import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final String adress = "https://lenta.ru";

    public static void main(String[] args) {

        Path txtFile = Paths.get("map.txt");
        CopyOnWriteArrayList<String> result = new CopyOnWriteArrayList();
        CopyOnWriteArrayList<String> treeResult = new CopyOnWriteArrayList();
        treeResult.add(adress);
        new ForkJoinPool().invoke(new PageJoinSaveFiles(adress, adress, result, "", treeResult));
        System.out.println(result.size());
        try {
            Files.write(txtFile, treeResult);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}



