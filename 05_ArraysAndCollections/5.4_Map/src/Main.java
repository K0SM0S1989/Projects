import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    static String phoneNumber = "[0-9]+";
    static String name = "[А-Яа-я]+";
    static TreeMap<String, String> phoneBook;

    public static void main(String[] args) {
        phoneBook = new TreeMap<String, String>();

        Scanner scanner = new Scanner(System.in);
        for (;;){
            String phoneBookItem = scanner.nextLine().trim();
            String [] wordsOfInput = phoneBookItem.split("\\s+");
            if (wordsOfInput.length > 1){
                if (isValidName(wordsOfInput[0]) && isValidPhoneNumber(wordsOfInput[1])){
                    phoneBook.put(wordsOfInput[1], wordsOfInput[0]);
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
            System.out.println(map.get(key)+ " - "+ key);
        }
    }

    private static boolean isValidPhoneNumber(String word){
        boolean check = false;
        if (word.matches(phoneNumber)){
            check = true;
        }
        return check;
    }

    private static boolean isValidName(String word){
        boolean check = false;
        if (word.matches(name)){
            check = true;
        }
        return check;
    }

    private  static void checkKeyValue (String word, Map<String,String> map){
        boolean check = false;
        for (String key : map.keySet()){
            if (word.matches(key) || word.matches(map.get(key))){
                if (isValidPhoneNumber(word)){
                    System.out.println("Такой номер есть в списке:");
                    System.out.println(map.get(key)+ " - "+ key);
                }
                if (isValidName(word)){
                    System.out.println("Такое имя есть в списке:");
                    System.out.println(map.get(key)+ " - "+ key);
                }

                check = true;
                break;
            }

        }
        if (!check){
            if (isValidPhoneNumber(word)){
                System.out.println("Совпадений нет! \nДобавьте Имя");
                addData(word, map);
            }
            if (isValidName(word)){
                System.out.println("Совпадений нет! \nДобавьте номер");
                addData(word, map);
            }

        }

    }
    private static void addData(String word, Map<String,String> map){
        Scanner scanner = new Scanner(System.in);
        String newData = scanner.nextLine().trim();
        if (isValidPhoneNumber(word)){
            map.put(word, newData);
        }else if (isValidName(word)){
            map.put(newData, word);
        }
    }

}
