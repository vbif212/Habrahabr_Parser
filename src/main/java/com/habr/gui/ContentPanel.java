package com.habr.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContentPanel extends JPanel {

    ArrayList<JButton> buttonsForArticles;
    ArrayList<JLabel> labelsForTitlesOfArticles;
    ArrayList<JLabel> labelsForImagesOfArticles;
    ArrayList<JTextField> textFieldsForArticles;

    ContentPanel() {
        setBackground(Color.white);
        setLayout(new BorderLayout());
        GUI();
    }

    private void GUI() {
        addHead();
        JScrollPane scrollPane = new JScrollPane(this);
        add(new ControlPanel(), BorderLayout.EAST);
    }

    private void addHead() {
        JPanel head = new JPanel();
        head.setLayout(new BorderLayout());
        head.setBackground(Color.white);
        ImageIcon img = new ImageIcon("icons//head.png");
        JLabel headImage = new JLabel(img);
        head.add(headImage, BorderLayout.CENTER);
        add(head, BorderLayout.NORTH);
        addHeadButtons(head);
    }

    private void addHeadButtons(JPanel head) {
        JPanel botHead = new JPanel();
        botHead.setBackground(Color.white);

        ImageIcon left = new ImageIcon("icons//left.png");
        JButton buttonLeft = new JButton(left);
        buttonLeft.setBackground(Color.white);
        buttonLeft.setFocusPainted(false);
        buttonLeft.setBorderPainted(false);
        botHead.add(buttonLeft, BorderLayout.WEST);

        JLabel number = new JLabel("0");
        number.setBackground(Color.white);
        number.setForeground(Color.darkGray);
        botHead.add(number, BorderLayout.CENTER);

        ImageIcon right = new ImageIcon("icons//right.png");
        JButton buttonRight = new JButton(right);
        buttonRight.setBackground(Color.white);
        buttonRight.setFocusPainted(false);
        buttonRight.setBorderPainted(false);
        botHead.add(buttonRight, BorderLayout.EAST);

        head.add(botHead, BorderLayout.SOUTH);
    }
}
