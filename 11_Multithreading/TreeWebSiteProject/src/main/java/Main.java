import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import java.util.concurrent.ConcurrentSkipListSet;

import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final String adress = "https://lenta.ru";
    private static final String tagsPageNotFound = "/tags";
    private static final String partsPageNotFound = "/parts";

    public static void main(String[] args) {

        Path txtFile = Paths.get("map.txt");
        ConcurrentSkipListSet<String> result = new ConcurrentSkipListSet();
        ConcurrentSkipListSet<String> treeResult = new ConcurrentSkipListSet();
        treeResult.add(adress);
        new ForkJoinPool().invoke(new PageJoinSaveFiles(adress, adress, result, "", treeResult, tagsPageNotFound, partsPageNotFound));
        ArrayList<String> treeList = new ArrayList<>(treeResult);
        Collections.reverse(treeList);
        try {
            Files.write(txtFile, treeList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
