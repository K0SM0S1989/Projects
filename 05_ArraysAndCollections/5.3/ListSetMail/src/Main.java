import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private  static  TreeSet<String> eMailList;
    public static void main(String[] args) {

        eMailList = new TreeSet<>();
        System.out.println("Список команд: LIST, ADD, STOP");

        boolean check = true;
        while (check == true) {
            System.out.println("Введите команду:");
            Scanner scanner = new Scanner(System.in);
            String eMailAndCommand = scanner.nextLine();
            String[] words = eMailAndCommand.split("\\s+");
            String command = words[0];
            String listCommand = "LIST";
            String addCommand = "ADD";
            String stopCommand = "STOP";

            if (words.length > 1 && isValidEmail(words[1])) {

                if (command.matches(addCommand)) {
                    if (isCheckEMail(words[1])){
                        System.out.println("Адрес уже есть в списке");
                    } else addMethod(words[1]);
                } else {
                    System.out.println("Вы ввели неверную команду!\nПопробуйте снова:");
                }
            } else if (words.length > 1 && !isValidEmail(words[1])) {
                System.out.println("Неправильно введена почта");
            } else if (words.length == 1) {
                if (command.matches(listCommand)) {
                    listMethod();
                } else if (command.matches(stopCommand)) {
                    check = false;
                } else {
                    System.out.println("Вы ввели неверную команду!\nПопробуйте снова:");
                }
            }
        }
    }

    public static void addMethod(String eMail){
        eMailList.add(eMail);
        System.out.println("Адрес добавлен");
    }

    public static void listMethod(){
        if (eMailList.isEmpty()){
            System.out.println("Список пуст");
        }else {
            for (String word : eMailList){
                System.out.println(word);
            }
        }
    }
    public static boolean isValidEmail(String email){
        Pattern patternEmail = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");
        Matcher matcher = patternEmail.matcher(email);
        return matcher.matches();
    }
    public static boolean isCheckEMail(String email){
        boolean check = false;
        for (String word : eMailList){
            check = word.equals(email);
          if (check == true){
              break;
          }

        }
        return check;
    }
}
