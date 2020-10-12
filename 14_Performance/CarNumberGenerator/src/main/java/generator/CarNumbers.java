package generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.RecursiveAction;


public class CarNumbers extends RecursiveAction {
    int fileNumbers;
    int from;
    int to;

    public CarNumbers(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public CarNumbers(int from, int to, int fileNumbers) {
        this.from = from;
        this.to = to;
        this.fileNumbers = fileNumbers;
    }


    @Override
    protected void compute() {
        if ((to - from) <= 51) {
            createCarNumbers();
        } else {
            int mid = (to + from) >>> 1;
            CarNumbers task1 = new CarNumbers(from, mid, from);
            CarNumbers task2 = new CarNumbers(mid + 1, to, mid);
            invokeAll(task1, task2);
        }

    }

    void createCarNumbers() {
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        try (FileChannel fileChannel = new FileOutputStream("res/numbers_" + fileNumbers + ".txt").getChannel()) {
            for (int i = from; i <= to; i++) {
                StringBuffer numberBuilder = new StringBuffer();
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
                    int bytes = fileChannel.write(byteBuffer);
                    if (bytes <= 0)
                        break;
                }
                fileChannel.force(true);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    StringBuffer padNumber(int number, int numberLength, StringBuffer numberBuilder) {
        numberBuilder.delete(0, numberBuilder.length());
        numberBuilder.append(number);
        int padSize = numberLength - numberBuilder.length();
        for (int i = 0; i < padSize; i++) {
            numberBuilder.insert(0, 0);
        }
        return numberBuilder;
    }
}
