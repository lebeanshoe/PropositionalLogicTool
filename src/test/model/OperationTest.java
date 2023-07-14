package model;

import org.junit.jupiter.api.BeforeEach;

public class OperationTest {
    private Operation testOp1;
    private Operation testOp2;

    @BeforeEach
    void runBefore() {
        testOp1 = new Operation("a", "~", null, null, null);
        testOp2 = new Operation("b", "v", "c", null, null);

    }
}
