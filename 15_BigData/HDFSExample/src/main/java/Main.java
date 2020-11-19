import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;

public class Main {
  //  private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws Exception {

        String rootPath = "hdfs://e55449e48e0c:8020";
        String path  = "hdfs://e55449e48e0c:8020/test/file.txt";
        String pathDirectory  = "hdfs://e55449e48e0c:8020/test";

        FileAccess fileAccess = new FileAccess(rootPath);
        fileAccess.create(path);
        fileAccess.append(path, "Некий текст");
        System.out.println(fileAccess.read(path));
        fileAccess.delete(path);


        System.out.println(fileAccess.isDirectory(pathDirectory));
        System.out.println(fileAccess.list(pathDirectory));
        fileAccess.fileSystemClose();

// ==========================================================================================================

//        Configuration configuration = new Configuration();
//        configuration.set("dfs.client.use.datanode.hostname", "true");
//        System.setProperty("HADOOP_USER_NAME", "root");

//        FileSystem hdfs = FileSystem.get(
//                new URI("hdfs://5e19bb0d90d4:8020"), configuration
//        );
//        Path file = new Path("hdfs://5e19bb0d90d4:8020/test/file.txt");
//
//        if (hdfs.exists(file)) {
//            hdfs.delete(file, true);
//        }

//        OutputStream os = hdfs.create(file);
//        BufferedWriter br = new BufferedWriter(
//                new OutputStreamWriter(os, "UTF-8")
//        );

//        for (int i = 0; i < 10_000_000; i++) {
//            br.write(getRandomWord() + " ");
//        }

//        br.flush();
//        br.close();
//        hdfs.close();
    }

//    private static String getRandomWord() {
//        StringBuilder builder = new StringBuilder();
//        int length = 2 + (int) Math.round(10 * Math.random());
//        int symbolsCount = symbols.length();
//        for (int i = 0; i < length; i++) {
//            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
//        }
//        return builder.toString();
//    }
}
