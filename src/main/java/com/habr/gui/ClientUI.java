package com.habr.gui;

import javax.swing.*;
import java.awt.*;

public class ClientUI extends JFrame {

    TextArea ta;

    public ClientUI() throws Exception {
        super("Habrahabr");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GUI();
        ContentPanel cp = new ContentPanel();
        add(new HeadPanel(cp), BorderLayout.NORTH);
        add(cp, BorderLayout.CENTER);
        setVisible(true);
        //setExtendedState(MAXIMIZED_BOTH);
    }

    private void GUI(){

    }
}
