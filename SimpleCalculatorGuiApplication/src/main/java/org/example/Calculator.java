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

    private JTextField input1 = new JTextField(6);
    private JTextField input2 = new JTextField(6);

    private JButton button_sum = new JButton("ADDITION");
    private JButton button_sub = new JButton("SUBTRACTION");
    private JButton button_mul = new JButton("MULTIPLICATION");
    private JButton button_div = new JButton("DIVISION");

    private JTextArea display = new JTextArea(10,20);

    public Calculator(){
        getContentPane().setLayout(new GridLayout(9,1));
        setSize(600,600);
        setLocation(100,150);
        setTitle("Calculator");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });



        getContentPane().add(prompt1);
        getContentPane().add(input1);
        getContentPane().add(prompt2);
        getContentPane().add(input2);

        getContentPane().add(button_sum);
        getContentPane().add(button_sub);
        getContentPane().add(button_mul);
        getContentPane().add(button_div);

        getContentPane().add(display);

        display.setLineWrap(true);
        display.setEditable(false);

        button_sum.addActionListener(this);
        button_sub.addActionListener(this);
        button_mul.addActionListener(this);
        button_div.addActionListener(this);
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
