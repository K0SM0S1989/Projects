import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static ArrayList<String> toDoList;
    public static void main(String[] args) {
       toDoList = new ArrayList<>();
       boolean check = true;
        System.out.println("Список команд: LIST, ADD, EDIT, DELETE, STOP");

        while (check == true){
            System.out.println("Введите команду:");
            Scanner scanner = new Scanner(System.in);
            String toDo = scanner.nextLine();

            String [] words = toDo.split("\\s+");
            String word = words[0];
            Pattern patternCommand = Pattern.compile("[A-Z]+");

            if (!words[0].matches(String.valueOf(patternCommand))){
                System.out.println("Неправильная команда!\nКоманды начинаются на LIST, ADD, EDIT, DELETE, STOP");
            }
            Pattern patternIndex = Pattern.compile("[0-9]");
            if (words.length > 1 && words[1].matches(String.valueOf(patternIndex))){

                int indexToDo = Integer.parseInt(words[1]);
                switch (word){
                    case "LIST":
                        listMethod(indexToDo);
                        break;
                    case "ADD":
                        addMethod(indexToDo,toDo);
                        break;
                    case "EDIT":
                        editMethod(indexToDo,toDo);
                        break;
                    case "DELETE":
                        deleteMethod(indexToDo);
                        break;
                }
            }else {
                switch (word){
                    case "LIST":
                        listMethod();
                        break;
                    case "ADD":
                        addMethod(toDo);
                        break;
                    case "DELETE":
                        deleteMethod();
                        break;
                    case "STOP":
                     check = false;
                        break;
                }
            }
        }

    }

    public static void addMethod(int pos, String delo){

        if (pos >= toDoList.size()){
            toDoList.add(withoutFirstSecondWords(2, delo));
        }else toDoList.add(pos,withoutFirstSecondWords(2,delo));
        System.out.println("Дело добавлено в список");
    }
    public static void listMethod(int pos){
        if (toDoList.isEmpty()){
            System.out.println("Список пуст");
        }else {
            System.out.println(pos + " - " + toDoList.get(pos));
        }
    }
    public static void editMethod(int pos, String delo){


        toDoList.remove(pos);
        toDoList.add(pos, withoutFirstSecondWords(2, delo));
        System.out.println("Дело под номером - " +pos+ " изменено");
    }
    public static void deleteMethod(int pos){
        toDoList.remove(pos);
        System.out.println("Дело под номером - " +pos+ " удалено");
    }

    public static void addMethod(String delo){

        toDoList.add(withoutFirstSecondWords(1,delo));
        System.out.println("Дело добавлено в список");
    }
    public static void listMethod(){
        if (toDoList.isEmpty()){
            System.out.println("Список пуст");
        }else {

            for (int i = 0; i < toDoList.size(); i++){
                System.out.println(i + " - " + toDoList.get(i));
            }
        }
    }
    public static void deleteMethod(){
        toDoList.removeAll(toDoList);
        System.out.println("Список очищен полностью");
    }
    public static String withoutFirstSecondWords(int index, String delo){
        String withoutFirstSecondWord = "";
        String[] toDoWords = delo.split("\\s+");
        for (int i = index; i < toDoWords.length; i++){
            withoutFirstSecondWord = withoutFirstSecondWord + " "+toDoWords[i];
        }
        withoutFirstSecondWord = withoutFirstSecondWord.trim();
        return withoutFirstSecondWord;
    }
}
