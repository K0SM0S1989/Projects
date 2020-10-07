package bench;


import generator.Loader;
import org.openjdk.jmh.annotations.*;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 2)
@Fork(2)
@Measurement(iterations = 4)
public class TestBM {

    @Benchmark
    public void singleThreadingGenerator() {
        Loader.singleThreadingGenerator();
    }

    @Benchmark
    public void multiThreadingGeneratorBench() {
        Loader.multiThreadingGenerator();

    }




}
