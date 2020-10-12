package generator;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ForkJoinPool;


public class Loader {
    private static final int allRegionCodes = 100;


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        multiThreadingGenerator();
        singleThreadingGenerator();


        System.out.println(System.currentTimeMillis() - start + " ms");

    }

    public static void multiThreadingGenerator() {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new CarNumbers(1, allRegionCodes));
        pool.shutdownNow();
    }


    public static void singleThreadingGenerator() {
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        try (FileChannel channel = new FileOutputStream("res/numbers.txt").getChannel()) {
            for (int i = 1; i <= allRegionCodes; i++) {
                StringBuilder numberBuilder = new StringBuilder();
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter)
                                        .append(padNumber(number, 3, numberBuilder))
                                        .append(secondLetter)
                                        .append(thirdLetter)
                                        .append(padNumber(i, 2, numberBuilder))
                                        .append('\n');
                            }
                        }
                    }
                }
                ByteBuffer byteBuffer = ByteBuffer.wrap(builder.toString().getBytes());
                while (byteBuffer.hasRemaining()) {
                    int bytes = channel.write(byteBuffer);
                    if (bytes <= 0)
                        break;
                }

            }
            channel.force(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder padNumber(int number, int numberLength, StringBuilder numberBuilder) {
        numberBuilder.delete(0, numberBuilder.length());
        numberBuilder.append(number);
        int padSize = numberLength - numberBuilder.length();
        for (int i = 0; i < padSize; i++) {
            numberBuilder.insert(0, 0);
        }
        return numberBuilder;
    }
}
