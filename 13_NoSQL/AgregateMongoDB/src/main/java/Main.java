import java.util.Scanner;

public class Main {
        //docker run --rm --name skill-mongo -p 127.0.0.1:27017:27017/tcp -d scalar4eg/skill-mongo-with-hacker

    private static final String addShop = "ДОБАВИТЬ_МАГАЗИН";
    private static final String addProduct = "ДОБАВИТЬ_ТОВАР";
    private static final String displayProduct = "ВЫСТАВИТЬ_ТОВАР";
    private static final String productStatistics = "СТАТИСТИКА_ТОВАРОВ";



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        System.out.println("Здравствуйте! Это программа для управления товарами в магазине");
        System.out.println("Чтобы добавить магазин введите команду " + addShop + " и название магазина.\n" +
                "Например: " + addShop + " Девяточка");
        System.out.println("Чтобы добавить товар введите команду " + addProduct + ", название товара и его цену.\n" +
                "Например: " + addProduct + " Вафли 54");
        System.out.println("Чтобы выставить товар в магазине введите команду " + displayProduct + " затем название товара и магазина.\n" +
                "Например: " + displayProduct + " Вафли Девяточка");
        System.out.println("Чтобы получить информацию о товарах во всех магазинах введите команду " + productStatistics);


        while (true) {
            String command = scanner.nextLine().trim();
            commandExecutor.execute(command);
        }
    }
}
