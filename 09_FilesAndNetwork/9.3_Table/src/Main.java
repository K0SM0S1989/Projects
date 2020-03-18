import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    private static List<BankProduct> parseProductCsv() {
        List<BankProduct> products = new ArrayList<>();
        try {
            List<String> list = Files.readAllLines(Paths.get(filePath));
            products = list.stream().map(s -> s.split(","))
                    .map(strings -> {
                        ArrayList<String> columnList = new ArrayList<>();
                        for (String text : strings) {
                            if (isColumnPart(text)) {
                                String lastText = columnList.get(columnList.size() - 1);
                                columnList.set(columnList.size() - 1, lastText + "," + text);
                            } else columnList.add(text);
                        }
                        return columnList;
                    }).filter(strings -> strings.size() == 8).map(strings -> new BankProduct()
                            .setTypeOfAccount(strings.get(0))
                            .setNumberOfAccount(strings.get(1))
                            .setCurrency(strings.get(2))
                            .setDateOfOperation(strings.get(3))
                            .setReference(strings.get(4))
                            .setOperationDescription(strings.get(5))
                            .setIncome(strings.get(6))
                            .setConsumption(strings.get(7))
                    ).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    private static boolean isColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

}
