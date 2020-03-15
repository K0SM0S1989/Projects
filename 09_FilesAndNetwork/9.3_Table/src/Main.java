import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static String string = "[А-Яа-я]+";
    private static String filePath = "data/movementList.csv";

    public static void main(String[] args) throws IOException {

        List<BankProduct> products = parseProductCsv();
        System.out.println("Общий доход - " + products.stream().filter(s -> !s.getIncome().matches(string))
                .mapToDouble(a -> Double.valueOf(a.getIncome())).sum());

        System.out.println("Общий расход - " + products.stream().filter(s -> !s.getConsumption().matches(string))
                .mapToDouble(a -> Double.valueOf(a.getConsumption())).sum());

        products.stream().filter(s -> s.getConsumption().matches(string) || Double.valueOf(s.getConsumption()) != 0)
                .map(s -> s.getOperationDescription() + " --- " + s.getConsumption()).forEach(System.out::println);
    }

    private static List<BankProduct> parseProductCsv() throws IOException {
        List<BankProduct> products = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<>();
            for (int i = 0; i < splitedText.length; i++) {
                if (isColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            BankProduct product = new BankProduct();
            product.setTypeOfAccount(columnList.get(0));
            product.setNumberOfAccount(columnList.get(1));
            product.setCurrency(columnList.get(2));
            product.setDateOfOperation(columnList.get(3));
            product.setReference(columnList.get(4));
            product.setOperationDescription(columnList.get(5));
            product.setIncome(columnList.get(6));
            product.setConsumption(columnList.get(7));

            products.add(product);

        }
        return products;
    }

    private static boolean isColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

}
