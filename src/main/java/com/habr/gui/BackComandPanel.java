package com.habr.gui;

import javax.swing.*;
import java.awt.*;

public class BackComandPanel extends JPanel {

    JButton backButton;

    BackComandPanel() {
        addBackButton();
        setBackground(Color.white);
    }

    private void addBackButton() {
        ImageIcon imageIcon = new ImageIcon("icons//back.png");
        backButton = new JButton(imageIcon);
        backButton.setBackground(Color.white);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setSize(getWidth(), getHeight());
        backButton.setEnabled(false);

        backButton.addActionListener(event -> {

        });

        add(backButton);
    }
}
