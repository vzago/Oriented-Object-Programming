package org.example;

import javax.swing.*; // Packages used

import java.awt.*;

import java.awt.event.*;

public class Converter extends JFrame implements ActionListener{

    private JLabel prompt = new JLabel("Distance in miles: ");

    private JTextField input = new JTextField(6);

    private JTextArea display = new JTextArea(10,20);

    private JButton convert = new JButton("Convert!");

    public Converter() {

        getContentPane().setLayout(new FlowLayout());

        getContentPane().add(prompt);

        getContentPane().add(input);

        getContentPane().add(convert);

        getContentPane().add(display);

        display.setLineWrap(true);

        display.setEditable(false);

        convert.addActionListener(this);

    } // Converter()

    public void actionPerformed( ActionEvent e ) {

        double miles = Double.valueOf(input.getText()).doubleValue();

        double km = MetricConverter.milesToKm(miles);

        display.append(miles + " miles equals " + km +

                " kilometers\n");

    } // actionPerformed()

    public static void main(String args[]) {

        Converter f = new Converter();

        f.setSize(400, 300);

        f.setVisible(true);

// Quit the application

        f.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

    }// main()

    public class MetricConverter {

        public static double milesToKm(double miles) {

            return miles / 0.62;

        }

    }

} //