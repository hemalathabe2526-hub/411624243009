package Day8;

public class Constructors {
    static class Student {
        String name;
        int age;

        // Default constructor
        Student() {
            this.name = "Unknown";
            this.age = 0;
        }

        // Parameterized constructor
        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Copy constructor
        Student(Student other) {
            this.name = other.name;
            this.age = other.age;
        }

        String details() {
            return name + " (" + age + ")";
        }
    }

    public static void main(String[] args) {
        Student defaultStudent = new Student();
        Student paramStudent = new Student("Manish", 21);
        Student copyStudent = new Student(paramStudent);

        System.out.println("Default: " + defaultStudent.details());
        System.out.println("Parameterized: " + paramStudent.details());
        System.out.println("Copy: " + copyStudent.details());
    }
}