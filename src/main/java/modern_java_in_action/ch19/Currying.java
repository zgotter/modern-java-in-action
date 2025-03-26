package modern_java_in_action.ch19;

import java.util.function.DoubleUnaryOperator;

public class Currying {
    public static void main(String[] args) {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        double gbp = convertUSDtoGBP.applyAsDouble(1000);
        System.out.println(gbp);
    }

    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }
}
