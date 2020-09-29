import java.util.concurrent.ForkJoinPool;


public class Loader {


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        //FileOutputStream writer = new FileOutputStream("res/numbers.txt");
        //PrintWriter writer = new PrintWriter("res/numbers.txt");


        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new CarNumbers(1));
        pool.shutdown();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }


}
