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
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@State(Scope.Thread)
public class MultiThreadingSampleBenchmark {

    @State(Scope.Thread)
    public static class BenchmarkState {
        private AtomicLong counterAtomic;
        private long counterPrimitive;
        private Object lock = new Object();
        private static final int MAX_ITERATION = 1000;


        @Setup(Level.Iteration)
        public void
        initialize() {
            this.counterAtomic = new AtomicLong(0);
            this.counterPrimitive = 0;
        }
    }

    @Benchmark
    @Group("incrementByAtomic")
    @GroupThreads(5)
    public void timeOfIncrementByAtomic(BenchmarkState state, Blackhole bh) {
        long v = 0;
        for (int i = 0; i < state.MAX_ITERATION; i++) {
            v += state.counterAtomic.incrementAndGet();
        }
        bh.consume(v);
    }

    @Benchmark
    @Group("incrementBySynchronized")
    @GroupThreads(5)
    public void timeOfIncrementBySynchronized(BenchmarkState state, Blackhole bh) {
        long v = 0;
        for (int i = 0; i < state.MAX_ITERATION; i++) {
            synchronized (state.lock) {
                v += ++state.counterPrimitive;
            }
        }
        bh.consume(v);
    }


    public static void runTests() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + MultiThreadingSampleBenchmark.class.getSimpleName() + ".*")
                .forks(1)
                //.addProfiler(LinuxPerfProfiler.class)
                //.addProfiler(LinuxPerfNormProfiler.class)
                //.addProfiler(LinuxPerfAsmProfiler.class)
                //.shouldDoGC(true)
                //.addProfiler(WinPerfAsmProfiler.class)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.syncIterations(true)
                .build();
        new Runner(opt).run();
    }

}
