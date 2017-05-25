package com.habr.gui;

import com.habr.parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContentPanel extends JPanel {

    ArrayList<JButton> list;

    ContentPanel() {
        setBackground(Color.white);
        setLayout(new GridBagLayout());
        list = new ArrayList<>();
    }

    public void addButtonsOnContentPanel(String pageAddress) {
        removeAll();
        updateUI();
        list.clear();
        list = Parser.getButtonsWithArticles(pageAddress);
        int i = 0;
        for (JButton b : list){
            add(b,
                    new GridBagConstraints(0, i++, 1, 1, 0, 0,
                            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                            new Insets(0, 0, 10, 0), 0, 0));
        }
    }

}
