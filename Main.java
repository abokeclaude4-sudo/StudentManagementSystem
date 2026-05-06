import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        int choice;

        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Remove Student");
            System.out.println("4. Exit");
            System.out.println("5. Update Student");
            System.out.println("6. Search Student");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    manager.addStudent(name);
                    break;

                case 2:
                    manager.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter student ID to remove: ");
                    String removeId = scanner.nextLine();
                    manager.removeStudent(removeId);
                    break;

                case 4:
                    System.out.println("👋 Exiting...");
                    break;

                case 5:
                    System.out.print("Enter student ID to update: ");
                    String oldID = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    manager.updateStudent(oldID, newName);
                    break;

                case 6:
                    System.out.print("Enter student ID to search: ");
                    String searchName = scanner.nextLine();
                    manager.searchStudent(searchName);
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }

        } while (choice != 4);

        scanner.close();
    }
}