import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame {
    private JComboBox<String> baseCurrency;
    private JComboBox<String> targetCurrency;
    private JTextField amountField;
    private JButton convertButton;
    private JLabel resultLabel;

    private static final double USD_TO_EUR = 0.85;
    private static final double EUR_TO_USD = 1.18;
    private static final double USD_TO_INR = 82.57;
    private static final double INR_TO_USD = 0.011;
    private static final double EUR_TO_INR = 94.40;
    private static final double INR_TO_EUR = 0.011;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        
        String[] currencies = {"USD", "EUR", "INR"};
        baseCurrency = new JComboBox<>(currencies);
        targetCurrency = new JComboBox<>(currencies);
        amountField = new JTextField(10);
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("");

        add(new JLabel("Base Currency:"));
        add(baseCurrency);
        add(new JLabel("Target Currency:"));
        add(targetCurrency);
        add(new JLabel("Amount:"));
        add(amountField);
        add(convertButton);
        add(resultLabel);

        convertButton.addActionListener(new ConvertButtonListener());
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String base = (String) baseCurrency.getSelectedItem();
            String target = (String) targetCurrency.getSelectedItem();
            double amount;

            try {
                amount = Double.parseDouble(amountField.getText());
                double convertedAmount = convertCurrency(base, target, amount);
                resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, target));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
            }
        }
    }

    private double convertCurrency(String base, String target, double amount) {
        if (base.equals(target)) {
            return amount; 
        }

        switch (base) {
            case "USD":
                if (target.equals("EUR")) {
                    return amount * USD_TO_EUR;
                } else if (target.equals("INR")) {
                    return amount * USD_TO_INR;
                }
                break;
            case "EUR":
                if (target.equals("USD")) {
                    return amount * EUR_TO_USD;
                } else if (target.equals("INR")) {
                    return amount * EUR_TO_INR;
                }
                break;
            case "INR":
                if (target.equals("USD")) {
                    return amount * INR_TO_USD;
                } else if (target.equals("EUR")) {
                    return amount * INR_TO_EUR;
                }
                break;
        }
        return 0; 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
