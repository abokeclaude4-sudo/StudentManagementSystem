import javax.swing.*;
import java.awt.*;

public class StudentGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Management System");

        JLabel label = new JLabel("Student Management System");
        label.setBounds(90, 20, 250, 30);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(100, 70, 180, 30);

        StudentManager manager = new StudentManager();

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 170, 280, 100);
        textArea.setEditable(false);


        addButton.addActionListener(e -> {
    String name = JOptionPane.showInputDialog(frame, "Enter student name:");

    if (name != null && !name.trim().isEmpty()) {
        manager.addStudent(name.trim());
        JOptionPane.showMessageDialog(frame, "Student added successfully!");
    } else {
        JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
    }
    });
        JButton viewButton = new JButton("View Students");
        viewButton.setBounds(100, 120, 180, 30);
    viewButton.addActionListener(e -> {

    StringBuilder studentList = new StringBuilder();

    for (String student : manager.students) {

        String[] parts = student.split(",");

        studentList.append("ID: ")
                   .append(parts[0])
                   .append(" | Name: ")
                   .append(parts[1])
                   .append("\n");
    }

    textArea.setText(studentList.toString());
});

        frame.add(label);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(textArea);

        frame.setSize(400, 350);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}