import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> students = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter student name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    students.add(name);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("Student List:");
                        for (String s : students) {
                            System.out.println("- " + s);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter student name to update: ");
                    scanner.nextLine();
                    String oldName = scanner.nextLine();
                    boolean updated = false;

                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).equalsIgnoreCase(oldName)) {
                            System.out.print("Enter new student name: ");
                            String newName = scanner.nextLine();
                            students.set(i, newName);
                            System.out.println("Student updated successfully!");
                            updated = true;
                            break;
                        }
                    }

                    if (!updated) {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student name to delete: ");
                    scanner.nextLine();
                    String deleteName = scanner.nextLine();

                    if (students.removeIf(s -> s.equalsIgnoreCase(deleteName))) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter student name to search: ");
                    scanner.nextLine();
                    String searchName = scanner.nextLine();
                    boolean found = false;

                    for (String s : students) {
                        if (s.equalsIgnoreCase(searchName)) {
                            System.out.println("Student found: " + s);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
