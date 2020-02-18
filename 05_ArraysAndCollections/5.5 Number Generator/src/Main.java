import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i<=2500000; i++){//заполнение списка 2500000 номерами
            list.add(getAutoNumber());
        }

        HashSet<String> hSet = new HashSet<String>();
        hSet.addAll(list);

        TreeSet<String> tSet = new TreeSet<String>();
        tSet.addAll(list);

        Scanner scanner = new Scanner(System.in);
        String inputData;

        while (true) {
            inputData = scanner.nextLine();

            long startSearchCon = System.nanoTime();
            if (list.contains(inputData)) {
                long finishYesCon = System.nanoTime();
                System.out.println("Поиск перебором: номер найден, поиск занял " + (finishYesCon - startSearchCon) + " нс.");

            } else {
                long finishNoCon = System.nanoTime();
                System.out.println("Поиск перебором: номер не найден, поиск занял " + (finishNoCon - startSearchCon) + " нс.");
            }

            Collections.sort(list);

            long startSearchBin = System.nanoTime();
            if (Collections.binarySearch(list, inputData) >= 0) {
                long finishYesBin = System.nanoTime();
                System.out.println("Бинарный поиск: номер найден, поиск занял " + (finishYesBin - startSearchBin) + " нс.");

            } else {
                long finishNoBin = System.nanoTime();
                System.out.println("Бинарный поиск: номер не найден, поиск занял " + (finishNoBin - startSearchCon) + " нс.");
            }

            long startSearchTSet = System.nanoTime();
            if (tSet.contains(inputData)) {
                long finishYesTSet = System.nanoTime();
                System.out.println("Поиск в TreeSet: номер найден, поиск занял " + (finishYesTSet - startSearchTSet) + " нс.");
            } else {
                long finishNoTSet = System.nanoTime();
                System.out.println("Поиск в TreeSet: номер не найден, поиск занял " + (finishNoTSet - startSearchTSet) + " нс.");
            }

            long startSearchHSet = System.nanoTime();
            if (hSet.contains(inputData)) {
                long finishYesHSet = System.nanoTime();
                System.out.println("Поиск в HashSet: номер найден, поиск занял " + (finishYesHSet - startSearchTSet) + " нс.");
            } else {
                long finishNoHSet = System.nanoTime();
                System.out.println("Поиск в HashSet: номер не найден, поиск занял " + (finishNoHSet - startSearchHSet) + " нс.");
            }


        }


    }

    private static String getAutoNumber(){
        String [] letters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        String [] numbers = {"111", "222","333","444","555","666","777","888","999"};


        int firstLetterIndex = (int)(Math.random()*12);
        int  secondLetterIndex = (int)(Math.random()*12);
        int  thirdLetterIndex =(int) (Math.random()*12);
        int numberIndex = (int)(Math.random()*9);
        String region = String.format("%02d", (int) (Math.random()*199+1));

        String autoNumber = letters[firstLetterIndex]+numbers[numberIndex]+letters[secondLetterIndex]+letters[thirdLetterIndex]+region;
        return autoNumber;
    }
}
