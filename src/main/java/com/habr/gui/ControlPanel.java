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

    private String currentContent;

    ControlPanel() {
        setBackground(Color.white);
        setLayout(new GridBagLayout());
        GUI();
        currentContent = "";
    }

    private void GUI() {
        addButtonToViewBestArticles();
        addButtonToViewAllArticles();
        addButtonsForSelecting();
    }

    private void addButtonToViewBestArticles() {
        buttonToViewBestArticles = new JButton("Лучшие статьи");
        setButton(buttonToViewBestArticles);

        buttonToViewBestArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfDayArticles, true);
            setContentString("https://habrahabr.ru/top/");
        });

        add(buttonToViewBestArticles,
                new GridBagConstraints(0, 0, 2, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 0, 0, 0), 0, 0));
    }

    private void addButtonToViewAllArticles() {
        buttonToViewAllArticles = new JButton("Все статьи");
        setButton(buttonToViewAllArticles);

        buttonToViewAllArticles.addActionListener(event -> {
            setEnabledButton(new JButton(), false);
            setContentString("https://habrahabr.ru/all/");
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
        setButton(buttonToViewBestOfDayArticles);
        buttonList.add(buttonToViewBestOfDayArticles);

        buttonToViewBestOfDayArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfDayArticles, true);
            setContentString("https://habrahabr.ru/top/");
        });

        add(buttonToViewBestOfDayArticles,
                new GridBagConstraints(0, 1, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addButtonToViewBestOfWeekArticles() {
        buttonToViewBestOfWeekArticles = new JButton("За неделю");
        setButton(buttonToViewBestOfWeekArticles);
        buttonList.add(buttonToViewBestOfWeekArticles);

        buttonToViewBestOfWeekArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfWeekArticles, true);
            setContentString("https://habrahabr.ru/top/weekly/");
        });

        add(buttonToViewBestOfWeekArticles,
                new GridBagConstraints(1, 1, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addButtonToViewBestOfMonthArticles() {
        buttonToViewBestOfMonthArticles = new JButton("За месяц");
        setButton(buttonToViewBestOfMonthArticles);
        buttonList.add(buttonToViewBestOfMonthArticles);

        buttonToViewBestOfMonthArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfMonthArticles, true);
            setContentString("https://habrahabr.ru/top/monthly/");
        });

        add(buttonToViewBestOfMonthArticles,
                new GridBagConstraints(0, 2, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void addButtonToViewBestOfAllTimeArticles() {
        buttonToViewBestOfAllTimeArticles = new JButton("За всё время");
        setButton(buttonToViewBestOfAllTimeArticles);
        buttonList.add(buttonToViewBestOfAllTimeArticles);

        buttonToViewBestOfAllTimeArticles.addActionListener(event -> {
            setEnabledButton(buttonToViewBestOfAllTimeArticles, true);
            setContentString("https://habrahabr.ru/top/alltime/");

        });

        add(buttonToViewBestOfAllTimeArticles,
                new GridBagConstraints(1, 2, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
    }

    private void setEnabledButton(JButton button, boolean bool) {
        for (JButton b : buttonList) {
            b.setEnabled(bool);
        }
        button.setEnabled(false);
    }

    private void setContentString(String pageAddress) {
        currentContent = pageAddress;
        Parser.setContentStringWithHTML(currentContent);
    }

    private void setButton(JButton button) {
        button.setBackground(Color.white);
        button.setForeground(Color.darkGray);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

}