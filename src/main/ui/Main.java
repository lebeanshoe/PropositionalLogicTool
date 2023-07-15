package ui;

import operators.*;

public class Main {
    public static void main(String[] args) {
        Operator and = new And();
        Operator bic = new Biconditional();
        Operator imp = new Implies();
        Operator not = new Not();
        Operator or = new Or();
        Operator xor = new Xor();

        System.out.println(not.evaluate(true, null));
        System.out.println(imp.evaluate(false, true));
        System.out.println(200 % 9);
    }
}
