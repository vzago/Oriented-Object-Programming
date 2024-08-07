package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyGUI extends JFrame implements ActionListener {

    private JButton clickme = new JButton("ClickMe");
    private boolean isFirstClick = true;

    public MyGUI() {
        getContentPane().add(clickme); // Adiciona clickme ao painel de conteúdo
        clickme.addActionListener(this); // Adiciona um ouvinte de ação ao botão
        setSize(200, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao clicar no "X"
    } // Construtor MyGUI

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clickme) { //The getSource() method is used to get the Object that caused the event.
            if (isFirstClick) {
                clickme.setText("*"); // Apaga o texto inicial e adiciona um asterisco
                isFirstClick = false; // Marca que o primeiro clique já ocorreu
            } else {
                clickme.setText(clickme.getText() + "*"); // Adiciona um asterisco ao texto do botão
            }
        }
    } // actionPerformed

    public static void main(String[] args) {
        new MyGUI();
    }
} // Classe MyGUI
