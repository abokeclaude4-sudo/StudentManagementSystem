import java.io.*;
import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    private String fileName = "students.txt";
    private int nextId = 1;

    public StudentManager() {
        loadStudents();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    public int getStudentCount() {
    return students.size();
}

    public void addStudent(String name) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        name = name.trim();

        name = name.substring(0, 1).toUpperCase()
                + name.substring(1).toLowerCase();

        for (Student student : students) {

            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("Student already exists!");
                return;
            }
        }

        Student student = new Student(nextId, name);

        students.add(student);

        nextId++;

        saveStudents();

        System.out.println("Student added successfully.");
    }

    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public Student searchStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public void updateStudent(int id, String newName) {

        Student student = searchStudent(id);

        if (student != null) {

            if (newName == null || newName.trim().isEmpty()) {
                System.out.println("Student name cannot be empty.");
                return;
            }

            newName = newName.trim();

            newName = newName.substring(0, 1).toUpperCase()
                    + newName.substring(1).toLowerCase();

            student.setName(newName);

            saveStudents();

            System.out.println("Student updated successfully.");

        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent(int id) {

        Student student = searchStudent(id);

        if (student != null) {

            students.remove(student);

            saveStudents();

            System.out.println("Student deleted successfully.");

        } else {
            System.out.println("Student not found.");
        }
    }

    public void saveStudents() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (Student student : students) {
                writer.write(student.toString());
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

                String[] parts = line.split(",");

                if (parts.length == 2) {

                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];

                    Student student = new Student(id, name);

                    students.add(student);

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