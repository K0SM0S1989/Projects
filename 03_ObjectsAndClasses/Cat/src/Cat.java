
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


    public boolean isLiveStatus(){
        if (getWeight()<maxWeight && getWeight()>minWeight){
            liveStatus = true;
        }else liveStatus = false;
        return liveStatus;
    }
    private static int count = 0;

    public double getFeedAmount() {
        return feedAmount;
    }
    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Cat.count = count;
    }

    public Cat()
    {
        count++;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;

    }
    public Cat(double weight)
    {

        count++;
        this.weight = weight;
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;

    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        if (!isLiveStatus()){
            System.out.println(" нельзя покормить, так как кошка мертва");

        } else
            feedAmount = feedAmount+amount;
        weight = weight + amount;
    }

    public void drink(Double amount)
    {
        if (!isLiveStatus()){
            System.out.println(" нельзя напоить, так как кошка мертва");

        } else
            weight = weight + amount;
    }
    public void pee(){
        if (!isLiveStatus()){System.out.println(" нельзя отправить в туалет, так как кошка мертва");
        } else {weight = weight - 150;
            System.out.println("Pee pee ka ka");
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
}