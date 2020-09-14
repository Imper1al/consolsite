package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Simplegui extends JFrame {
    JButton button = new JButton("Start");
    JTextField input = new JTextField("", 5);
    JLabel text = new JLabel("Реал мадрид победит?)");
    JRadioButton radio1 = new JRadioButton("Да");
    JRadioButton radio2 = new JRadioButton("Нет");
    JCheckBox check = new JCheckBox("Соглашаюсь с правилами", false);

    public Simplegui(){
        super("Menu");
        this.setBounds(100, 100, 400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(text);
        container.add(input);

        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        container.add(radio1);
        radio1.setSelected(true);
        container.add(radio2);
        container.add(check);

        button.addActionListener(new ButtonEvent());
        container.add(button);
    }

    class ButtonEvent implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String message = "";
            message += (radio1.isSelected() ? "Конечно победит))))" : "Все равно победит .!.\n");
            message += "\nКомментарий: " + input.getText();
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }

    }

}
