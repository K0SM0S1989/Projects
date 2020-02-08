
public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        String vasyaString = text.substring(text.indexOf('л')+1).trim();
        String vasyaSalary = vasyaString.substring(0, vasyaString.indexOf(' '));

        String petyaString = text.substring(text.indexOf('-')+1).trim();
        String petyaSalary = petyaString.substring(0, petyaString.indexOf(' '));

        String mashaString = text.substring(text.lastIndexOf('-')+1).trim();
        String mashaSalary = mashaString.substring(0, mashaString.indexOf(' '));

        int sumSalaryAllFriends = Integer.parseInt(vasyaSalary)+Integer.parseInt(petyaSalary)+Integer.parseInt(mashaSalary);
        int sumSalryVasyaMasha = Integer.parseInt(vasyaSalary)+Integer.parseInt(mashaSalary);

        System.out.println("Сумма заработка всех друзей = "+sumSalaryAllFriends);
        System.out.println("Сумма заработка Васи и Маши = "+sumSalryVasyaMasha);

    }
}