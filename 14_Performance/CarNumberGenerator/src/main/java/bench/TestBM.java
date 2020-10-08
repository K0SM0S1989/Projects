package bench;


import generator.Loader;
import org.openjdk.jmh.annotations.*;

import java.io.File;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 2)
@Fork(2)
@Measurement(iterations = 4)
public class TestBM {
    @State(Scope.Thread)
    public static class BenchClass {

        @TearDown(Level.Trial)
        public void deleteAllFiles() {
            for (File file : new File("res/").listFiles())
                if (file.isFile()) file.delete();
        }
    }

    @Benchmark
    public void singleThreadingGenerator() {
        Loader.singleThreadingGenerator();
    }

    @Benchmark
    public void multiThreadingGeneratorBench() {
        Loader.multiThreadingGenerator();

    }


}
