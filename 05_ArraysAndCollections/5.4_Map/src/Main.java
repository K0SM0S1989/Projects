import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

   private static final String PHONE_NUMBER = "[0-9]+";
   private static final String NAME = "[А-Яа-я]+";
    static TreeMap<String, String> phoneBook;

    public static void main(String[] args) {
        phoneBook = new TreeMap<String, String>();

        Scanner scanner = new Scanner(System.in);

        while (true){
            String phoneBookItem = scanner.nextLine().trim();
            String [] wordsOfInput = phoneBookItem.split("\\s+");
            if (wordsOfInput.length > 1){
                if (isValidName(wordsOfInput[0]) && isValidPhoneNumber(wordsOfInput[1])){
                    phoneBook.put(wordsOfInput[0], wordsOfInput[1]);
                } else {
                    System.out.println("Ошибка ввода! \nПопробуйте снова");
                }

            }else {
                if (phoneBookItem.equals("LIST")){
                    printMap(phoneBook);
                    continue;
                }
                if (isValidPhoneNumber(phoneBookItem)){
                    checkKeyValue(phoneBookItem,phoneBook);
                } else if (isValidName(phoneBookItem)){
                    checkKeyValue(phoneBookItem,phoneBook);
                }


            }
        }
    }
    private static void printMap(Map<String,String> map){
        for (String key : map.keySet()){
            System.out.println(key+ " - "+ map.get(key));
        }
    }

    private static boolean isValidPhoneNumber(String word){
        return word.matches(PHONE_NUMBER);
    }

    private static boolean isValidName(String word){
        return word.matches(NAME);
    }

    private  static void checkKeyValue (String word, Map<String,String> map){

        if (!map.containsKey(word) && !map.containsValue(word) ){
            if (isValidPhoneNumber(word)){
                System.out.println("Совпадений нет! \nДобавьте Имя");
                Scanner scanner = new Scanner(System.in);

                addData(word, map, scanner.nextLine().trim());
            }
            if (isValidName(word)){
                System.out.println("Совпадений нет! \nДобавьте номер");
                Scanner scanner = new Scanner(System.in);
                addData(word, map, scanner.nextLine().trim());
            }

        } else {System.out.println("Данные есть в списке:");
            for (Map.Entry<String,String> entry : map.entrySet()) {
                if (word.equals(entry.getValue())) {
                    System.out.println(entry.getKey() + " - "+word);
                }
            }
                if (map.containsKey(word)){
                        System.out.println(word + " - "+map.get(word));
                    }
            }

    }
    private static void addData(String word, Map<String,String> map, String newData){

        if (isValidPhoneNumber(word)){
            map.put(newData,word);
        }else if (isValidName(word)){
            map.put(word, newData);
        }
    }

}
