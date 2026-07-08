package Day9;

public class Encapsulation {

    // // Exapmle 1
    // static class Student {
    //     // private variables (data hiding)
    //     private String name;
    //     private int age;

    //     // setter methods
    //     void setName(String name) {
    //         this.name = name;
    //     }
    //     void setAge(int age) {
    //         if (age > 0) {
    //             this.age = age;
    //         } else {
    //             System.out.println("Invalid age");
    //         }
    //     }

    //     // getter methods
    //     String getName() {
    //         return name;
    //     }
    //     int getAge() {
    //         return age;
    //     }

    //     // normal method
    //     void display() {
    //         System.out.println("Student Name: " + name);
    //         System.out.println("Student Age: " + age);
    //     }
    // }
    // public static void main(String[] args) {
    //     Student s = new Student();
    //     s.setName("Arun");
    //     s.setAge(21);
    //     s.display();
    //     System.out.println("Using getters:");
    //     System.out.println(s.getName());
    //     System.out.println(s.getAge());
    // } 



    // Example 2
    static class BankAccount {

        // private variables (data hiding)
        private int account_id;
        private String account_Name;
        private double balance;

        // setter methods 
        public void setAccountId(int account_id) {
            this.account_id = account_id;
        }
        public void setAccountName(String account_Name) {
            this.account_Name = account_Name;
        }
        public void setBalance(double balance) {
            if (balance >= 0) {
                this.balance = balance;
            } else {
                System.out.println("Invalid Balance");
            }
        }
        public void setDetails(String account_id, String account_Name, int balance) {
            try {
                this.account_id = Integer.parseInt(account_id);
            } catch (NumberFormatException e) {
                System.out.println("Invalid account id");
                this.account_id = 0;
            }
            this.account_Name = account_Name;
            setBalance(balance);
        }
        
        // getter methods
        public int getAccountId() {
            return account_id;
        }
        public String getAccountName() {
            return account_Name;
        }
        public double getBalance() {
            return balance;
        }
        public void deposit(double amount){
            this.balance += amount;
        }
        public void withdraw(double amount){
            if(amount > 0 && amount <= balance){
                balance -= amount;
                System.out.println("Withdraw: " +amount);
            }else{
                System.out.println("Invalid withdraw amt or insufficient funds");
            }
        }

        // normal method
        public void getDetails() {
            System.out.println("Account ID: " + account_id);
            System.out.println("Account Name: " + account_Name);
            System.out.println("Balance: " + balance);
        }
    }
    public static class Main {
        public static void main(String[] args) {
            BankAccount acc = new BankAccount();
            acc.setDetails("162636", "Hemalatha", 10000);
            acc.getDetails();
        }
    }
}