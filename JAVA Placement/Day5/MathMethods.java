package Day5;

public class MathMethods {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        double x = 25.64;
        System.out.println("Different Kind of Maths Functional Methods ");
        System.out.println("Maximum: " + Math.max(a, b));

        System.out.println("Minimum: " + Math.min(a, b));

        System.out.println("Square Root: " + Math.sqrt(25));

        System.out.println("Power: " + Math.pow(2, 3));

        System.out.println("Absolute Value: " + Math.abs(-15));

        System.out.println("Ceiling: " + Math.ceil(x));

        System.out.println("Floor: " + Math.floor(x));

        System.out.println("Round: " + Math.round(x));

        System.out.println("Random Number: " + Math.random());

        System.out.println("Cube Root: " + Math.cbrt(27));

        System.out.println("Signum: " + Math.signum(-10));

        System.out.println("Sin(90°): " + Math.sin(Math.toRadians(90)));

        System.out.println("Cos(0°): " + Math.cos(Math.toRadians(0)));

        System.out.println("Tan(45°): " + Math.tan(Math.toRadians(45)));

        System.out.println("Log Base e: " + Math.log(10));

        System.out.println("Log Base 10: " + Math.log10(100));

        System.out.println("PI Value: " + Math.PI);

        System.out.println("Euler Number (e): " + Math.E);
    }
}