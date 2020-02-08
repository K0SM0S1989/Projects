
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        String digits = text.replaceAll("[^0-9]"," ").trim();
        String [] words = digits.split("\\s+");
        int sumSalaryAllFriends=0;
        for (int i=0;i<words.length;i++){
            System.out.println(words[i]);
            sumSalaryAllFriends = Integer.parseInt(words[i])+sumSalaryAllFriends;
        }
        System.out.println("Сумма заработных плат ысех друзей = "+sumSalaryAllFriends);
    }
}