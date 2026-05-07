import javax.swing.*;
import java.awt.*;

public class StudentGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Management System");
        frame.setSize(400, 450);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Student Management System");
        label.setBounds(90, 20, 250, 30);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(100, 70, 180, 30);

        JButton viewButton = new JButton("View Students");
        viewButton.setBounds(100, 120, 180, 30);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.setBounds(100, 330, 180, 30);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 170, 280, 140);
        textArea.setEditable(false);

        StudentManager manager = new StudentManager();

        addButton.addActionListener(event -> {
            String name = JOptionPane.showInputDialog(frame, "Enter student name:");

            if (name != null && !name.trim().isEmpty()) {
                manager.addStudent(name.trim());

                textArea.setText("");
                for (String student : manager.students) {
                    String[] parts = student.split(",");
                    if (parts.length == 2) {
                        textArea.append("ID: " + parts[0] + " | Name: " + parts[1] + "\n");
                    } else {
                        textArea.append(student + "\n");
                    }
                }

                JOptionPane.showMessageDialog(frame, "Student added successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
            }
        });

        viewButton.addActionListener(event -> {
            textArea.setText("");

            for (String student : manager.students) {
                String[] parts = student.split(",");
                if (parts.length == 2) {
                    textArea.append("ID: " + parts[0] + " | Name: " + parts[1] + "\n");
                } else {
                    textArea.append(student + "\n");
                }
            }
        });

        deleteButton.addActionListener(event -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Student ID to delete:");

            try {
                int id = Integer.parseInt(input);

                if (id > 0 && id <= manager.students.size()) {
                    manager.students.remove(id - 1);

                    textArea.setText("");
                    for (String student : manager.students) {
                        String[] parts = student.split(",");
                        if (parts.length == 2) {
                            textArea.append("ID: " + parts[0] + " | Name: " + parts[1] + "\n");
                        } else {
                            textArea.append(student + "\n");
                        }
                    }

                    JOptionPane.showMessageDialog(frame, "Student deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Student ID.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        frame.add(label);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(deleteButton);
        frame.add(textArea);

        frame.setVisible(true);
    }
}