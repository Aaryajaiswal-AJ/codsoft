import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame {
    private JTextField[] marksFields;
    private JButton calculateButton;
    private JLabel resultLabel;
    private int numberOfSubjects;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

       
        String input = JOptionPane.showInputDialog("Enter the number of subjects:");
        try {
            numberOfSubjects = Integer.parseInt(input);
            if (numberOfSubjects <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for the number of subjects.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        marksFields = new JTextField[numberOfSubjects];

        
        for (int i = 0; i < numberOfSubjects; i++) {
            marksFields[i] = new JTextField(5);
            add(new JLabel("Marks for Subject " + (i + 1) + ":"));
            add(marksFields[i]);
        }

        calculateButton = new JButton("Calculate Grade");
        resultLabel = new JLabel("");
        add(calculateButton);
        add(resultLabel);

        calculateButton.addActionListener(new CalculateButtonListener());
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int totalMarks = 0;
            for (JTextField marksField : marksFields) {
                try {
                    totalMarks += Integer.parseInt(marksField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StudentGradeCalculator.this, "Please enter valid marks for all subjects.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            double averagePercentage = (double) totalMarks / numberOfSubjects;
            for (JTextField marksField : marksFields) {
                try {
                    totalMarks += Integer.parseInt(marksField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StudentGradeCalculator.this, "Please enter valid marks for all subjects.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            String grade = calculateGrade(averagePercentage);
            resultLabel.setText("Total Marks: " + totalMarks + ", Average: " + averagePercentage + "%, Grade: " + grade);
            animateResultLabel();
        }
    }

    private String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    private void animateResultLabel() {
        
        new Timer(500, new ActionListener() {
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 5) {
                    resultLabel.setForeground(count % 2 == 0 ? Color.RED : Color.BLACK);
                    count++;
                } else {
                    ((Timer) e.getSource()).stop();
                    resultLabel.setForeground(Color.BLACK); // Reset color
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeCalculator calculator = new StudentGradeCalculator();
            calculator.setVisible(true);
        });
    }
}
