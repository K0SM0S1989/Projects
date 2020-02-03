
public class Loader
{
    public static void main(String[] args)
    {
        Cat murka = new Cat();
        Cat sonya = new Cat();
        Cat barsik = new Cat();
        Cat ryzhik = new Cat();
        Cat roy = new Cat();
        Cat kosoy = new Cat();

        String [] weightCats = {"Вес Мурки = ", "Вес Cони = ","Вес Барсика = ","Вес Рыжика = ","Вес Роя = ","Вес Косого = "};
        String [] catsNames = {"Мурка = ", "Cоня = ","Барсик = ","Рыжик = ","Рой = ","Косой = "};
        Cat[] cats = {murka,sonya,barsik,ryzhik,roy,kosoy};
        for (int i=0;i<6;i++){
            System.out.println(weightCats[i]+cats[i].getWeight());

        }
        murka.feed(1000.0);
        sonya.feed(1000.0);
        System.out.println("_______________");
        System.out.println(weightCats[0]+murka.getWeight());
        System.out.println(weightCats[1]+sonya.getWeight());
        System.out.println("_______________");

        for (int i =0; i<=10;i++){
            barsik.feed(1000.0);
            if (barsik.getStatus().equals("Exploded")){
                System.out.println(catsNames[2]+barsik.getStatus());
                System.out.println(weightCats[2]+barsik.getWeight());

                break;
            }
        }
        System.out.println("_______________");



        double localWeight = kosoy.getWeight();
        for (int i=0; i<=localWeight; i++){
            kosoy.meow();
            if (kosoy.getStatus().equals("Dead")){
                System.out.println(catsNames[5]+kosoy.getStatus());
                System.out.println(weightCats[5]+kosoy.getWeight());
                break;
            }
        }
    }
}