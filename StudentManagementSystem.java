import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class StudentManagementSystem {
    static ArrayList<String> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        loadFromFile();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    saveToFile();
                    System.out.println("Students saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);
    }

    public static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(name);
        saveToFile();
        System.out.println("Student added successfully.");
    }

    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\nStudent List:");
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }
        }
    }

    public static void searchStudent() {
        System.out.print("Enter student name to search: ");
        String name = scanner.nextLine();

        if (students.contains(name)) {
            System.out.println("Student found: " + name);
        } else {
            System.out.println("Student not found.");
        }
    }

   public static void updateStudent() {
    viewStudents();

    if (students.isEmpty()) return;

    System.out.print("Enter student number to update: ");
    int index = scanner.nextInt() - 1;
    scanner.nextLine();

    if (index >= 0 && index < students.size()) {
        System.out.print("Enter new student name: ");
        String newName = scanner.nextLine();
        students.set(index, newName);
        saveToFile();
        System.out.println("Student updated successfully.");
    } else {
        System.out.println("Invalid number.");
    }
}

    public static void deleteStudent() {
    viewStudents();

    if (students.isEmpty()) return;

    System.out.print("Enter student number to delete: ");
    int index = scanner.nextInt() - 1;
    scanner.nextLine();

    if (index >= 0 && index < students.size()) {
        students.remove(index);
        saveToFile();
        System.out.println("Student deleted successfully.");
    } else {
        System.out.println("Invalid number.");
    }
}

    public static void saveToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            for (String student : students) {
                writer.write(student + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving students.");
        }
    }

    public static void loadFromFile() {
        try {
            File file = new File(FILE_NAME);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String student = fileReader.nextLine();
                students.add(student);
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            // No file yet. It will be created after adding students.
        }
    }
}
