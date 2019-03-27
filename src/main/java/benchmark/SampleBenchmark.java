package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 1)
@State(Scope.Thread)
@Fork(1)
public class SampleBenchmark {

    @State(Scope.Thread)
    public static class BenchmarkState
    {
        private AtomicLong n;
        private int a;
        private int b;


        @Setup(Level.Iteration) public void
        initialize() {
            this.n = new AtomicLong(0);
            this.a = 2;
            this.b = 3;
        }
    }

    @Benchmark
    public void timeOfAtomic(BenchmarkState sate, Blackhole bh) {
        long v = sate.n.incrementAndGet();
        bh.consume(v);

    }

    @Benchmark
    public void timeOfAdd(BenchmarkState sate, Blackhole bh) {
        int a = sate.a;
        int b = sate.b;
        int c = a + b;
        bh.consume(c);
    }


    public static void runTests() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + SampleBenchmark.class.getSimpleName() + ".*")
                //.addProfiler(LinuxPerfProfiler.class)
                //.addProfiler(LinuxPerfNormProfiler.class)
                //.addProfiler(LinuxPerfAsmProfiler.class)
                //.shouldDoGC(true)
                //.addProfiler(WinPerfAsmProfiler.class)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                .build();
        new Runner(opt).run();
    }

}
