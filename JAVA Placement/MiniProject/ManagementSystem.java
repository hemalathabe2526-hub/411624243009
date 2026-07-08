import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ManagementSystem {
    public static final Scanner scanner = new Scanner(System.in);
    public static final List<Student> students = new ArrayList<>();
    public static int nextId = 501;

    public static void main(String[] args) {
        printBanner();
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ", 1, 10);
            switch (choice) {
                case 1 -> addStudent(); 
                case 2 -> viewAllStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> removeStudent();
                case 6 -> showAcademicSummary();
                case 7 -> updateAttendance();
                case 8 -> showHonorRoll();
                case 9 -> viewStudentsByGrade();
                case 10 -> System.out.println("Thank you! Learn more, grow more.");
            }
        } while (choice != 10);
    }

    public static void printBanner() {
        System.out.println("********************************************************");
        System.out.println("   Welcome to the Student Management Companion System");
        System.out.println("   Build stronger student journeys with thoughtful records.");
        System.out.println("********************************************************\n");
    }

    public static void printMenu() {
        System.out.println("========== Student Management System ==========");
        System.out.println("1. Register New Student");
        System.out.println("2. View All Student Profiles");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student Record");
        System.out.println("5. Remove Student Record");
        System.out.println("6. Show Academic Summary");
        System.out.println("7. Update Attendance Percentage");
        System.out.println("8. Show Honor Roll Candidates");
        System.out.println("9. View Students by Grade Level");
        System.out.println("10. Exit");
        System.out.println("===============================================");
    }

    public static void addStudent() {
        System.out.println("\n--- Register New Student ---");
        String name = readText("Enter student name: ");
        int age = readInt("Enter age: ", 3, 120);
        String program = readText("Enter program: ");
        String grade = readText("Enter grade level: ");
        double gpa = readDouble("Enter GPA (0.0 - 4.0): ", 0, 4);
        double attendance = readDouble("Enter attendance percentage: ", 0, 100);
        String subject = readText("Enter favorite subject: ");
        String club = readText("Enter club or activity: ");
        String goal = readText("Enter future goal: ");
        students.add(new Student(nextId++, name, age, program, grade, gpa, attendance, subject, club, goal));
        System.out.println("Student registered successfully. ID: " + (nextId - 1) + "\n");
    }

    public static void viewAllStudents() {
        System.out.println("\n--- Student Profiles ---");
        if (students.isEmpty()) {
            System.out.println("No students found. Register one first.\n");
            return;
        }
        printHeader();
        for (Student student : students) {
            System.out.println(student.row());
        }
        System.out.println();
    }

    public static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        Student student = findStudent(readInt("Enter student ID to search: ", 1, Integer.MAX_VALUE));
        if (student == null) {
            System.out.println("Student not found.\n");
        } else {
            System.out.println(student.profile());
        }
    }

    public static void updateStudent() {
        System.out.println("\n--- Update Student Record ---");
        Student student = findStudent(readInt("Enter student ID to update: ", 1, Integer.MAX_VALUE));
        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }

        System.out.println("Leave blank to keep current value.");
        student.name = readOptional("Name (" + student.name + "): ", student.name);
        student.age = readOptionalInt("Age (" + student.age + "): ", student.age);
        student.program = readOptional("Program (" + student.program + "): ", student.program);
        student.grade = readOptional("Grade (" + student.grade + "): ", student.grade);
        student.gpa = readOptionalDouble("GPA (" + student.gpa + "): ", student.gpa, 0, 4);
        student.attendance = readOptionalDouble("Attendance (" + student.attendance + "): ", student.attendance, 0, 100);
        student.subject = readOptional("Favorite subject (" + student.subject + "): ", student.subject);
        student.club = readOptional("Club (" + student.club + "): ", student.club);
        student.goal = readOptional("Future goal (" + student.goal + "): ", student.goal);
        System.out.println("Student record updated successfully.\n");
    }

    public static void removeStudent() {
        System.out.println("\n--- Remove Student Record ---");
        Student student = findStudent(readInt("Enter student ID to remove: ", 1, Integer.MAX_VALUE));
        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }
        students.remove(student);
        System.out.println("Student removed successfully.\n");
    }

    public static void showAcademicSummary() {
        System.out.println("\n--- Academic Summary ---");
        if (students.isEmpty()) {
            System.out.println("No students available to summarize.\n");
            return;
        }

        double totalGpa = 0;
        double totalAttendance = 0;
        int honorCount = 0;
        int probationCount = 0;
        for (Student student : students) {
            totalGpa += student.gpa;
            totalAttendance += student.attendance;
            if (student.isHonorRoll()) honorCount++;
            if (student.isOnProbation()) probationCount++;
        }

        Student top = Collections.max(students, Comparator.comparingDouble(s -> s.gpa));
        System.out.printf("Total students: %d\n", students.size());
        System.out.printf("Average GPA: %.2f\n", totalGpa / students.size());
        System.out.printf("Average attendance: %.1f%%\n", totalAttendance / students.size());
        System.out.printf("Honor roll candidates: %d\n", honorCount);
        System.out.printf("On probation: %d\n", probationCount);
        System.out.printf("Top performer: %s (ID %d)\n\n", top.name, top.id);
    }

    public static void updateAttendance() {
        System.out.println("\n--- Update Attendance Percentage ---");
        Student student = findStudent(readInt("Enter student ID: ", 1, Integer.MAX_VALUE));
        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }
        student.attendance = readDouble("New attendance percentage: ", 0, 100);
        System.out.println("Attendance updated successfully.\n");
    }

    public static void showHonorRoll() {
        System.out.println("\n--- Honor Roll Candidates ---");
        List<Student> honorRoll = new ArrayList<>();
        for (Student student : students) {
            if (student.isHonorRoll()) honorRoll.add(student);
        }
        if (honorRoll.isEmpty()) {
            System.out.println("No honor roll candidates at the moment.\n");
            return;
        }
        honorRoll.sort(Comparator.comparingDouble(s -> -s.gpa));
        printHeader();
        for (Student student : honorRoll) {
            System.out.println(student.row());
        }
        System.out.println();
    }

    public static void viewStudentsByGrade() {
        System.out.println("\n--- View Students by Grade Level ---");
        String grade = readText("Enter grade level: ");
        List<Student> filtered = new ArrayList<>();
        for (Student student : students) {
            if (student.grade.equalsIgnoreCase(grade.trim())) {
                filtered.add(student);
            }
        }
        if (filtered.isEmpty()) {
            System.out.println("No students found for grade " + grade + ".\n");
            return;
        }
        printHeader();
        for (Student student : filtered) {
            System.out.println(student.row());
        }
        System.out.println();
    }

    public static Student findStudent(int id) {
        for (Student student : students) {
            if (student.id == id) return student;
        }
        return null;
    }

    public static void printHeader() {
        System.out.printf("%-4s %-16s %-4s %-8s %-5s %-7s %-10s%n", "ID", "Name", "GPA", "Att%", "Grade", "Status", "Program");
        System.out.println("----------------------------------------------------------------------------");
    }

    public static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Please enter a valid number.");
        }
    }

    public static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Please enter a valid value.");
        }
    }

    public static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("This field cannot be empty.");
        }
    }

    public static String readOptional(String prompt, String current) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? current : input;
    }

    public static int readOptionalInt(String prompt, int current) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return current;
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return current;
        }
    }

    public static double readOptionalDouble(String prompt, double current, double min, double max) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return current;
        try {
            double value = Double.parseDouble(input);
            if (value >= min && value <= max) return value;
        } catch (NumberFormatException ignored) {
        }
        return current;
    }

    public static class Student {
        int id, age;
        String name, program, grade, subject, club, goal;
        double gpa, attendance;
        Student(int id, String name, int age, String program, String grade, double gpa, double attendance, String subject, String club, String goal) {
            this.id = id; 
            this.name = name; 
            this.age = age;
            this.program = program; 
            this.grade = grade; 
            this.gpa = gpa;
            this.attendance = attendance; 
            this.subject = subject; 
            this.club = club;
            this.goal = goal;
        }
        boolean isHonorRoll() {
            return gpa >= 3.5 && attendance >= 90;
        }
        boolean isOnProbation() {
            return gpa < 2.0 || attendance < 75;
        }
        String status() {
            if (isHonorRoll()) return "Honor";
            if (isOnProbation()) return "Probation";
            return "Good";
        }
        String row() {
            return String.format("%-4d %-16s %-4.2f %-7.1f %-5s %-7s %-10s", id, trim(name, 16), gpa, attendance, grade, status(), trim(program, 10));
        }
        String profile() {
            return "\nStudent ID: " + id + "\nName: " + name + "\nAge: " + age + "\nProgram: " + program + "\nGrade: " + grade + "\nGPA: " + gpa + "\nAttendance: " + attendance + "%\nStatus: " + status() + "\nFavorite subject: " + subject + "\nClub: " + club + "\nFuture goal: " + goal + "\n";
        }
        
        public String trim(String text, int length) {
            return text.length() <= length ? text : text.substring(0, length - 3) + "...";
        }
    }
}