import java.util.Scanner;

public class Main {
        //docker run --rm --name skill-mongo -p 127.0.0.1:27017:27017/tcp -d scalar4eg/skill-mongo-with-hacker

    private static final String addShop = "ДОБАВИТЬ_МАГАЗИН";
    private static final String addProduct = "ДОБАВИТЬ_ТОВАР";
    private static final String displayProduct = "ВЫСТАВИТЬ_ТОВАР";
    private static final String productStatistics = "СТАТИСТИКА_ТОВАРОВ";
    private static final String exit = "ВЫХОД";
    private static boolean isExit = true;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Application application = new Application();
        application.init();
        System.out.println("Здравствуйте! Это программа для управления товарами в магазине");
        System.out.println("Чтобы добавить магазин введите команду " + addShop + " и название магазина.\n" +
                "Например: " + addShop + " Девяточка");
        System.out.println("Чтобы добавить товар введите команду " + addProduct + ", название товара и его цену.\n" +
                "Например: " + addProduct + " Вафли 54");
        System.out.println("Чтобы выставить товар в магазине введите команду " + displayProduct + " затем название товара и магазина.\n" +
                "Например: " + displayProduct + " Вафли Девяточка");
        System.out.println("Чтобы получить информацию о товарах во всех магазинах введите команду " + productStatistics);
        System.out.println("Для выхода введите команду " + exit);

        while (isExit) {
            String command = scanner.nextLine().trim();
            String[] commands = command.split(" ");
            switch (commands[0]) {
                case addShop:
                    application.addShop(command);
                    break;
                case addProduct:
                    application.addProduct(command);
                    break;
                case displayProduct:
                    application.displayProduct(command);
                    break;
                case productStatistics:
                    application.productStatistics();
                    break;
                case exit:
                    isExit = false;
                    break;
                default:
                    System.out.println("Неправильная команда, попробуйте снова");
                    break;

            }
        }

    }

}
