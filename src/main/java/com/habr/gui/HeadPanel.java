package com.habr.gui;

import javax.swing.*;
import java.awt.*;

public class HeadPanel extends JPanel {

    private JLabel labelForPageNumber;
    private ContentPanel contentPanel;
    private String pageAddress;
    static JPanel swithcingPanel;

    HeadPanel(ContentPanel cp) {
        setBackground(Color.white);
        setLayout(new BorderLayout());
        contentPanel = cp;
        pageAddress = "https://habrahabr.ru/top/page1";
        addHeadImages();
        addPanelForSwitchingPages();
        addPanelForSelectingPages();
        addPanelForStyle();
    }

    private void addHeadImages() {
        ImageIcon imageIcon = new ImageIcon("icons//head.png");
        JLabel headImage = new JLabel(imageIcon);
        add(headImage, BorderLayout.WEST);
    }

    private void addPanelForSwitchingPages() {
        swithcingPanel = new JPanel();
        swithcingPanel.setLayout(new GridBagLayout());
        swithcingPanel.setBackground(Color.white);
        addLeftButton();
        addLabelForPageNumber();
        addRightButton();
        add(swithcingPanel, BorderLayout.CENTER);
    }

    private void addLeftButton() {
        ImageIcon imageIcon = new ImageIcon("icons//left.png");
        JButton leftButton = new JButton(imageIcon);
        setStyleForButtons(leftButton, false);
        swithcingPanel.add(leftButton,
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 85, 0, 20), 0, 0));

        leftButton.addActionListener(event -> {
            if (!labelForPageNumber.getText().equals("1")){
                int temp = Integer.parseInt(labelForPageNumber.getText());
                labelForPageNumber.setText(String.valueOf(temp - 1));
                pageAddress = pageAddress.replace((char)(temp + '0'), (char)((temp - 1) + '0'));
                contentPanel.addButtonsOnContentPanel(pageAddress);
            }
        });
    }

    private void addLabelForPageNumber() {
        labelForPageNumber = new JLabel("1");
        labelForPageNumber.setForeground(Color.darkGray);
        labelForPageNumber.setFont(new Font("Times Roman", Font.PLAIN, 25));
        swithcingPanel.add(labelForPageNumber,
                new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addRightButton() {
        ImageIcon imageIcon = new ImageIcon("icons//right.png");
        JButton rightButton = new JButton(imageIcon);
        setStyleForButtons(rightButton, false);
        swithcingPanel.add(rightButton,
                new GridBagConstraints(2, 0, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 20, 0, 0), 0, 0));

        rightButton.addActionListener(event -> {
            int temp = Integer.parseInt(labelForPageNumber.getText());
            labelForPageNumber.setText(String.valueOf(temp + 1));
            pageAddress = pageAddress.replace((char)(temp + '0'), (char)((temp + 1) + '0'));
            contentPanel.addButtonsOnContentPanel(pageAddress);
        });
    }

    private void addPanelForSelectingPages() {
        JPanel selectingPanel = new JPanel();
        selectingPanel.setLayout(new GridBagLayout());
        selectingPanel.setBackground(Color.white);
        addComponentsForSelectingBestTitles(selectingPanel);
        addButtonForSelectingAllArticles(selectingPanel);
        add(selectingPanel, BorderLayout.EAST);
    }

    private void addComponentsForSelectingBestTitles(JPanel panel) {
        JButton buttonForBestArticles = new JButton("Лучшие статьи");
        setStyleForButtons(buttonForBestArticles, true);
        panel.add(buttonForBestArticles,
                new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 0, 0, 10), 0, 0));

        String[] array = {"За сутки", "За неделю", "За месяц", "За всё время"};
        JComboBox comboBox = new JComboBox(array);
        comboBox.setForeground(Color.darkGray);
        comboBox.setBackground(Color.white);
        comboBox.setRequestFocusEnabled(false);
        panel.add(comboBox,
                new GridBagConstraints(1, 1, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 0, 0, 10), 0, 0));


        buttonForBestArticles.addActionListener(event -> {
            contentPanel.addButtonsOnContentPanel(pageAddress);
        });

        comboBox.addActionListener(event -> {
            switch (comboBox.getSelectedIndex()) {
                case 0: {
                    switchCaseSelecting("https://habrahabr.ru/top/page1");
                    break;
                }
                case 1: {
                    switchCaseSelecting("https://habrahabr.ru/top/weekly/page1");
                    break;
                }
                case 2: {
                    switchCaseSelecting("https://habrahabr.ru/top/monthly/page1");
                    break;
                }
                case 3: {
                    switchCaseSelecting("https://habrahabr.ru/top/alltime/page1");
                    break;
                }
            }
        });
    }

    private void switchCaseSelecting(String address){
        labelForPageNumber.setText("1");
        pageAddress = address;
        contentPanel.addButtonsOnContentPanel(pageAddress);
    }

    private void addButtonForSelectingAllArticles(JPanel panel) {
        JButton buttonForAllArticles = new JButton("Все статьи");
        setStyleForButtons(buttonForAllArticles, true);
        panel.add(buttonForAllArticles,
                new GridBagConstraints(0, 0, 1, 2, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.VERTICAL,
                        new Insets(5, 0, 0, 10), 0, 0));

        buttonForAllArticles.addActionListener(event -> {
            labelForPageNumber.setText("1");
            pageAddress = "https://habrahabr.ru/all/page1";
            contentPanel.addButtonsOnContentPanel(pageAddress);
        });
    }

    private void addPanelForStyle() {
        JPanel panelForStyle = new JPanel();
        panelForStyle.setBackground(new Color(65, 105, 225));
        add(panelForStyle, BorderLayout.SOUTH);
    }

    private void setStyleForButtons(JButton button, boolean borderPainted) {
        button.setBorderPainted(borderPainted);
        button.setFocusPainted(false);
        button.setBackground(Color.white);
        button.setForeground(Color.darkGray);
    }
}
