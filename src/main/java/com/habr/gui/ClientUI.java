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
        setVisible(true);
        //setExtendedState(MAXIMIZED_BOTH);
    }

    public void GUI() throws Exception {
        /*
        String s = getContentOfHTTPPage("https://habrahabr.ru");
        Document html = Jsoup.parse(s);
        Elements e = html.select("div.post.post_teaser.shortcuts_item");
        Element e1 = e.get(0);
        Element e2 = e1.select("img").first();
        String url = e2.absUrl("src");
        Panel p = new Panel(url);
        add(p);
        p.repaint();
        ImageIcon ic = new ImageIcon("img.png");
        JLabel l = new JLabel(ic);
        add(l);*/
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
        add(new ControlPanel(), BorderLayout.EAST);
        add(new ContentPanel(), BorderLayout.CENTER);
        add(new BackComandPanel(), BorderLayout.WEST);
    }




}
