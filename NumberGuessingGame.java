import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private static final int MAX_ATTEMPTS = 10;
    private int numberToGuess;
    private int attempts;
    private JLabel feedbackLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JButton resetButton;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        feedbackLabel = new JLabel("Guess a number between 1 and 10:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resetButton = new JButton("Reset");

        add(feedbackLabel);
        add(guessField);
        add(guessButton);
        add(resetButton);

        guessButton.addActionListener(new GuessButtonListener());
        resetButton.addActionListener(new ResetButtonListener());

        resetGame();
    }

    private void resetGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(10) + 1; 
        attempts = 0;
        feedbackLabel.setText("Guess a number between 1 and 10:");
        guessField.setText("");
        guessButton.setEnabled(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;

                if (guess < numberToGuess) {
                    feedbackLabel.setText("Too low! Try again.");
                } else if (guess > numberToGuess) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Congratulations! You've guessed the number in " + attempts + " attempts.");
                    animateButton();
                    guessButton.setEnabled(false); 
                }

                if (attempts >= MAX_ATTEMPTS) {
                    feedbackLabel.setText("Sorry, you've used all attempts. The number was " + numberToGuess + ".");
                    guessButton.setEnabled(false); 
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetGame();
        }
    }

    private void animateButton() {
        
        int x = (int) (Math.random() * (getWidth() - guessButton.getWidth()));
        int y = (int) (Math.random() * (getHeight() - guessButton.getHeight()));
        guessButton.setLocation(x, y);
        guessButton.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}
