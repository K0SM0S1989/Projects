
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;
    private double feedAmount=0;
    private boolean liveStatus;
    private static final int NUMBER_OF_EYES = 2;
    private static final double MINIMUM_WEIGHT = 1000.0;
    private static final double MAXIMUM_WEIGHT = 9000.0;
    private CatColor catColor;
    private String name;
    private static int count;



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCatColor(CatColor catColor) {
        this.catColor = catColor;
    }

    public CatColor getCatColor() {
        return catColor;
    }




    public double getFeedAmount() {
        return feedAmount;
    }



    public boolean isLiveStatus(){
        if (getWeight()<maxWeight && getWeight()>minWeight){

            liveStatus = true;
        }else {

            liveStatus = false;
        }
        return liveStatus;
    }
    public Cat()
    {
        count = getCount()+1;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
    }
    public Cat(double weight)
    {

        this();
        this.weight = weight;
        originWeight = weight;
    }

    public void meow(){
            if (isLiveStatus()){
                weight = weight - 1;
                if (!isLiveStatus()){
                    count--;
                }
            } if (!isLiveStatus()){
               // Cat.count = count-1;
                System.out.println("Кошка мертва и не может мяукнуть");
            }


    }

    public void feed(Double amount){
        if (isLiveStatus()){
            feedAmount +=amount;
            weight = weight + amount;
            if (!isLiveStatus()){
                count--;
            }
        } if (!isLiveStatus()){
           // Cat.count = count-1;
        System.out.println(" нельзя покормить, так как кошка мертва");}

    }

    public void drink(Double amount)
    {
        if (isLiveStatus()){
            weight = weight + amount;
            if (!isLiveStatus()){
                count--;
            }

        } if (!isLiveStatus()){
           // Cat.count = count-1;
            System.out.println(" нельзя напоить, так как кошка мертва");
    }

    }
    public void pee(){

        if (isLiveStatus()){weight = weight - 150;
            System.out.println("Pee pee ka ka");
            if (!isLiveStatus()){
                count--;
            }

        } if (!isLiveStatus()){

            System.out.println(" нельзя отправить в туалет, так как кошка мертва");
        }

    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {

            return "Dead";
        }
        else if(weight > maxWeight) {

            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
    /**
     * метод для копирования
     */
    public Cat copyCat(){
        Cat nameCat = new Cat();
        nameCat.weight = weight;
        nameCat.setName(name);
        nameCat.setCatColor(catColor);
        return nameCat;
    }
    public static int getCount() {
        return count;
    }
}