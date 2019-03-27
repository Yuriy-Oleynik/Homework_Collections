package benchmark;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult1;

@JCStressTest
@Outcome(id = "2", expect = Expect.ACCEPTABLE, desc = "Default Boolean outcome.")
@State
public class TestSubject {
    private boolean a, b;
    private int x = 0;

    @Actor
    void executedOnCpu0(IntResult1 intResult1) {
        a = true;
        if(b == true) x++;
        intResult1.r1 = x;
    }

    @Actor
    void executedOnCpu1(IntResult1 intResult1) {
        b = true;
        if(a == true) x++;
        intResult1.r1 = x;
    }
}
