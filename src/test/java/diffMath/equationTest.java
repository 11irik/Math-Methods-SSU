package diffMath;

import org.junit.Test;

import static org.junit.Assert.*;

public class equationTest {

    @Test
    public void count() {
        String s = "1 + 4 + 3 + 0";
        Equation equation = new Equation(s);
        System.out.println(equation.count(2));
    }
}