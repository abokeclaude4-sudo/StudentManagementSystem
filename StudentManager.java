import java.io.*;
import java.util.ArrayList;

public class StudentManager {

    ArrayList<String> students = new ArrayList<>();
    String fileName = "students.txt";
    int nextId = 1;

    public StudentManager() {
        loadStudents();
    }

    public void addStudent(String name) {
      for (String student : students) {
        name = name.substring(0,1).toUpperCase()
       + name.substring(1).toLowerCase();

    String[] parts = student.split(",");

    if (parts[1].equalsIgnoreCase(name)) {
        System.out.println("Student already exists!");
        return;
    }
}

String studentRecord = nextId + "," + name;
students.add(studentRecord); 
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\nStudent List:");
            for (String student : students) {
                String[] parts = student.split(",");
                System.out.println("ID: " + parts[0] + " | Name: " + parts[1]);
            }
        }
    }

    public void removeStudent(String id) {
        boolean removed = false;

        for (int i = 0; i < students.size(); i++) {
            String[] parts = students.get(i).split(",");

            if (parts[0].equals(id)) {
                students.remove(i);
                removed = true;
                saveStudents();
                System.out.println("Student removed successfully!");
                break;
            }
        }

        if (!removed) {
            System.out.println("Student not found.");
        }
    }

    public void searchStudent(String id) {
        boolean found = false;

        for (String student : students) {
            String[] parts = student.split(",");

            if (parts[0].equals(id)) {
                System.out.println("Student found: ID: " + parts[0] + " | Name: " + parts[1]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void updateStudent(String id, String newName) {
        boolean updated = false;

        for (int i = 0; i < students.size(); i++) {
            String[] parts = students.get(i).split(",");

            if (parts[0].equals(id)) {
                students.set(i, id + "," + newName);
                updated = true;
                saveStudents();
                System.out.println("Student updated successfully!");
                break;
            }
        }

        if (!updated) {
            System.out.println("Student not found.");
        }
    }

    public void saveStudents() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (String student : students) {
                writer.write(student);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving students.");
        }
    }

    public void loadStudents() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            int highestId = 0;

            while ((line = reader.readLine()) != null) {
                students.add(line);

                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0]);

                if (currentId > highestId) {
                    highestId = currentId;
                }
            }

            nextId = highestId + 1;
            reader.close();

        } catch (IOException e) {
            System.out.println("No previous student records found.");
        }
    }
}