package Day9;

public class MethodOverLoading {

    // // Method Overloading
    // class Calculator {
    //     void add(int a, int b) {
    //         System.out.println("Sum of 2 numbers = " + (a + b));
    //     }
    //     void add(int a, int b, int c) {
    //         System.out.println("Sum of 3 numbers = " + (a + b + c));
    //     }
    // }
    // public static void main(String[] args) {
    //     MethodOverLoading outer = new MethodOverLoading();
    //     Calculator calc = outer.new Calculator();
    //     calc.add(10, 20);
    //     calc.add(10, 20, 30);
    // }


    // Abstraction Program
    static abstract class Animal{
        abstract void sound(); // abstract methon it does not contain body

        // normal method
        void colour() {
            System.err.println("Tell about Animal colour");
        }
    }
    static class Lion extends Animal {
        void sound(){
            System.out.println("Lion will roar");
        }
    }
        public static void main(String[] args) {
            Lion l = new Lion();
            l.sound();
            l.colour();
        }
    }