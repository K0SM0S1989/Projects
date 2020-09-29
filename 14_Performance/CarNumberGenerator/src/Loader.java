import java.util.concurrent.ForkJoinPool;

public class Loader {

    public static void main(String[] args){
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new CarNumbers(1));
        pool.shutdown();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

}
