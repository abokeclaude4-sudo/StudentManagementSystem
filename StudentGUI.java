import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentGUI {

    public static void main(String[] args) {
 
        StudentManager manager = new StudentManager();

        JFrame frame = new JFrame("Student Management System");
        frame.setSize(650, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Student Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton updateBtn = new JButton("Update");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(updateBtn);

        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Records"));

        frame.add(title, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addBtn.addActionListener(e -> {

            String name = nameField.getText();

            manager.addStudent(name);

            output.setText("Student added successfully!");

            nameField.setText("");
        });

        viewBtn.addActionListener(e -> {

            ArrayList<Student> students = manager.getStudents();

            if (students.isEmpty()) {
                output.setText("No students found.");
            } else {
                String result = "";

                for (Student student : students) {
                    result += "ID: " + student.getId()
                            + " | Name: " + student.getName()
                            + "\n";
                }

                output.setText(result);
            }
        });

        deleteBtn.addActionListener(e -> {

            try {
                int id = Integer.parseInt(idField.getText());

                manager.deleteStudent(id);

                output.setText("Student deleted successfully!");

                idField.setText("");

            } catch (NumberFormatException ex) {
                output.setText("Please enter a valid student ID.");
            }
        });

        searchBtn.addActionListener(e -> {

            try {
                int id = Integer.parseInt(idField.getText());

                ArrayList<Student> students = manager.getStudents();

                boolean found = false;

                for (Student student : students) {

                    if (student.getId() == id) {

                        output.setText(
                                "Found Student:\nID: "
                                        + student.getId()
                                        + "\nName: "
                                        + student.getName()
                        );

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    output.setText("Student not found.");
                }

            } catch (NumberFormatException ex) {
                output.setText("Please enter a valid student ID.");
            }
        });

        frame.setVisible(true);
    }
}