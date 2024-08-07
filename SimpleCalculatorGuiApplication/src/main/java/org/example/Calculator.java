package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator extends JFrame implements ActionListener {
    private JLabel prompt1 = new JLabel("First Number: ");
    private JLabel prompt2 = new JLabel("Second Number: ");

    private JTextField input1 = new JTextField(20);
    private JTextField input2 = new JTextField(20);

    private JButton button_sum = new JButton("ADDITION");
    private JButton button_sub = new JButton("SUBTRACTION");
    private JButton button_mul = new JButton("MULTIPLICATION");
    private JButton button_div = new JButton("DIVISION");

    private JTextArea display = new JTextArea(5,20);

    public Calculator(){
        // Set the layout manager
        setLayout(new BorderLayout());

        // Create a panel for labels and text fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 5, 5)); // 2 rows, 2 columns with spacing

        // Add labels and text fields to the panel
        inputPanel.add(prompt1);
        inputPanel.add(input1);
        inputPanel.add(prompt2);
        inputPanel.add(input2);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 5, 5)); // 2 rows, 2 columns with spacing

        // Add buttons to the panel
        buttonPanel.add(button_sum);
        buttonPanel.add(button_sub);
        buttonPanel.add(button_mul);
        buttonPanel.add(button_div);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(display), BorderLayout.SOUTH);

        display.setLineWrap(true);
        display.setEditable(false);

        button_sum.addActionListener(this);
        button_sub.addActionListener(this);
        button_mul.addActionListener(this);
        button_div.addActionListener(this);

        setSize(400, 300);
        setLocation(100, 150);
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double number1 = Double.parseDouble(input1.getText());
            double number2 = Double.parseDouble(input2.getText());
            double result = 0;

            if (e.getSource() == button_sum) {
                result = number1 + number2;
                display.setText("Result: " + result);
            } else if (e.getSource() == button_sub) {
                result = number1 - number2;
                display.setText("Result: " + result);
            } else if (e.getSource() == button_mul) {
                result = number1 * number2;
                display.setText("Result: " + result);
            } else if (e.getSource() == button_div) {
                if (number2 != 0) {
                    result = number1 / number2;
                    display.setText("Result: " + result);
                } else {
                    display.setText("Cannot divide by zero");
                }
            }
        } catch (NumberFormatException ex) {
            display.setText("Invalid Input");
        }
    }
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

//This code will add a WindowListener, the window will close when the user clicks the "x" in the upper right corner of the window

    }
}
