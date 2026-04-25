import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("6. Search Student");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            if (choice == 1) {
                scanner.nextLine();

                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter student name: ");
                String name = scanner.nextLine();

                ids.add(id);
                students.add(name);

                System.out.println("✅ Student added!");

            } else if (choice == 2) {
                System.out.println("\n📋 Student List:");

                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println("ID: " + ids.get(i) + " | Name: " + students.get(i));
                    }
                }

            } else if (choice == 3) {
                System.out.print("Enter student ID to update: ");
                int updateId = scanner.nextInt();
                scanner.nextLine();

                boolean updated = false;

                for (int i = 0; i < ids.size(); i++) {
                    if (ids.get(i) == updateId) {
                        System.out.print("Enter new student name: ");
                        String newName = scanner.nextLine();
                        students.set(i, newName);
                        System.out.println("✅ Student updated!");
                        updated = true;
                        break;
                    }
                }

                if (!updated) {
                    System.out.println("❌ Student not found.");
                }

            } else if (choice == 4) {
                System.out.print("Enter student ID to delete: ");
                int deleteId = scanner.nextInt();

                boolean removed = false;

                for (int i = 0; i < ids.size(); i++) {
                    if (ids.get(i) == deleteId) {
                        ids.remove(i);
                        students.remove(i);
                        System.out.println("🗑️ Student deleted!");
                        removed = true;
                        break;
                    }
                }

                if (!removed) {
                    System.out.println("❌ Student not found.");
                }

            } else if (choice == 6) {
                System.out.print("Enter student ID to search: ");
                int searchId = scanner.nextInt();

                boolean found = false;

                for (int i = 0; i < ids.size(); i++) {
                    if (ids.get(i) == searchId) {
                        System.out.println("🔍 Found: ID: " + ids.get(i) + " | Name: " + students.get(i));
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("❌ Student not found.");
                }

            } else if (choice == 5) {
                System.out.println("👋 Exiting program...");

            } else {
                System.out.println("⚠️ Invalid choice.");
            }

        } while (choice != 5);

        scanner.close();
    }
}