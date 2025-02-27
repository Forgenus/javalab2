
import java.util.ArrayList;
import java.util.Deque;

import Calculator.*;
public class Main {


    public static void main(String[] args) {

        String input = "2x+10y*sqrt(10/2*cos(1))";
        double val = Calculator.calculate(input);
        System.out.println(val);

    }
}
