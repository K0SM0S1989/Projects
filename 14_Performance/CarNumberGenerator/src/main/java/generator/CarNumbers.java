package generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;


public class CarNumbers extends RecursiveAction {
    int from;
    int to;

    public CarNumbers(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected void compute() {
        if ((to - from) == 1) {
                createCarNumbers();
        } else {
            List<CarNumbers> taskList = new ArrayList<>();
            for (int k = from; k < to; k++) {
                CarNumbers task = new CarNumbers(k, (k + 1));
                taskList.add((CarNumbers) task.fork());
            }
            taskList.forEach(ForkJoinTask::join);
        }

    }

    void createCarNumbers() {
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        StringBuffer numberBuilder = new StringBuffer();
        int i = from;
        StringBuffer builder = new StringBuffer();
        try (FileChannel fileChannel = new FileOutputStream("res/numbers_" + from + ".txt").getChannel()) {
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
