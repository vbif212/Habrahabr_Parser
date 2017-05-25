package com.habr.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<JButton> getButtonsWithArticles(String pageAddress) {
        String content = "";
        try {
            content = getContentOfHTTPPage(pageAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createButtonsForArticles(content);
    }

    private static ArrayList<JButton> createButtonsForArticles(String content) {
        ArrayList<JButton> buttonsArrayList = new ArrayList<>();
        Document doc = Jsoup.parse(content);
        Elements articles = doc.select("div.post__header");
        for (Element element : articles) {
            Elements elements = element.getElementsByAttribute("href");
            boolean check = false;
            for (Element e : elements){
                if (e.attr("href").equals("https://habrahabr.ru/company/mailru/")){
                    check = true;
                    break;
                }
            }
            if (check){
                continue;
            }
            Element e1 = element.select("a.post__flow").first();
            Element e2 = element.select("a.post__title_link").first();
            JButton button = new JButton(e1.text() + ": " + e2.text());
            setStyleForButtons(button);
            button.addActionListener(event -> {
                openArticle(e2.attr("href"), e2.text(), (JPanel) button.getParent());
            });
            buttonsArrayList.add(button);
        }
        return buttonsArrayList;
    }

    public static void openArticle(String pageAddress, String titleString, JPanel parentPanel) {
        parentPanel.removeAll();
        parentPanel.updateUI();
        String articleContent = "";
        try {
            articleContent = getContentOfHTTPPage(pageAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(articleContent);
        JLabel title = new JLabel(titleString);
        title.setFont(new Font("Times Roman", Font.BOLD, 24));
        title.setForeground(Color.darkGray);
        parentPanel.add(title,
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 10, 0), 0, 0));
        parseArticle(parentPanel, doc);
    }

    private static void parseArticle(JPanel panel, Document document) {
        Element classOfArticle = document.select("div.content.html_format.js-mediator-article").first();
        Elements childrenClassesOfArticle = classOfArticle.children();
    }

    private static void setStyleForButtons(JButton button) {
        button.setForeground(Color.white);
        button.setBackground(new Color(65, 105, 225));
        button.setFocusPainted(false);
    }

    private static String getContentOfHTTPPage(String pageAddress) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            URL pageURL = new URL(pageAddress);
            URLConnection uc = pageURL.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            try {
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Страници с таким номером не существует");
        }
        return sb.toString();
    }
}
