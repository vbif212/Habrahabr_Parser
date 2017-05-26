package com.habr.gui;

import javax.swing.*;
import java.awt.*;

public class ClientUI extends JFrame {

    public ClientUI() throws Exception {
        super("Habrahabr");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GUI();
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void GUI(){
        ContentPanel cp = new ContentPanel();
        add(new HeadPanel(cp), BorderLayout.NORTH);
        add(cp.getScrollPane(), BorderLayout.CENTER);
    }
}
