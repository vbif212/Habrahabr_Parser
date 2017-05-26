package com.habr.gui;

import com.habr.parser.Parser;

import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;
import java.util.ArrayList;

public class ContentPanel extends JPanel {

    private ArrayList<JButton> list;
    private JScrollPane scrollPane;

    ContentPanel() {
        setBackground(Color.white);
        setLayout(new GridBagLayout());
        setScrollPane();
        list = new ArrayList<>();
    }

    public void addButtonsOnContentPanel(String pageAddress) {
        removeAll();
        updateUI();
        Parser.resetComponentCounter();
        list.clear();
        list = Parser.getButtonsWithArticles(pageAddress);
        int i = 0;
        for (JButton b : list) {
            add(b,
                    new GridBagConstraints(0, i++, 1, 1, 0, 0,
                            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                            new Insets(0, 0, 10, 0), 0, 0));
        }
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    private void setScrollPane() {
        scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUI(new MetalScrollBarUI() {
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle tb) {
                g.setColor(new Color(65, 105, 225));
                if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                    g.fillRect(tb.x, tb.y, tb.width, tb.height);
                } else {
                    g.fillRect(tb.x, tb.y, tb.width, tb.height);
                }
            }
        });
        scrollPane.getHorizontalScrollBar().setUI(new MetalScrollBarUI() {
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle tb) {
                g.setColor(new Color(65, 105, 225));
                if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                    g.fillRect(tb.x, tb.y, tb.width, tb.height);
                } else {
                    g.fillRect(tb.x, tb.y, tb.width, tb.height);
                }
            }
        });
    }

}
