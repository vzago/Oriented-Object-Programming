package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterativeColorMixer extends JFrame {
    private JSlider red = new JSlider(0,255);
    private JSlider green = new JSlider(0,255);
    private JSlider blue = new JSlider(0,255);

    private JLabel red_slider = new JLabel("Current Value of red");
    private JLabel green_slider = new JLabel("Current Value of green");
    private JLabel blue_slider = new JLabel("Current Value of blue");

    private JPanel mixed_color = new JPanel();


    public InterativeColorMixer(){
        getContentPane().setLayout(new GridLayout(9,1));
        setSize(600,600);
        setLocation(100,150);
        setTitle("Color Mixer Interactive");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        getContentPane().add(red_slider);
        getContentPane().add(red);

        getContentPane().add(green_slider);
        getContentPane().add(green);

        getContentPane().add(blue_slider);
        getContentPane().add(blue);

        getContentPane().add(mixed_color);
        setBackground(Color.BLACK);

        //Set initial background color
        mixed_color.setBackground(new Color(red.getValue(), green.getValue(), blue.getValue()));

        ChangeListener color_change_listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Get values from the sliders
                int r = red.getValue();
                int g = green.getValue();
                int b = blue.getValue();

                //Update the labels with current values;
                red_slider.setText("Red: " + r +"%");
                green_slider.setText("Green: " +g+"%");
                blue_slider.setText("Blue: "+b+"%");

                mixed_color.setBackground(new Color(r,g,b));
            }
        };

        red.addChangeListener(color_change_listener);
        green.addChangeListener(color_change_listener);
        blue.addChangeListener(color_change_listener);

    }

    public static void main(String[] args) {
        InterativeColorMixer colorMixer = new InterativeColorMixer();
    }
}