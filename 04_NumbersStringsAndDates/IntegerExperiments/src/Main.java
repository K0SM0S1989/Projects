public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        Main main = new Main();
        System.out.println(main.sumDigits(container.count));
      }

    public Integer sumDigits(Integer number)
    {
        //@TODO: write code here
         Integer sum=0;
        for (int i=0; i<Integer.toString(number).length();i++){
            sum = sum+Character.digit(Integer.toString(number).charAt(i),10);
        }
        return sum;
    }
}
