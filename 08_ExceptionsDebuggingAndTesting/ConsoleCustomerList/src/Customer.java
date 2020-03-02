
public class Customer {
    private String name;
    private String phone;
    private String eMail;


    public Customer(String name, String phone, String eMail) {


        this.name = name;
        this.phone = phone;
        this.eMail = eMail;


    }

    public String toString() {
        return name + " - " + eMail + " - " + phone;
    }

    public void validate() throws IncorrectNameException {
        String nameCorrect = "[А-Я][а-я]+\\s[А-Я][а-я]+";
        String phoneCorrect = "[+][7][9][0-9]{9}";
        String eMailCorrect = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

        if (!name.matches(nameCorrect)) {
            throw new IncorrectNameException("Please provide correct customer name");
        }

        if (!eMail.matches(eMailCorrect)) {
            throw new IncorrectEmailException("Please provide correct customer eMail");
        }

        if (!phone.matches(phoneCorrect)) {
            throw new IncorrectPhoneException("Please provide correct customer phone");
        }
    }
}
