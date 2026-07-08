package Day9;

public class Interface {

    interface Payment {
        void pay();
    }
    static class CreditCardPayment implements Payment {
        public void pay() {
            System.out.println("Payment made using Credit Card.");
        }
    }
    static class UpiPayment implements Payment {
        public void pay() {
            System.out.println("Payment made using UPI.");
        }
    }
    public static class Main {
        public static void main(String[] args) {
            Payment payment1 = new CreditCardPayment();
            Payment payment2 = new UpiPayment();
            payment1.pay();
            payment2.pay();
        }
    }
}