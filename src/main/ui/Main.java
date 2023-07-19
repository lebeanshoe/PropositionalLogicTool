package ui;

import model.BinaryOperation;
import model.Proposition;
import operators.*;

import java.util.Arrays;
import java.util.*;

import static model.StatementSplitter.balancedGroups;

public class Main {
    public static void main(String[] args) {
        Operator and = new And();
        Operator bic = new Biconditional();
        Operator imp = new Implies();
        Operator not = new Not();
        Operator or = new Or();
        Operator xor = new Xor();

        Proposition simpleNot;
        Proposition simpleBin1;
        Proposition simpleBin2;

        Proposition longOperator1;
        Proposition longOperator2;

        Proposition compoundOp1;
        Proposition compoundOp2;

        Proposition twoDeep1;
        Proposition twoDeep2;

//        simpleNot = new BinaryOperation("~a");
//        simpleBin1 = new BinaryOperation("bvc");
//        simpleBin2 = new BinaryOperation("d^e");
//
//        longOperator1 = new BinaryOperation("f<->g");
//        longOperator2 = new BinaryOperation("h xor i");
//
//        compoundOp1 = new BinaryOperation("(xv(~y))");
//        compoundOp2 = new BinaryOperation("(av(~b))^(avc)"); // DIST law
//
//        twoDeep1 = new BinaryOperation("((a ^ b) v (c -> d)) xor ((c v a) -> (b <-> d))");
//        twoDeep2 = new BinaryOperation("((x -> z) xor (w ^ y)) ^ (((~x) v y) ^ (w xor z))");

        // String twoDeep2S = "((x -> z) xor (w ^ y)) ^ (((~x) v y) ^ (w xor z))";
        String twoDeep2S = "x^y";

        List<String> v = new ArrayList<>();

        // Function call
        v = balancedGroups(twoDeep2S);

        for (String i : v) {
            System.out.print(i + " ");
        }

        System.out.println(v);

        int veeSize = v.get(0).length();

        String veeSub0 = v.get(0).substring(1, veeSize - 1);

        System.out.println(veeSub0);

        System.out.println(balancedGroups(veeSub0));

        System.out.println(balancedGroups(v.get(4).substring(1, v.get(4).length() - 1)));

        System.out.println((double) (1 / 2));
    }
}