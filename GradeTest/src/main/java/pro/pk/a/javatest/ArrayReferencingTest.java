package pro.pk.a.javatest;

import java.util.ArrayList;
import java.util.List;

public class ArrayReferencingTest {
    public static void main(String[] args) {
        class Int {
            private int i;

            Int(int i) {
                this.i = i;
            }

            public int getI() {
                return i;
            }

            public void setI(int i) {
                this.i = i;
            }
        }

        Int a = new Int(1);
        Int b = new Int(-1);
        Int[] l = new Int[]{a, b};

        for (Int i : l) {
            System.out.println(i.getI());
        }

        a.setI(999999999);
        b = new Int(0);

        for (Int i : l) {
            System.out.println(i.getI());
        }
    }
}
