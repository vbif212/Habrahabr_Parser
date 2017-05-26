package com.habr.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static String addressOfPage = "";

    private static JPanel panel = new JPanel();

    private static int countComponentsOfArticle = 1;

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
        panel = parentPanel;
        addressOfPage = pageAddress;
        panel.removeAll();
        panel.updateUI();
        String articleContent = "";
        try {
            articleContent = getContentOfHTTPPage(addressOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(articleContent);
        setTitle(titleString);
        parseArticle(doc);
    }

    private static void parseArticle(Document document) {
        Element classOfArticle = document.select("div.content.html_format.js-mediator-article").first();
        List<Node> nodes = classOfArticle.childNodes();
        contentBypass(nodes);
    }

    private static void contentBypass(List<Node> nodesList) {
        for (Node node : nodesList) {
            boolean checkToContinue = contentProcessing(node);
            if (node.childNodeSize() > 0 && checkToContinue) {
                List<Node> nodes = node.childNodes();
                contentBypass(nodes);
            }
        }
    }

    private static boolean contentProcessing(Node node) {
        boolean check = true;
        switch (node.nodeName()) {
            case "h2": {
                JLabel h2Label = new JLabel(node.childNode(0).toString());
                h2Label.setFont(new Font("Times Roman", Font.BOLD, 22));
                h2Label.setForeground(Color.darkGray);
                panel.add(h2Label,
                        new GridBagConstraints(0, countComponentsOfArticle++, 1, 1, 0, 0,
                                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                                new Insets(0, 30, 10, 20), 0, 0));
                check = false;
                break;
            }
            case "h3": {
                JLabel h3Label = new JLabel(node.childNode(0).toString());
                h3Label.setFont(new Font("Times Roman", Font.BOLD, 20));
                h3Label.setForeground(Color.darkGray);
                panel.add(h3Label,
                        new GridBagConstraints(0, countComponentsOfArticle++, 1, 1, 0, 0,
                                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                                new Insets(0, 30, 10, 20), 0, 0));
                check = false;
                break;
            }
            case "h4": {
                JLabel h4Label = new JLabel(node.childNode(0).toString());
                h4Label.setFont(new Font("Times Roman", Font.BOLD, 18));
                h4Label.setForeground(Color.darkGray);
                panel.add(h4Label,
                        new GridBagConstraints(0, countComponentsOfArticle++, 1, 1, 0, 0,
                                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                                new Insets(0, 30, 10, 20), 0, 0));
                check = false;
                break;
            }
            case "img": {
                ImageIcon imageIcon = null;
                try {
                    BufferedImage bufferedImage = ImageIO.read(new URL(node.attr("src")));
                    imageIcon = new ImageIcon(bufferedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JLabel imgLabel = new JLabel(imageIcon);
                panel.add(imgLabel,
                        new GridBagConstraints(0, countComponentsOfArticle++, 1, 1, 0, 0,
                                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                                new Insets(0, 20, 10, 20), 0, 0));
                check = false;
                break;
            }
            case "code": {
                JTextArea labelForLink = new JTextArea("Код здесь: " + addressOfPage);
                labelForLink.setFont(new Font("Times Roman", Font.BOLD, 14));
                labelForLink.setBackground(Color.lightGray);
                labelForLink.setForeground(Color.darkGray);
                panel.add(labelForLink,
                        new GridBagConstraints(0, countComponentsOfArticle++, 1, 1, 0, 0,
                                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                                new Insets(0, 30, 10, 20), 0, 0));
                check = false;
                break;
            }
            case "iframe": {
                JTextArea labelForLink = new JTextArea("Ссылка на источник: " + node.attr("src"));
                labelForLink.setFont(new Font("Times Roman", Font.BOLD, 14));
                labelForLink.setBackground(Color.darkGray);
                labelForLink.setForeground(Color.white);
                panel.add(labelForLink,
                        new GridBagConstraints(0, countComponentsOfArticle++, 1, 1, 0, 0,
                                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                                new Insets(0, 30, 10, 20), 0, 0));
                check = false;
                break;
            }
            case "#text": {
                JTextArea jTextArea = new JTextArea(stringProcessing(node.toString()));
                jTextArea.setFont(new Font("Times Roman", Font.PLAIN, 14));
                jTextArea.setForeground(Color.darkGray);
                panel.add(jTextArea,
                        new GridBagConstraints(0, countComponentsOfArticle++, 1, 1, 0, 0,
                                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                                new Insets(0, 20, 10, 20), 0, 0));
                break;
            }
            case "hr/": {
                break;
            }
        }
        return check;
    }

    private static String stringProcessing(String sourceString) {
        String newString = "    ";
        while (sourceString.length() > 150) {
            int k = sourceString.lastIndexOf(" ", 150);
            newString += sourceString.substring(0, k) + "\n";
            sourceString = sourceString.substring(k, sourceString.length());
        }
        newString += sourceString;
        if (newString.equals("")) return sourceString;
        return newString;
    }

    private static void setTitle(String titleString) {
        JLabel title = new JLabel(titleString);
        title.setFont(new Font("Times Roman", Font.BOLD, 24));
        title.setForeground(Color.darkGray);
        panel.add(title,
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 50, 10, 20), 0, 0));
    }

    private static void setStyleForButtons(JButton button) {
        button.setForeground(Color.white);
        button.setBackground(new Color(65, 105, 225));
        button.setFocusPainted(false);
    }

    public static void resetComponentCounter() {
        countComponentsOfArticle = 1;
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
            JOptionPane.showMessageDialog(null, "Этой страницы не существует");
        }
        return sb.toString();
    }
}
