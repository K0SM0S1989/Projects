
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String srcFolder = args[0];
        String dstFolder = args[1];

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int processors = Runtime.getRuntime().availableProcessors();
        assert files != null;
        int delta = (int) Math.ceil(files.length / processors);
        Thread[] newThread = new Thread[processors];

        File[][] filesForNewThread = new File[processors][delta];

        for (int i = 0; i < processors; i++) {

            if (i == (processors - 1)) {
                filesForNewThread[i] = new File[delta + (files.length % processors)];
            }

            System.arraycopy(files, i * (delta), filesForNewThread[i], 0, filesForNewThread[i].length);
            newThread[i] = new ImageResizer(dstFolder, filesForNewThread[i]);
            newThread[i].start();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }


}
