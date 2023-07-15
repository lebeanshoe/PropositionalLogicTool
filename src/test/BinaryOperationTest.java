import model.BinaryOperation;
import model.Proposition;
import operators.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryOperationTest {
    private Proposition simpleNot;
    private Proposition simpleBin1;
    private Proposition simpleBin2;

    @BeforeEach
    void runBefore() {
        simpleNot = new BinaryOperation("~a");
        simpleBin1 = new BinaryOperation("avb");
        simpleBin2 = new BinaryOperation("a^b");
    }

    @Test
    void testConstructor() {
        assertEquals(new Not(), simpleNot.getOperator());
    }
}
