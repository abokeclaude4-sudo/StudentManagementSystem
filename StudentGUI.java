import javax.swing.*;
import java.awt.event.*;

public class StudentGUI extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextArea displayArea;
    private StudentManager manager;

    public StudentGUI() {

        manager = new StudentManager();

        setTitle("Student Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(20, 20, 120, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(140, 20, 200, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(20, 55, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(140, 55, 200, 25);
        add(nameField);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(20, 95, 150, 30);
        add(addButton);

        JButton viewButton = new JButton("View Students");
        viewButton.setBounds(190, 95, 150, 30);
        add(viewButton);

        JButton searchButton = new JButton("Search Student");
        searchButton.setBounds(20, 135, 150, 30);
        add(searchButton);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.setBounds(20, 175, 150, 30);
        add(deleteButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.setBounds(190, 135, 150, 30);
        add(updateButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(190, 175, 150, 30);
        add(clearButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(20, 215, 320, 30);
        add(exitButton);

        displayArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(displayArea);

        scrollPane.setBounds(20, 230, 540, 200);

        add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText().trim();

                if (!name.isEmpty()) {
                    manager.addStudent(name);
                    displayArea.setText("Student added successfully: " + name);
                    JOptionPane.showMessageDialog(
        null,
                "Student added successfully!"
);
                    nameField.setText("");
                } else {
                    displayArea.setText("Please enter a student name.");
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                displayArea.setText("");

                if (manager.getStudents().isEmpty()) {
                    displayArea.setText("No students found.");
                    return;
                }

                for (String student : manager.getStudents()) {

                    String[] parts = student.split(",");

                    if (parts.length == 2) {
                        displayArea.append("ID: " + parts[0] + " | Name: " + parts[1] + "\n");
                    }
                }
                displayArea.append("\nTotal Students: " + manager.getStudents().size());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String searchName = nameField.getText().trim();
                String searchId = idField.getText().trim();

                if (searchName.isEmpty() && searchId.isEmpty()) {
                displayArea.setText("Please enter a student ID or student name to search.");
                return;
                }

                boolean found = false;

                for (String student : manager.getStudents()) {

                    String[] parts = student.split(",");

                    if (
                        (parts.length == 2 &&
                        parts[1].equalsIgnoreCase(searchName))
                        ||
                        (parts.length == 2 &&
                         parts[0].equals(searchId))
       ) {
                        displayArea.setText("Student found: ID " + parts[0] + " | Name: " + parts[1]);
                        JOptionPane.showMessageDialog(
        null,
               "Student found!"
);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    displayArea.setText("Student not found.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        String input = idField.getText().trim();

        if (input.isEmpty()) {
            displayArea.setText("Enter student ID to delete.");
            return;
        }

        try {

            int id = Integer.parseInt(input);

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this student?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                manager.deleteStudent(id);

                displayArea.setText("Student deleted successfully.");

                JOptionPane.showMessageDialog(
                        null,
                        "Delete completed."
                );

                nameField.setText("");

            } else {

                displayArea.setText("Delete cancelled.");
            }

        } catch (NumberFormatException ex) {

            displayArea.setText("Please enter a valid numeric ID.");
        }
    }
});

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                displayArea.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);

        updateButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {

           String idText = idField.getText().trim();
           String newName = nameField.getText().trim();

           if (idText.isEmpty() || newName.isEmpty()) {
           displayArea.setText("Enter student ID and new name.");
           return;
    }

        try {

            int id = Integer.parseInt(idText);

            boolean updated = manager.updateStudent(id, newName);

            if (updated) {

                displayArea.setText(
                    "Student updated successfully."
                );

                JOptionPane.showMessageDialog(
                        null,
                        "Student updated successfully!"
                );

                nameField.setText("");

            } else {

                displayArea.setText(
                    "Student ID not found."
                );
            }

        } catch (NumberFormatException ex) {

            displayArea.setText(
                "Invalid format. Example: 1,Claude"
            );
        }
    }
});
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}