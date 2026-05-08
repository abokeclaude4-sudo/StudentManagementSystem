import javax.swing.*;
import java.awt.*;

public class StudentGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Management System");
        frame.setSize(400, 450);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Student Management System");
        label.setBounds(90, 20, 250, 30);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(100, 70, 180, 30);

        JButton viewButton = new JButton("View Students");
        viewButton.setBounds(100, 120, 180, 30);

        JButton searchButton = new JButton("Search Student");
        searchButton.setBounds(100, 170, 180, 30);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.setBounds(100, 330, 180, 30);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 220, 280, 90);

        StudentManager manager = new StudentManager();

        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Enter student name:");

            if (name != null && !name.trim().isEmpty()) {
                manager.addStudent(name.trim());
                textArea.setText("Student added successfully!");
            }
        });

        viewButton.addActionListener(e -> {
            textArea.setText("");

            for (String student : manager.getStudents()) {
                String[] parts = student.split(",");

                if (parts.length == 2) {
                    textArea.append("ID: " + parts[0] + " | Name: " + parts[1] + "\n");
                } else {
                    textArea.append(student + "\n");
                }
            }
        });

        searchButton.addActionListener(e -> {
            String searchName = JOptionPane.showInputDialog(frame, "Enter student name to search:");

            if (searchName != null && !searchName.trim().isEmpty()) {
                boolean found = false;

                for (String student : manager.getStudents()) {
                    String[] parts = student.split(",");

                    if (parts.length == 2 && parts[1].equalsIgnoreCase(searchName.trim())) {
                        textArea.setText("Student Found:\nID: " + parts[0] + " | Name: " + parts[1]);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    textArea.setText("Student not found.");
                }
            }
        });

        deleteButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Student ID to delete:");

            try {
                int id = Integer.parseInt(input);
                manager.deleteStudent(id);
                textArea.setText("Student deleted successfully.");
            } catch (Exception ex) {
                textArea.setText("Invalid ID.");
            }
        });

        frame.add(label);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(searchButton);
        frame.add(deleteButton);
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}