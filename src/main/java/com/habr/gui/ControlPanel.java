package com.habr.gui;

import com.habr.parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ControlPanel extends JPanel {

    private JButton buttonToViewBestArticles;
    private JButton buttonToViewAllArticles;
    private JButton buttonToViewBestOfDayArticles;
    private JButton buttonToViewBestOfWeekArticles;
    private JButton buttonToViewBestOfMonthArticles;
    private JButton buttonToViewBestOfAllTimeArticles;
    private JButton updateButton;
    private ArrayList<JButton> buttonList;

    ControlPanel() {
        setBackground(Color.darkGray);
        setLayout(new GridBagLayout());
        GUI();
    }

    private void GUI() {
        addButtonToViewBestArticles();
        addButtonToViewAllArticles();
        addButtonsForSelecting();
        addUpdateButton();
    }

    private void addButtonToViewBestArticles() {
        buttonToViewBestArticles = new JButton("Лучшие статьи");
        buttonToViewBestArticles.setBackground(Color.gray);
        buttonToViewBestArticles.setForeground(Color.white);
        buttonToViewBestArticles.setFocusPainted(false);
        buttonToViewBestArticles.setBorderPainted(false);

        buttonToViewBestArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfDayArticles, true);
            try {
                Parser.getContentOfHTTPPage("https://habrahabr.ru/top/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(buttonToViewBestArticles,
                new GridBagConstraints(0, 0, 2, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 0, 1, 0), 0, 0));
    }

    private void addButtonToViewAllArticles() {
        buttonToViewAllArticles = new JButton("Все статьи");
        buttonToViewAllArticles.setBackground(Color.gray);
        buttonToViewAllArticles.setForeground(Color.white);
        buttonToViewAllArticles.setFocusPainted(false);
        buttonToViewAllArticles.setBorderPainted(false);

        buttonToViewAllArticles.addActionListener(event -> {
            setEnabledButton(new JButton(), false);
            try {
                Parser.getContentOfHTTPPage("https://habrahabr.ru/all/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(buttonToViewAllArticles,
                new GridBagConstraints(0, 3, 2, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(10, 0, 1, 0), 0, 0));
    }

    private void addButtonsForSelecting() {
        buttonList = new ArrayList<>();
        addButtonToViewBestOfDayArticles();
        addButtonToViewBestOfWeekArticles();
        addButtonToViewBestOfMonthArticles();
        addButtonToViewBestOfAllTimeArticles();
    }

    private void addButtonToViewBestOfDayArticles() {
        buttonToViewBestOfDayArticles = new JButton("За день");
        buttonToViewBestOfDayArticles.setBackground(Color.gray);
        buttonToViewBestOfDayArticles.setForeground(Color.white);
        buttonToViewBestOfDayArticles.setFocusPainted(false);
        buttonToViewBestOfDayArticles.setBorderPainted(false);
        buttonList.add(buttonToViewBestOfDayArticles);

        buttonToViewBestOfDayArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfDayArticles, true);
            try {
                Parser.getContentOfHTTPPage("https://habrahabr.ru/top/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(buttonToViewBestOfDayArticles,
                new GridBagConstraints(0, 1, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addButtonToViewBestOfWeekArticles() {
        buttonToViewBestOfWeekArticles = new JButton("За неделю");
        buttonToViewBestOfWeekArticles.setBackground(Color.gray);
        buttonToViewBestOfWeekArticles.setForeground(Color.white);
        buttonToViewBestOfWeekArticles.setFocusPainted(false);
        buttonToViewBestOfWeekArticles.setBorderPainted(false);
        buttonList.add(buttonToViewBestOfWeekArticles);

        buttonToViewBestOfWeekArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfWeekArticles, true);
            try {
                Parser.getContentOfHTTPPage("https://habrahabr.ru/top/weekly/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(buttonToViewBestOfWeekArticles,
                new GridBagConstraints(1, 1, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addButtonToViewBestOfMonthArticles() {
        buttonToViewBestOfMonthArticles = new JButton("За месяц");
        buttonToViewBestOfMonthArticles.setBackground(Color.gray);
        buttonToViewBestOfMonthArticles.setForeground(Color.white);
        buttonToViewBestOfMonthArticles.setFocusPainted(false);
        buttonToViewBestOfMonthArticles.setBorderPainted(false);
        buttonList.add(buttonToViewBestOfMonthArticles);

        buttonToViewBestOfMonthArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfMonthArticles, true);
            try {
                Parser.getContentOfHTTPPage("https://habrahabr.ru/top/monthly/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(buttonToViewBestOfMonthArticles,
                new GridBagConstraints(0, 2, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addButtonToViewBestOfAllTimeArticles() {
        buttonToViewBestOfAllTimeArticles = new JButton("За всё время");
        buttonToViewBestOfAllTimeArticles.setBackground(Color.gray);
        buttonToViewBestOfAllTimeArticles.setForeground(Color.white);
        buttonToViewBestOfAllTimeArticles.setFocusPainted(false);
        buttonToViewBestOfAllTimeArticles.setBorderPainted(false);
        buttonList.add(buttonToViewBestOfAllTimeArticles);

        buttonToViewBestOfAllTimeArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfAllTimeArticles, true);
            try {
                Parser.getContentOfHTTPPage("https://habrahabr.ru/top/alltime/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        add(buttonToViewBestOfAllTimeArticles,
                new GridBagConstraints(1, 2, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addUpdateButton(){
        ImageIcon imageIcon = new ImageIcon("icons//update.png");
        updateButton = new JButton(imageIcon);
        updateButton.setBackground(Color.darkGray);
        updateButton.setFocusPainted(false);
        updateButton.setBorderPainted(false);

        updateButton.addActionListener(event -> {

        });

        add(updateButton,
                new GridBagConstraints(0, 4, 2, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(20, 0, 0, 0), 0, 0));
    }

    private void setEnabledButton(JButton button, boolean bool){
        for (JButton b : buttonList){
            b.setEnabled(bool);
        }
        button.setEnabled(false);
    }

}