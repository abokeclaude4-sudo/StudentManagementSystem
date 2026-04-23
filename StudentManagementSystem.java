import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    public static void main(String[] args) {

        ArrayList<String> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try {
    File file = new File("students.txt");
    Scanner fileScanner = new Scanner(file);

    while (fileScanner.hasNextLine()) {
        students.add(fileScanner.nextLine());
    }

    fileScanner.close();
    System.out.println("Students loaded from file!");

} catch (FileNotFoundException e) {
    System.out.println("No previous data found.");
}

        while (true) {
            System.out.println("\n==============================");
            System.out.println("  STUDENT MANAGEMENT SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Save to File");
            System.out.println("6. Exit");
            System.out.println("==============================");
            System.out.print("Choose an option: ");

            int choice;

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                students.add(name);
                System.out.println("Student added successfully!");

            } else if (choice == 2) {

                if (students.isEmpty()) {
                    System.out.println("\nNo students added yet.");
                } else {
                    System.out.println("\nStudent List:");

                    int index = 1;
                    for (String student : students) {
                        System.out.println(index + ". " + student);
                        index++;
                    }
                }

            } else if (choice == 3) {

                if (students.isEmpty()) {
                    System.out.println("\nNo students to delete.");
                } else {
                    System.out.println("\nStudent List:");

                    int index = 1;
                    for (String student : students) {
                        System.out.println(index + ". " + student);
                        index++;
                    }

                    System.out.print("Enter student number to delete: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (deleteIndex > 0 && deleteIndex <= students.size()) {
                        students.remove(deleteIndex - 1);
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Invalid number.");
                    }
                }
} else if (choice == 4) {

    if (students.isEmpty()) {
        System.out.println("\nNo students to search.");
    } else {
        System.out.print("Enter student name to search: ");
        String searchName = scanner.nextLine();

        boolean found = false;

        for (String student : students) {
            if (student.equalsIgnoreCase(searchName)) {
                System.out.println("Student found: " + student);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
 } else if (choice == 5) {

    try {
        FileWriter writer = new FileWriter("students.txt");

        for (String student : students) {
            writer.write(student + "\n");
        }

        writer.close();
        System.out.println("Students saved to file successfully!");

    } catch (IOException e) {
        System.out.println("An error occurred while saving.");
    }

} else if (choice == 6) {
    System.out.println("Exiting program...");
    break;   


            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}