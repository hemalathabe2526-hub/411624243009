public class SwitchExample {
    public static void main(String[] args) {
        int age = 21;
        int marks = 85;
        if (age >= 18 && marks >= 90) {
            System.out.println("You are an adult so you are eligible for a 100% scholarship.");
        } else {
            System.out.println("You are a minor or your marks are less than 90, so you are not eligible for a scholarship.");
        }
        switch (marks / 10) {
            case 10:
            case 9:
                System.out.println("Grade: A");
                break;
            case 8:
                System.out.println("Grade: B");
                break;
            case 7:
                System.out.println("Grade: C");
                break;
            case 6:
                System.out.println("Grade: D");
                break;
            default:
                System.out.println("Grade: F");
        }
    }
}
