import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.RecursiveAction;

public class CarNumbers extends RecursiveAction {
    int regionCode;
    final char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    public CarNumbers(int regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    protected void compute() {
        if (regionCode < 100) {
            CarNumbers task = new CarNumbers(createCarNumbers());
            invokeAll(task);
        }
    }

    int createCarNumbers() {
        StringBuffer numberBuilder = new StringBuffer();
        int i = regionCode;
        StringBuffer builder = new StringBuffer();
        try (PrintWriter writer = new PrintWriter("res/numbers" + i + ".txt")) {
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
            writer.write(builder.toString());
            i++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return i;
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
