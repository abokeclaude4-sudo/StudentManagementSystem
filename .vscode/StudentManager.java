import java.io.*;
import java.util.ArrayList;

public class StudentManager {

    ArrayList<String> students = new ArrayList<>();

    String fileName = "students.txt";
    int nextId = 1;

    public StudentManager() {
        loadStudents();
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void addStudent(String name) {

        name = name.substring(0, 1).toUpperCase()
                + name.substring(1).toLowerCase();

        for (String student : students) {

            String[] parts = student.split(",");

            if (parts.length == 2 && parts[1].equalsIgnoreCase(name)) {

                System.out.println("Student already exists!");
                return;
            }
        }

        String studentRecord = nextId + "," + name;

        students.add(studentRecord);

        nextId++;

        saveStudents();
    }

    public void viewStudents() {

        if (students.isEmpty()) {

            System.out.println("No students found.");

        } else {

            for (String student : students) {
                System.out.println(student);
            }
        }
    }

    public void deleteStudent(int id) {

        for (int i = 0; i < students.size(); i++) {

            String[] parts = students.get(i).split(",");

            if (Integer.parseInt(parts[0]) == id) {

                students.remove(i);

                saveStudents();

                return;
            }
        }

        System.out.println("Student not found.");
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

            while ((line = reader.readLine()) != null) {

                students.add(line);

                String[] parts = line.split(",");

                if (parts.length == 2) {

                    int id = Integer.parseInt(parts[0]);

                    if (id >= nextId) {
                        nextId = id + 1;
                    }
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("No previous student records found.");
        }
    }
}