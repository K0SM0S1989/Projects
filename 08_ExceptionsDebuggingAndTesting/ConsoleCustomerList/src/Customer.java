public class Customer
{
    private String name;
    private String phone;
    private String eMail;

    private  String nameCorrect = "[А-Я][а-я]+\\s[А-Я][а-я]+";
    private  String phoneCorrect = "[+][7][9][0-9]{9}";
    private  String eMailCorrect = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

    public Customer(String name, String phone, String eMail)
    {

        if (!name.matches(nameCorrect)||!eMail.matches(eMailCorrect)||!phone.matches(phoneCorrect)){
            throw new IllegalArgumentException("Wrong format name or eMail or phone. Example correct format:\n" +
                    "Василий Петров " + "vasily.petrov@gmail.com +79215637722");
        }else {
            this.name = name;
            this.phone = phone;
            this.eMail = eMail;
        }

    }

    public String toString()
    {
        return name + " - " + eMail + " - " + phone;
    }
}
